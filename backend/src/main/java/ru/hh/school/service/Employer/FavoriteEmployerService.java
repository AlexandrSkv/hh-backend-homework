package ru.hh.school.service.Employer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.FavoriteEmployerDto;
import ru.hh.school.entity.FavoriteEmployerEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteEmployerService {

    private final EmployerDao employerDao;
    private final EmployerService employerService;
    private Integer popular_rate;

    @Autowired
    public FavoriteEmployerService(EmployerDao employerDao, EmployerService employerService, FileSettings fileSettings) {
        this.employerDao = employerDao;
        this.employerService = employerService;
        this.popular_rate = fileSettings.getInteger("popular_rate");
    }

    @Transactional
    public Integer addFavoriteEmployer(Integer employerId, String comment) throws JsonProcessingException {
        EmployerDto employerDto = employerService.getEmployer(employerId);
        LocalDateTime localDateTime = LocalDateTime.now();
        Integer views_count  = 0;
        Integer areaId = Integer.parseInt(employerDto.getArea().get("id"));
        String areaName = employerDto.getArea().get("name");
        FavoriteEmployerEntity favoriteEmployerEntity = new FavoriteEmployerEntity(employerDto.getId(),employerDto.getName(),localDateTime,employerDto.getDescription(),areaId,areaName,comment,views_count);

        return employerDao.addFavoriteEmployer(favoriteEmployerEntity);
    }

    @Transactional
    public ArrayList<FavoriteEmployerDto> getFavoriteEmployers(Integer page, Integer per_page) {

        List<FavoriteEmployerEntity> employerDaoList = employerDao.getFavoriteEmployers(page,per_page);
        ArrayList<FavoriteEmployerDto> favoriteEmployerDto = new ArrayList<>();

        for (FavoriteEmployerEntity employer : employerDaoList ) {

            favoriteEmployerDto.add(new FavoriteEmployerDto(
                    employer.getId(),
                    employer.getName(),
                    employer.getDescription(),
                    employer.getAreaId(),
                    employer.getAreaName(),
                    employer.getData_create(),
                    employer.getComment(),
                    employer.getViews_count(),
                    popular_rate));

            employer.setViews_count(employer.getViews_count()+1);
            employerDao.addFavoriteEmployer(employer);
        }

        return favoriteEmployerDto;
    }

    @Transactional
    public void updateFavoriteEmployer(Integer employerId, String comment) {
        FavoriteEmployerEntity employer = employerDao.getFavoriteEmployer(employerId)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found with id " + employerId));
        employer.setComment(comment);
        employerDao.addFavoriteEmployer(employer);
    }

    @Transactional
    public void deleteFavoriteEmployer(Integer employerId) {
        employerDao.deleteFavoriteEmployer(employerId);
    }

    @Transactional
    public void refreshFavoriteEmployer(Integer employerId) throws JsonProcessingException {
        EmployerDto employerDto = employerService.getEmployer(employerId);
        FavoriteEmployerEntity employer = employerDao.getFavoriteEmployer(employerId)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found with id " + employerId));

        employer.setName(employerDto.getName());
        employer.setDescription(employerDto.getDescription());
        employer.setAreaId(Integer.parseInt(employerDto.getArea().get("id")));
        employer.setAreaName(employerDto.getArea().get("name"));

        employerDao.addFavoriteEmployer(employer);
    }
}
