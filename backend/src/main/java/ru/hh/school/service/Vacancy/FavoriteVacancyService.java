package ru.hh.school.service.Vacancy;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.Area;
import ru.hh.school.dto.Employer.EmployerShortDto;
import ru.hh.school.dto.Salary;
import ru.hh.school.dto.Vacancy.FavoriteVacancyDto;
import ru.hh.school.dto.Vacancy.VacancyDto;
import ru.hh.school.entity.FavoriteEmployerEntity;
import ru.hh.school.entity.FavoriteVacancyEntity;

import java.util.ArrayList;
import java.util.List;


@Service
public class FavoriteVacancyService {

    private final VacancyDao vacancyDao;
    private final EmployerDao employerDao;
    private final VacancyService vacancyService;
    private final Integer popular_rate;

    @Autowired
    public FavoriteVacancyService(VacancyDao vacancyDao, EmployerDao employerDao, VacancyService vacancyService, FileSettings fileSettings) {
        this.vacancyDao = vacancyDao;
        this.employerDao = employerDao;
        this.vacancyService = vacancyService;
        this.popular_rate = fileSettings.getInteger("popular_rate");
    }

    @Transactional
    public void  addFavoriteVacancy (String vacancyId, String comment) throws JsonProcessingException {
        FavoriteVacancyEntity vacancy = vacancyDao.getFavoriteVacancy(vacancyId);
        if (vacancy == null) {
            VacancyDto vacancyDto = vacancyService.getVacancy(vacancyId);

            FavoriteVacancyEntity favoriteVacancyEntity = new FavoriteVacancyEntity(
                    vacancyDto.getId(),
                    vacancyDto.getName(),
                    vacancyDto.getArea().getId(),
                    vacancyDto.getArea().getName(),
                    vacancyDto.getSalary().getFrom(),
                    vacancyDto.getSalary().getTo(),
                    vacancyDto.getSalary().getCurrency(),
                    vacancyDto.getSalary().getGross(),
                    vacancyDto.getCreated_at(),
                    comment,
                    vacancyDto.getEmployer().getId(),
                    vacancyDto.getEmployer().getName());

            vacancyDao.SaveFavoriteVacancy(favoriteVacancyEntity);
        }
    }

    @Transactional
    public ArrayList<FavoriteVacancyDto> getFavoriteVacancies(Integer page, Integer per_page) {

        List<FavoriteVacancyEntity> vacancyDaoList = vacancyDao.getFavoriteVacancies(page,per_page);
        ArrayList<FavoriteVacancyDto> favoriteVacancyDto = new ArrayList<>();

        for (FavoriteVacancyEntity vacancy : vacancyDaoList ) {

            Area area = new Area(
                    vacancy.getId(),
                    vacancy.getName());

            Salary salary = new Salary(
                    vacancy.getSalary_from(),
                    vacancy.getSalary_to(),
                    vacancy.getSalary_currency(),
                    vacancy.getSalary_gross());

            EmployerShortDto employerShortDto = new EmployerShortDto(
                    vacancy.getEmployer_id(),
                    vacancy.getEmployer_name());

           favoriteVacancyDto.add(new FavoriteVacancyDto(
                    vacancy.getId(),
                    vacancy.getName(),
                    vacancy.getData_create(),
                    area,
                    salary,
                    vacancy.getCreated_at(),
                    employerShortDto,
                    vacancy.getViews_count(),
                    vacancy.getComment(),
                    popular_rate));

            vacancy.setViews_count(vacancy.getViews_count() + 1);

            FavoriteEmployerEntity favoriteEmployerEntity = employerDao.getFavoriteEmployer(vacancy.getEmployer_id());

            if (favoriteEmployerEntity != null)
            {
                favoriteEmployerEntity.setViews_count(favoriteEmployerEntity.getViews_count() + 1);
            }
        }
        return favoriteVacancyDto;
    }

    @Transactional
    public void deleteFavoriteVacancy(String vacancyId) {
        FavoriteVacancyEntity vacancy = vacancyDao.getFavoriteVacancy(vacancyId);
        if (vacancy != null) {
            vacancyDao.deleteFavoriteVacancy(vacancyId);
        }
    }

    @Transactional
    public void refreshFavoriteVacancy(String vacancyId) throws JsonProcessingException {
        VacancyDto vacancyDto = vacancyService.getVacancy(vacancyId);
        FavoriteVacancyEntity vacancy = vacancyDao.getFavoriteVacancy(vacancyId);

        if (vacancy != null)
        {
            vacancy.setName(vacancyDto.getName());
            vacancy.setAreaId(vacancyDto.getArea().getId());
            vacancy.setAreaName(vacancyDto.getArea().getName());
            vacancy.setSalary_from(vacancyDto.getSalary().getFrom());
            vacancy.setSalary_to(vacancyDto.getSalary().getTo());
            vacancy.setSalary_currency(vacancyDto.getSalary().getCurrency());
            vacancy.setSalary_gross(vacancyDto.getSalary().getGross());
        }
    }

}
