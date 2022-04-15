package ru.hh.school.resource.Employer;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.hh.school.dto.Employer.EmployerDto;
import ru.hh.school.dto.Employer.EmployerShortDto;
import ru.hh.school.service.Employer.EmployerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/employer")
public class EmployerResource {

    private final EmployerService employerService;

    @Inject
    public EmployerResource(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    public Response getEmployers(
            @DefaultValue("")@QueryParam(value = "text") String text,
            @DefaultValue("0")@QueryParam(value = "page") Integer page,
            @DefaultValue("20")@QueryParam(value = "per_page") Integer per_page) throws JsonProcessingException {

        List<EmployerShortDto> employerShortDto = employerService.getEmployerList(text,page,per_page);
        return Response.ok(employerShortDto).build();
    }

    @GET
    @Path(value = "/{employerId}")
    @Produces("application/json; charset=UTF-8")
    public Response getEmployerById(@PathParam(value = "employerId") String employerId) throws JsonProcessingException {
        EmployerDto employerDto = employerService.getEmployer(employerId);
        return Response.ok(employerDto).build();
    }

}
