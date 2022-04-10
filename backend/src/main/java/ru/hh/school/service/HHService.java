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
import ru.hh.school.dto.Employer.EmployerListDto;

import javax.ws.rs.Produces;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HHServis {

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
    public String getEmployer(Integer employerId) {

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

        return response;
    }

    @Produces("application/json; charset=UTF-8")
    public String getVacancies(String text, Integer page, Integer per_page) {

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

        return response;
    }

    @Produces("application/json; charset=UTF-8")
    public String getVacancy(Integer vacancyId) {

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

        return response;
    }

}
