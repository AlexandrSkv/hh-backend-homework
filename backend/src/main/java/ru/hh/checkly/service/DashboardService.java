package ru.hh.checkly.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.checkly.dao.RecruiterDao;
import ru.hh.checkly.dto.DashboardDto;
import ru.hh.checkly.dto.RecommendationFormDto;
import ru.hh.checkly.entity.RecommendationForm;
import ru.hh.checkly.entity.Recruiter;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DashboardService {

  private final RecruiterDao recruiterDao;

  @Inject
  public DashboardService(RecruiterDao recruiterDao) {
    this.recruiterDao = recruiterDao;
  }

  @Transactional
  public Optional<DashboardDto> getDashboard(Long recruiterId) {

    Optional<Recruiter> recruiter = recruiterDao.getRecruiterById(recruiterId);

    Optional<DashboardDto> dashboardDto = recruiter
        .map(Recruiter::getRecommendationForms)
        .map(recommendationFormSet -> recommendationFormSet.stream()
            .map(DashboardMapper::mapToDashboard)
            .collect(Collectors.toSet()))
        .map(recommendationFormDtoSet -> new DashboardDto(recommendationFormDtoSet));

    return dashboardDto;

  }

}
