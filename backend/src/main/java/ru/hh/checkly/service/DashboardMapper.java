package ru.hh.checkly.service;

import ru.hh.checkly.dto.RecommendationFormDto;
import ru.hh.checkly.entity.RecommendationForm;

public class DashboardMapper {
  public static RecommendationFormDto mapToDashboard(RecommendationForm recommendationForm) {

    return new RecommendationFormDto(
            recommendationForm.getCreationTime(),
            recommendationForm.getUpdateTime(),
            recommendationForm.getCandidateFirstName(),
            recommendationForm.getCandidateLastName(),
            recommendationForm.getStatus());
  }

}
