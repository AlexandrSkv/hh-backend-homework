package ru.hh.checkly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.checkly.dao.PersonDao;
import ru.hh.checkly.entity.Person;
import ru.hh.checkly.resource.AuthenticationResource;
import ru.hh.checkly.resource.ExampleResource;
import ru.hh.checkly.service.security.AuthenticationService;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;

@Configuration
@Import({
  // import your beans here
  ExampleResource.class,
  NabCommonConfig.class,
  AuthenticationResource.class,
  AuthenticationService.class,
  MailConfig.class,
  PersonDao.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addEntityClass(Person.class);
    return mappingConfig;
  }
}
