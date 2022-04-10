package ru.hh.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacancy")
public class FavoriteVacancyEntity {

    @Id
    @Column(name = "vacancy_id")
    private Integer id;

    @Column(name = "vacancy_name")
    private String name;

    @Column(name = "data_create")
    private LocalDateTime data_create;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "areaName")
    private String areaName;

    @Column(name = "salary_from")
    private Integer salary_from;

    @Column(name = "salary_to")
    private Integer salary_to;

    @Column(name = "salary_currency")
    private String salary_currency;

    @Column(name = "salary_gross")
    private Boolean salary_gross;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "employer")
    private Integer employer;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer views_count;

    public FavoriteVacancyEntity() {
    }

    public FavoriteVacancyEntity(Integer id,
                                 String name,
                                 LocalDateTime data_create,
                                 Integer areaId,
                                 String areaName,
                                 Integer salary_from,
                                 Integer salary_to,
                                 String salary_currency,
                                 Boolean salary_gross,
                                 String created_at,
                                 String comment,
                                 Integer views_count) {
        this.id = id;
        this.name = name;
        this.data_create = data_create;
        this.areaId = areaId;
        this.areaName = areaName;
        this.salary_from = salary_from;
        this.salary_to = salary_to;
        this.salary_currency = salary_currency;
        this.salary_gross = salary_gross;
        this.created_at = created_at;
        this.comment = comment;
        this.views_count = views_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getData_create() {
        return data_create;
    }

    public void setData_create(LocalDateTime data_create) {
        this.data_create = data_create;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getSalary_from() {
        return salary_from;
    }

    public void setSalary_from(Integer salary_from) {
        this.salary_from = salary_from;
    }

    public Integer getSalary_to() {
        return salary_to;
    }

    public void setSalary_to(Integer salary_to) {
        this.salary_to = salary_to;
    }

    public String getSalary_currency() {
        return salary_currency;
    }

    public void setSalary_currency(String salary_currency) {
        this.salary_currency = salary_currency;
    }

    public Boolean getSalary_gross() {
        return salary_gross;
    }

    public void setSalary_gross(Boolean salary_gross) {
        this.salary_gross = salary_gross;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getViews_count() {
        return views_count;
    }

    public void setViews_count(Integer views_count) {
        this.views_count = views_count;
    }

    public Integer getEmployer() {
        return employer;
    }

    public void setEmployer(Integer employer) {
        this.employer = employer;
    }
}
