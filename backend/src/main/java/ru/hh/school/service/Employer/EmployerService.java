package ru.hh.school.service.Employer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.EmployerListDto;
import ru.hh.school.service.HHService;

import java.util.ArrayList;

@Service
public class EmployerService {

    private final HHService hhService;

    public EmployerService(HHService hhService) {
        this.hhService = hhService;
    }

    @Transactional
    public ArrayList<EmployerListDto> getEmployerList(String text, Integer page, Integer per_page) throws JsonProcessingException {

        ArrayList<EmployerListDto> employerListDto = hhService.getEmployers(text,page,per_page);

        return employerListDto;
    }

    @Transactional
    public EmployerDto getEmployer(Integer employerId) throws JsonProcessingException {

        EmployerDto employerDto = hhService.getEmployer(employerId);
        return  employerDto;

    }

}
