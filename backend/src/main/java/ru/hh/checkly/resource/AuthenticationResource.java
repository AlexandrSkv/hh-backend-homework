package ru.hh.checkly.resource;

import ru.hh.checkly.dto.Credentials;
import ru.hh.checkly.service.security.AuthenticationService;
import ru.hh.checkly.service.security.Secured;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class AuthenticationResource {

  private final AuthenticationService authenticationService;

  @Inject
  public AuthenticationResource(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response authenticateUser(Credentials credentials) {
    return authenticationService.getToken(credentials)
      .map(Response::ok)
      .map(Response.ResponseBuilder::build)
      .orElse(Response
        .status(Response.Status.FORBIDDEN)
        .entity("Provide valid username and/or password!")
        .build());
  }

}
