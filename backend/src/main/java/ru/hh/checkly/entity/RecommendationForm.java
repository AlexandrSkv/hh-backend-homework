package ru.hh.checkly.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation_form")
public class RecommendationForm {
  @Id
  @Column(name = "recommendation_form_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recommendationFormId;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "recruiter_id", referencedColumnName = "recruiter_id", nullable = false)
  private Recruiter recruiter;

  @Column(name = "creation_time")
  private LocalDateTime creationTime;

  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private RecommendationFormStatus status;

  @Column(name = "candidate_first_name", nullable = false)
  private String candidateFirstName;

  @Column(name = "candidate_last_name", nullable = false)
  private String candidateLastName;

  @Column(name = "recommender_email", nullable = false)
  private String recommenderEmail;

  public Long getRecommendationFormId() {
    return recommendationFormId;
  }

  public void setRecommendationFormId(Long recommendationFormId) {
    this.recommendationFormId = recommendationFormId;
  }

  public Recruiter getRecruiter() {
    return recruiter;
  }

  public void setRecruiter(Recruiter recruiter) {
    this.recruiter = recruiter;
  }

  public RecommendationFormStatus getStatus() {
    return status;
  }

  public void setStatus(RecommendationFormStatus status) {
    this.status = status;
  }

  public String getCandidateFirstName() {
    return candidateFirstName;
  }

  public void setCandidateFirstName(String candidateFirstName) {
    this.candidateFirstName = candidateFirstName;
  }

  public String getCandidateLastName() {
    return candidateLastName;
  }

  public void setCandidateLastName(String candidateLastName) {
    this.candidateLastName = candidateLastName;
  }

  public String getRecommenderEmail() {
    return recommenderEmail;
  }

  public void setRecommenderEmail(String recommenderEmail) {
    this.recommenderEmail = recommenderEmail;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }
}
