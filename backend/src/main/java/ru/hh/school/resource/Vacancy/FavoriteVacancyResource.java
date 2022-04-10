package ru.hh.school.resource.Vacancy;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.hh.school.dto.Vacancy.FavoriteVacancyDto;
import ru.hh.school.service.Vacancy.FavoriteVacancyService;
import ru.hh.school.service.Vacancy.VacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Singleton
@Path("/favorites")
public class FavoriteVacancyResource {

    private final FavoriteVacancyService favoriteVacancyService;

    @Inject
    public FavoriteVacancyResource(FavoriteVacancyService favoriteVacancyService, VacancyService vacancyService) {
        this.favoriteVacancyService = favoriteVacancyService;
    }

    @POST
    @Path(value = "/vacancy")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response addFavoriteVacancy(@Context HttpHeaders headers) throws JsonProcessingException {
        String vacancyId = headers.getHeaderString("vacancy_id");
        String comment = headers.getHeaderString("comment");

        favoriteVacancyService.addFavoriteVacancy(Integer.parseInt(vacancyId),comment);
        return Response.ok().build();
    }

    @GET
    @Path(value = "/vacancy")
    @Produces("application/json; charset=UTF-8")
    public Response getFavoriteVacancy(
            @DefaultValue("0")@QueryParam(value = "page") Integer page,
            @DefaultValue("20")@QueryParam(value = "per_page") Integer per_page) {

        ArrayList<FavoriteVacancyDto> vacancyListDto = favoriteVacancyService.getFavoriteVacancies(page, per_page);

        return Response.ok(vacancyListDto).build();
    }

    @DELETE
    @Path(value = "vacancy/{vacancy_id}")
    @Produces("application/json; charset=UTF-8")
    public Response deleteFavoriteVacancy(@PathParam(value = "vacancy_id") Integer vacancy_id) {
        favoriteVacancyService.deleteFavoriteVacancy(vacancy_id);
        return Response.ok().build();
    }

    @POST
    @Path(value = "vacancy/{vacancy_id}/vacancy")
    @Produces("application/json; charset=UTF-8")
    public Response refreshFavoriteVacancy(@PathParam(value = "vacancy_id") Integer vacancy_id) throws JsonProcessingException {
        favoriteVacancyService.refreshFavoriteVacancy(vacancy_id);
        return Response.ok().build();
    }

}
