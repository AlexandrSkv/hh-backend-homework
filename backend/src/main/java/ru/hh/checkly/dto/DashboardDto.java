package ru.hh.checkly.dto;

import java.util.Set;

public class DashboardDto {

  private Set<RecommendationFormDto> recommendationForms;

  public DashboardDto(Set<RecommendationFormDto> recommendationForms) {
    this.recommendationForms = recommendationForms;

  }

  public Set<RecommendationFormDto> getRecommendationForms() {
    return recommendationForms;
  }

  public void setRecommendationForms(Set<RecommendationFormDto> recommendationForms) {
    this.recommendationForms = recommendationForms;
  }
}
