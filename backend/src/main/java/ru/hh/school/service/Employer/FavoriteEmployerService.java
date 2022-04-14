package ru.hh.school.service.Employer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.Area;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.FavoriteEmployerDto;
import ru.hh.school.entity.FavoriteEmployerEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteEmployerService {

    private final EmployerDao employerDao;
    private final EmployerService employerService;
    private final Integer popular_rate;

    public FavoriteEmployerService(EmployerDao employerDao, EmployerService employerService, FileSettings fileSettings) {
        this.employerDao = employerDao;
        this.employerService = employerService;
        this.popular_rate = fileSettings.getInteger("popular_rate");
    }

    @Transactional
    public void addFavoriteEmployer(String employerId, String comment) throws JsonProcessingException {
        FavoriteEmployerEntity employer = employerDao.getFavoriteEmployer(employerId);
        if (employer == null) {
            EmployerDto employerDto = employerService.getEmployer(employerId);

            FavoriteEmployerEntity favoriteEmployerEntity = new FavoriteEmployerEntity(
                    employerDto.getId(),
                    employerDto.getName(),
                    employerDto.getDescription(),
                    employerDto.getArea().getId(),
                    employerDto.getArea().getName(),
                    comment);

            employerDao.SaveFavoriteEmployer(favoriteEmployerEntity);
        }
    }

    @Transactional
    public ArrayList<FavoriteEmployerDto> getFavoriteEmployers(Integer page, Integer per_page) {

        List<FavoriteEmployerEntity> employerDaoList = employerDao.getFavoriteEmployers(page,per_page);
        ArrayList<FavoriteEmployerDto> favoriteEmployerDto = new ArrayList<>();

        for (FavoriteEmployerEntity employer : employerDaoList ) {

            Area area = new Area(employer.getId(), employer.getName());

            favoriteEmployerDto.add(new FavoriteEmployerDto(
                    employer.getId(),
                    employer.getName(),
                    employer.getDescription(),
                    area,
                    employer.getData_create(),
                    employer.getComment(),
                    employer.getViews_count(),
                    popular_rate));

            employer.setViews_count(employer.getViews_count() + 1);
        }

        return favoriteEmployerDto;
    }

    @Transactional
    public void updateFavoriteEmployer(String employerId, String comment) {
        FavoriteEmployerEntity employer = employerDao.getFavoriteEmployer(employerId);
        if (employer != null)
        {
            employer.setComment(comment);
        }
    }

    @Transactional
    public void deleteFavoriteEmployer(String employerId) {
        FavoriteEmployerEntity employer = employerDao.getFavoriteEmployer(employerId);
        if (employer != null) {
            employerDao.deleteFavoriteEmployer(employerId);
        }
    }

    @Transactional
    public void refreshFavoriteEmployer(String employerId) throws JsonProcessingException {
        EmployerDto employerDto = employerService.getEmployer(employerId);
        FavoriteEmployerEntity employer = employerDao.getFavoriteEmployer(employerId);

        if (employer != null)
        {
            employer.setName(employerDto.getName());
            employer.setDescription(employerDto.getDescription());
            employer.setAreaId(employerDto.getArea().getId());
            employer.setAreaName(employerDto.getArea().getName());
        }
    }

}
