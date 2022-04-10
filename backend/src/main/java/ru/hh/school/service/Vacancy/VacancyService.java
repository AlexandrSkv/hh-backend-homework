package ru.hh.school.service.Vacancy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dto.Vacancy.VacancyDto;
import ru.hh.school.util.HHApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class VacancyService {

    private final HHApi HHApi;

    public VacancyService(HHApi HHApi) {
        this.HHApi = HHApi;
    }

    @Transactional
    public ArrayList<VacancyDto> getVacancyList(String text, Integer page, Integer per_page) throws JsonProcessingException {
        String response = HHApi.getVacancies(text,page,per_page);

        ArrayList<VacancyDto> vacancyListDto = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode arrNode = mapper.readTree(response).get("items");

        for (final JsonNode objNode : arrNode) {
            Map<?, ?> map = mapper.readValue(objNode.toString(), Map.class);
            Integer id = Integer.parseInt((String) map.get("id"));
            String name = (String) map.get("name");

            Map<?, ?> areaMap = (Map<?,?>) map.get("area");
            String areaId = (String) areaMap.get("id");
            String areaName = (String) areaMap.get("name");

            LinkedHashMap<String,String> area = new LinkedHashMap<>();
            area.put("id",areaId);
            area.put("name",areaName);

            Map<?, ?> salaryMap = (Map<?,?>) map.get("salary");
            Integer salaryFrom = (Integer) salaryMap.get("from");
            Integer salaryTo = (Integer) salaryMap.get("to");
            String salaryCurrency = (String) salaryMap.get("currency");
            Boolean salaryGross = (Boolean) salaryMap.get("gross");

            LinkedHashMap<String,Object> salary = new LinkedHashMap<>();
            salary.put("from",salaryFrom);
            salary.put("to",salaryTo);
            salary.put("currency",salaryCurrency);
            salary.put("gross",salaryGross);

            String createdAt = (String) map.get("created_at");

            Map<?, ?> employerMap = (Map<?,?>) map.get("employer");
            Integer employer = (Integer) employerMap.get("id");

            vacancyListDto.add(new VacancyDto(id, name, area, salary, createdAt, employer));

        }

        return vacancyListDto;
    }

    @Transactional
    public VacancyDto getVacancy (Integer vacancyId) throws JsonProcessingException {
        String response = HHApi.getVacancy(vacancyId);

        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = mapper.readValue(response, Map.class);
        Integer id = Integer.parseInt((String) map.get("id"));
        String name = (String) map.get("name");

        Map<?, ?> areaMap = (Map<?,?>) map.get("area");
        String areaId = (String) areaMap.get("id");
        String areaName = (String) areaMap.get("name");

        LinkedHashMap<String,String> area = new LinkedHashMap<>();
        area.put("id",areaId);
        area.put("name",areaName);

        Map<?, ?> salaryMap = (Map<?,?>) map.get("salary");
        Integer salaryFrom = (Integer) salaryMap.get("from");
        Integer salaryTo = (Integer) salaryMap.get("to");
        String salaryCurrency = (String) salaryMap.get("currency");
        Boolean salaryGross = (Boolean) salaryMap.get("gross");

        LinkedHashMap<String,Object> salary = new LinkedHashMap<>();
        salary.put("from",salaryFrom);
        salary.put("to",salaryTo);
        salary.put("currency",salaryCurrency);
        salary.put("gross",salaryGross);

        String createdAt = (String) map.get("created_at");

        Map<?, ?> employerMap = (Map<?,?>) map.get("employer");
        Integer employer = (Integer) employerMap.get("id");

        return new VacancyDto(id, name, area, salary,createdAt, employer);
    }

}
