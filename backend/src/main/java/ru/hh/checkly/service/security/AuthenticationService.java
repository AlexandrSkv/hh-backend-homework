package ru.hh.checkly.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import ru.hh.checkly.dao.PersonDao;
import ru.hh.checkly.dto.Credentials;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.hibernate.transaction.TransactionalScope;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class AuthenticationService {

  private final String authenticationScheme;
  private final String secretKey;
  private final int tokenExpirationTimeHours;

  private final PersonDao personDao;
  private final TransactionalScope transactionalScope;

  @Inject
  public AuthenticationService(PersonDao personDao, TransactionalScope transactionalScope, FileSettings fileSettings) {
    this.personDao = personDao;
    this.transactionalScope = transactionalScope;
    this.secretKey = fileSettings.getString("login.secretKey");
    this.tokenExpirationTimeHours = fileSettings.getInteger("login.tokenExpirationTimeInHours");
    this.authenticationScheme = fileSettings.getString("login.authenticationScheme");
  }

  public Optional<String> getToken(Credentials credentials) {
    if (credentials == null ||
      credentials.getEmail() == null ||
      credentials.getPassword() == null ||
      !authenticate(credentials.getEmail(), credentials.getPassword())
    ) {
      return Optional.empty();
    }
    return Optional.of(issueToken(credentials.getEmail()));
  }

  private boolean authenticate(String email, String password) {
    return transactionalScope.read(() -> personDao.checkIfPersonExists(email, password));
  }

  private String issueToken(String email) {
    return authenticationScheme + " " + Jwts.builder()
      .setSubject(email)
      .setIssuedAt(Date.from(Instant.now()))
      .setExpiration(Date.from(Instant.now().plus(tokenExpirationTimeHours, ChronoUnit.HOURS)))
      .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
      .compact();
  }

}
