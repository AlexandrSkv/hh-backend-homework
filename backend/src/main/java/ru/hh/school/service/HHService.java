package ru.hh.school.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.EmployerListDto;
import ru.hh.school.dto.Vacancy.VacancyDto;

import javax.ws.rs.Produces;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class HHService {

    @Produces("application/json; charset=UTF-8")
    public ArrayList<EmployerListDto> getEmployers(String text,
                                                   Integer page,
                                                   Integer per_page) throws JsonProcessingException {

        String url = "http://api.hh.ru/employers";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("text", URLEncoder.encode(text, StandardCharsets.UTF_8))
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        String response = restTemplate.getForObject(urlTemplate, String.class);
        ArrayList<EmployerListDto> employerListDto = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode arrNode = mapper.readTree(response).get("items");

        for (final JsonNode objNode : arrNode) {
            employerListDto.add(new EmployerListDto(Integer.parseInt(objNode.get("id").asText()),
                    objNode.get("name").asText()));
        }

        return employerListDto;

    }

    @Produces("application/json; charset=UTF-8")
    public EmployerDto getEmployer(Integer employerId) throws JsonProcessingException {

        String url = "http://api.hh.ru/employers/{employerId}";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(employerId)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        String response = restTemplate.getForObject(urlTemplate, String.class);
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

    @Produces("application/json; charset=UTF-8")
    public ArrayList<VacancyDto> getVacancies(String text, Integer page, Integer per_page) throws JsonProcessingException {

        String url = "http://api.hh.ru/vacancies";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("text",  URLEncoder.encode(text, StandardCharsets.UTF_8))
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        String response = restTemplate.getForObject(urlTemplate, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode arrNode = mapper.readTree(response).get("items");
        ArrayList<VacancyDto> vacancyListDto = new ArrayList<>();

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
            Integer employer = Integer.parseInt((String) employerMap.get("id"));

            vacancyListDto.add(new VacancyDto(id, name, area, salary, createdAt, employer));

        }

        return vacancyListDto;
    }

    @Produces("application/json; charset=UTF-8")
    public VacancyDto getVacancy(Integer vacancyId) throws JsonProcessingException {

        String url = "http://api.hh.ru/vacancies/{vacancyId}";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(vacancyId)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        String response = restTemplate.getForObject(urlTemplate, String.class);

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
        Integer employer = Integer.parseInt((String) employerMap.get("id"));

        return new VacancyDto(id, name, area, salary,createdAt, employer);
    }


}
