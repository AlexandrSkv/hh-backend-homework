package ru.hh.school.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.resource.Employer.EmployerResource;
import ru.hh.school.resource.Employer.FavoriteEmployerResource;
import ru.hh.school.resource.Vacancy.FavoriteVacancyResource;
import ru.hh.school.resource.Vacancy.VacancyResource;
import ru.hh.school.service.Employer.EmployerService;
import ru.hh.school.service.Employer.FavoriteEmployerService;
import ru.hh.school.service.HHService;
import ru.hh.school.service.Vacancy.FavoriteVacancyService;
import ru.hh.school.service.Vacancy.VacancyService;

@Configuration
@Import({
  // import your beans here
  HHService.class,
  EmployerService.class,
  VacancyService.class,
  EmployerResource.class,
  VacancyResource.class,
  FavoriteEmployerResource.class,
  FavoriteVacancyResource.class,
  FavoriteEmployerService.class,
  FavoriteVacancyService.class,
  EmployerDao.class,
  VacancyDao.class,
  NabCommonConfig.class
})
public class CommonConfig {

  @Value("${popular_rate}")
  private String popularRate;

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
