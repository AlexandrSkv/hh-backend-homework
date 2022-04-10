package ru.hh.school.resource.Vacancy;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.hh.school.dto.Vacancy.VacancyDto;
import ru.hh.school.service.Vacancy.VacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/vacancy")
public class VacancyResource {

    private final VacancyService vacancyService;

    @Inject
    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    public Response getVacancies(
            @DefaultValue("")@QueryParam(value = "text") String text,
            @DefaultValue("0")@QueryParam(value = "page") Integer page,
            @DefaultValue("20")@QueryParam(value = "per_page") Integer per_page) throws JsonProcessingException {

        List<VacancyDto> vacancyListDto = vacancyService.getVacancyList(text,page,per_page);
        return Response.ok(vacancyListDto).build();
    }

    @GET
    @Path(value = "/{vacancyId}")
    @Produces("application/json; charset=UTF-8")
    public Response getVacancyById(@PathParam(value = "vacancyId") Integer vacancyId) throws JsonProcessingException {
        VacancyDto vacancyDto = vacancyService.getVacancy(vacancyId);
        return Response.ok(vacancyDto).build();
    }

}
