package ru.hh.checkly.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import ru.hh.nab.common.properties.FileSettings;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
  private final String authenticationScheme;
  private final String secretKey;

  @Inject
  public AuthenticationFilter(FileSettings fileSettings) {
    this.secretKey = fileSettings.getString("login.secretKey");
    this.authenticationScheme = fileSettings.getString("login.authenticationScheme");
  }

  @Override
  public void filter(ContainerRequestContext requestContext) {

    String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

    if (!isTokenBasedAuthentication(authorizationHeader) ||
      !validateToken(authorizationHeader.substring(authenticationScheme.length()).trim())) {
      abortWithUnauthorized(requestContext);
    }

  }

  private boolean isTokenBasedAuthentication(String authorizationHeader) {
    return authorizationHeader != null &&
      authorizationHeader
        .toLowerCase()
        .startsWith(authenticationScheme.toLowerCase() + " ");
  }

  private void abortWithUnauthorized(ContainerRequestContext requestContext) {
    requestContext.abortWith(
      Response.status(Response.Status.UNAUTHORIZED)
        .header(HttpHeaders.WWW_AUTHENTICATE, authenticationScheme)
        .entity("Provide valid token!")
        .build());
  }

  private boolean validateToken(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
        .build()
        .parseClaimsJws(token)
        .getBody();
      return claims.getExpiration().after(Date.from(Instant.now()));
    } catch (JwtException e) {
      return false;
    }
  }

}
