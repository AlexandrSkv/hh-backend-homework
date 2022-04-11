package ru.hh.school.service.Vacancy;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dto.Vacancy.VacancyDto;
import ru.hh.school.service.HHService;

import java.util.ArrayList;

@Service
public class VacancyService {

    private final HHService hhService;

    public VacancyService(HHService hhService) {
        this.hhService = hhService;
    }

    @Transactional
    public ArrayList<VacancyDto> getVacancyList(String text, Integer page, Integer per_page) throws JsonProcessingException {
       ArrayList<VacancyDto> vacancyDtoList  = hhService.getVacancies(text,page,per_page);
        return vacancyDtoList;
    }

    @Transactional
    public VacancyDto getVacancy (Integer vacancyId) throws JsonProcessingException {
        VacancyDto vacancyDto = hhService.getVacancy(vacancyId);
        return  vacancyDto;
    }

}
