package ru.hh.checkly.dto;

import ru.hh.checkly.entity.RecommendationFormStatus;
import java.time.LocalDateTime;

public class RecommendationFormDto {

  private LocalDateTime creationTime;
  private LocalDateTime updateTime;
  private String candidateFirstName;
  private String candidateLastName;
  private RecommendationFormStatus status;

  public RecommendationFormDto(LocalDateTime creationTime,
                               LocalDateTime updateTime,
                               String candidateFirstName,
                               String candidateLastName,
                               RecommendationFormStatus status) {

    this.creationTime = creationTime;
    this.updateTime = updateTime;
    this.candidateFirstName = candidateFirstName;
    this.candidateLastName = candidateLastName;
    this.status = status;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
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

  public RecommendationFormStatus getStatus() {
    return status;
  }

  public void setStatus(RecommendationFormStatus status) {
    this.status = status;
  }

}
