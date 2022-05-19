package ru.hh.checkly.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "recruiter")
@PrimaryKeyJoinColumn(name = "recruiter_id")
public class Recruiter extends Person {

  @Column(name = "company_name", nullable = false)
  private String companyName;

  @OneToMany(mappedBy = "recruiter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<RecommendationForm> recommendationForms;

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Set<RecommendationForm> getRecommendationForms() {
    return recommendationForms;
  }

  public void setRecommendationForms(Set<RecommendationForm> recommendationForms) {
    this.recommendationForms = recommendationForms;
  }

}
