package ru.hh.checkly.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recommendation_form_question")
public class RecommendationFormQuestion {
  @Id
  @Column(name = "recommendation_form_question_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recommendationFormQuestionId;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "recommendation_form_id", referencedColumnName = "recommendation_form_id", nullable = false)
  private RecommendationForm recommendationForm;

  @Column(name = "question", nullable = false)
  private String question;

  @Column(name = "answer")
  private String answer;

  public Long getId() {
    return recommendationFormQuestionId;
  }

  public void setId(Long id) {
    this.recommendationFormQuestionId = id;
  }

  public RecommendationForm getRecommendationFormId() {
    return recommendationForm;
  }

  public void setRecommendationFormId(RecommendationForm recommendationForm) {
    this.recommendationForm = recommendationForm;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
