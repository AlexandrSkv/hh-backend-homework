package ru.hh.checkly.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "recruiter")
public class Recruiter {
  @Id
  @Column(name = "recruiter_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recruiterId;

  @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
  private Set<RecommendationForm> recommendationForms;

  public Long getId() {
        return recruiterId;
    }
  public void setId(Long id) {
        this.recruiterId = id;
    }

  public Set<RecommendationForm> getRecommendationForms() {
        return recommendationForms;
    }
  public void setRecommendationForms(Set<RecommendationForm> recommendationForms) {
        this.recommendationForms = recommendationForms;
    }
}
