package ru.hh.school.service.Vacancy;

import com.fasterxml.jackson.core.JsonProcessingException;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.Vacancy.FavoriteVacancyDto;
import ru.hh.school.dto.Vacancy.VacancyDto;
import ru.hh.school.entity.FavoriteVacancyEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteVacancyService {

    private final VacancyDao vacancyDao;
    private final VacancyService vacancyService;
    private Integer popular_rate;

    @Autowired
    public FavoriteVacancyService(VacancyDao vacancyDao, VacancyService vacancyService, FileSettings fileSettings) {
        this.vacancyDao = vacancyDao;
        this.vacancyService = vacancyService;
        this.popular_rate = fileSettings.getInteger("popular_rate");
    }

    @Transactional
    public Integer addFavoriteVacancy (Integer vacancyId, String comment) throws JsonProcessingException {
        VacancyDto vacancyDto = vacancyService.getVacancy(vacancyId);
        LocalDateTime localDateTime = LocalDateTime.now();
        Integer views_count  = 0;
        Integer areaId = Integer.parseInt(vacancyDto.getArea().get("id"));
        String areaName = vacancyDto.getArea().get("name");
        Integer salaryFrom = (Integer) vacancyDto.getSalary().get("from");
        Integer salaryTo = (Integer) vacancyDto.getSalary().get("to");
        String salaryCurrency = (String) vacancyDto.getSalary().get("currency");
        Boolean salaryGross = (Boolean) vacancyDto.getSalary().get("gross");
        Integer employer = vacancyDto.getEmployer();

        FavoriteVacancyEntity favoriteVacancyEntity = new FavoriteVacancyEntity(
                vacancyDto.getId(),
                vacancyDto.getName(),
                localDateTime,
                areaId,
                areaName,
                salaryFrom,
                salaryTo,
                salaryCurrency,
                salaryGross,
                vacancyDto.getCreated_at(),
                comment,
                employer,
                views_count);

        return vacancyDao.addFavoriteVacancy(favoriteVacancyEntity);
    }

    @Transactional
    public ArrayList<FavoriteVacancyDto> getFavoriteVacancies(Integer page, Integer per_page) {

        List<FavoriteVacancyEntity> vacancyDaoList = vacancyDao.getFavoriteVacancies(page,per_page);
        ArrayList<FavoriteVacancyDto> favoriteVacancyDto = new ArrayList<>();

        for (FavoriteVacancyEntity vacancy : vacancyDaoList ) {

            favoriteVacancyDto.add(new FavoriteVacancyDto(
                    vacancy.getId(),
                    vacancy.getName(),
                    vacancy.getData_create(),
                    vacancy.getAreaId(),
                    vacancy.getAreaName(),
                    vacancy.getSalary_from(),
                    vacancy.getSalary_to(),
                    vacancy.getSalary_currency(),
                    vacancy.getSalary_gross(),
                    vacancy.getCreated_at(),
                    vacancy.getEmployer(),
                    vacancy.getViews_count(),
                    vacancy.getComment(),
                    popular_rate));

            vacancy.setViews_count(vacancy.getViews_count()+1);
            vacancyDao.addFavoriteVacancy(vacancy);
        }

        return favoriteVacancyDto;
    }

    @Transactional
    public void deleteFavoriteVacancy(Integer vacancyId) {
        vacancyDao.deleteFavoriteVacancy(vacancyId);
    }

    @Transactional
    public void refreshFavoriteVacancy(Integer vacancyId) throws JsonProcessingException {
        VacancyDto vacancyDto = vacancyService.getVacancy(vacancyId);
        FavoriteVacancyEntity vacancy = vacancyDao.getFavoriteVacancy(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Vacancy not found with id " + vacancyId));

        vacancy.setName(vacancyDto.getName());

        vacancy.setSalary_from(Integer.parseInt((String) vacancyDto.getSalary().get("from")));
        vacancy.setSalary_to(Integer.parseInt((String) vacancyDto.getSalary().get("to")));
        vacancy.setSalary_currency((String) vacancyDto.getSalary().get("currency"));
        vacancy.setSalary_gross((Boolean) vacancyDto.getSalary().get("gross"));

        vacancy.setAreaId(Integer.parseInt(vacancyDto.getArea().get("id")));
        vacancy.setAreaName(vacancyDto.getArea().get("name"));

        vacancyDao.addFavoriteVacancy(vacancy);
    }
}
