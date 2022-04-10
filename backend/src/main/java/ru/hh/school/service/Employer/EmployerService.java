package ru.hh.school.service.Employer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.EmployerListDto;
import ru.hh.school.util.HHApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class EmployerService {

    private final HHApi HHApi;

    public EmployerService(HHApi HHApi) {
        this.HHApi = HHApi;
    }

    @Transactional
    public ArrayList<EmployerListDto> getEmployerList(String text, Integer page, Integer per_page) throws JsonProcessingException {
        String response = HHApi.getEmployers(text,page,per_page);

        ArrayList<EmployerListDto> employerListDto = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode arrNode = mapper.readTree(response).get("items");

        for (final JsonNode objNode : arrNode) {
            employerListDto.add(new EmployerListDto(Integer.parseInt(objNode.get("id").asText()),
                    objNode.get("name").asText()));
        }
        return employerListDto;
    }

    @Transactional
    public EmployerDto getEmployer(Integer employerId) throws JsonProcessingException {
        String response = HHApi.getEmployer(employerId);

        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = mapper.readValue(response, Map.class);
        Integer id = Integer.parseInt((String) map.get("id"));
        String name = (String) map.get("name");
        String description = (String) map.get("description");
        Map<?, ?> areaMap = (Map<?,?>) map.get("area");
        String areaId = (String) areaMap.get("id");
        String areaName = (String) areaMap.get("name");
        LinkedHashMap<String,String> area = new LinkedHashMap<>();
        area.put("id",areaId);
        area.put("name",areaName);

        return new EmployerDto(id, name, description, area);
    }

}
