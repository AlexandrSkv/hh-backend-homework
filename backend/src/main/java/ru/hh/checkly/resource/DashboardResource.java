package ru.hh.checkly.resource;

import ru.hh.checkly.service.DashboardService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dashboard")
public class DashboardResource {

  private final DashboardService dashboardService;

  @Inject
  public DashboardResource(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GET
  @Path(value = "/{recruiterId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDashboard(@PathParam(value = "recruiterId") Long id) {
    if (id == null || id < 1) {
      throw new IllegalArgumentException("recruiterId is invalid");
    }

    return dashboardService.getDashboard(id)
        .map(Response::ok)
        .map(Response.ResponseBuilder::build)
        .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());

  }

}
