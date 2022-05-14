package ru.hh.checkly;

import ru.hh.checkly.service.security.AuthenticationFilter;
import ru.hh.nab.starter.NabApplication;
import ru.hh.checkly.config.ProdConfig;

public class App {

  public static void main(String[] args) {
    NabApplication
            .builder()
            .configureJersey()
            .registerResources(AuthenticationFilter.class)
            .bindToRoot()
            .build()
            .run(ProdConfig.class);
  }
}
