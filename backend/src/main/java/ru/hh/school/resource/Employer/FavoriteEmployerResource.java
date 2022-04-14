package ru.hh.school.resource.Employer;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.hh.school.dto.Employer.FavoriteEmployerDto;
import ru.hh.school.service.Employer.EmployerService;
import ru.hh.school.service.Employer.FavoriteEmployerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Singleton
@Path("/favorites")
public class FavoriteEmployerResource {

    private final FavoriteEmployerService favoriteEmployerService;

    @Inject
    public FavoriteEmployerResource(FavoriteEmployerService favoriteEmployerService, EmployerService employerService) {
        this.favoriteEmployerService = favoriteEmployerService;
    }

    @POST
    @Path(value = "/employer")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response addFavoriteEmployer(@Context HttpHeaders headers) throws JsonProcessingException {
        String employerId = headers.getHeaderString("employer_id");
        String comment = headers.getHeaderString("comment");

        favoriteEmployerService.addFavoriteEmployer(employerId,comment);
        return Response.ok().build();
    }

    @GET
    @Path(value = "/employer")
    @Produces("application/json; charset=UTF-8")
    public Response getFavoriteEmployer(
            @DefaultValue("0")@QueryParam(value = "page") Integer page,
            @DefaultValue("20")@QueryParam(value = "per_page") Integer per_page) {

        ArrayList<FavoriteEmployerDto> employerListDto = favoriteEmployerService.getFavoriteEmployers(page, per_page);

        return Response.ok(employerListDto).build();
    }

    @PUT
    @Path(value = "employer/{employer_id}")
    @Produces("application/json; charset=UTF-8")
    public Response updateFavoriteEmployer(@Context HttpHeaders headers,
                                           @PathParam(value = "employer_id") String employer_id) {
        String comment = headers.getHeaderString("comment");
        favoriteEmployerService.updateFavoriteEmployer(employer_id, comment);
        return Response.ok().build();
    }

    @DELETE
    @Path(value = "employer/{employer_id}")
    @Produces("application/json; charset=UTF-8")
    public Response deleteFavoriteEmployer(@PathParam(value = "employer_id") String employer_id) {
        favoriteEmployerService.deleteFavoriteEmployer(employer_id);
        return Response.ok().build();
    }

    @POST
    @Path(value = "employer/{employer_id}/refresh")
    @Produces("application/json; charset=UTF-8")
    public Response refreshFavoriteEmployer(@PathParam(value = "employer_id") String employer_id) throws JsonProcessingException {
        favoriteEmployerService.refreshFavoriteEmployer(employer_id);
        return Response.ok().build();
    }

}
