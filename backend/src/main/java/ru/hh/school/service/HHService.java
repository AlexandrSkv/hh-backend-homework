package ru.hh.school.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.EmployerShortDto;
import ru.hh.school.dto.Vacancy.VacancyDto;
import javax.ws.rs.Produces;
import java.util.ArrayList;

public class HHService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    private final CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

    public HHService (){
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Produces("application/json; charset=UTF-8")
    public ArrayList<EmployerShortDto> getEmployers(String text,
                                                    Integer page,
                                                    Integer per_page) throws JsonProcessingException {

        String url = "http://api.hh.ru/employers";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("text", text)
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .build(false)
                .toUriString();

        String response = restTemplate.getForObject(urlTemplate , String.class);
        JsonNode arrNode = mapper.readTree(response).get("items");

        ArrayList<EmployerShortDto> employerShortDto = new ArrayList<>();

        for (final JsonNode objNode : arrNode) {
            employerShortDto.add(mapper.readValue(objNode.toString(), EmployerShortDto.class));
        }

        return employerShortDto;
    }

    @Produces("application/json; charset=UTF-8")
    public EmployerDto getEmployer(String employerId) throws JsonProcessingException {

        String url = "http://api.hh.ru/employers/{employerId}";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(employerId)
                .toUriString();

        String response = restTemplate.getForObject(urlTemplate, String.class);

        return mapper.readValue(response, EmployerDto.class);
    }

    @Produces("application/json; charset=UTF-8")
    public ArrayList<VacancyDto> getVacancies(String text, Integer page, Integer per_page) throws JsonProcessingException {

        String url = "http://api.hh.ru/vacancies";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("text",  text)
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .build(false)
                .toUriString();

        String response = restTemplate.getForObject(urlTemplate, String.class);

        JsonNode arrNode = mapper.readTree(response).get("items");
        ArrayList<VacancyDto> vacancyListDto = new ArrayList<>();

        for (final JsonNode objNode : arrNode) {
            vacancyListDto.add(mapper.readValue(objNode.toString(),VacancyDto.class));
        }

        return vacancyListDto;
    }

    @Produces("application/json; charset=UTF-8")
    public VacancyDto getVacancy(String vacancyId) throws JsonProcessingException {

        String url = "http://api.hh.ru/vacancies/{vacancyId}";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(vacancyId)
                .toUriString();

        String response = restTemplate.getForObject(urlTemplate, String.class);

        return mapper.readValue(response, VacancyDto.class);
    }

}
