package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "vacancy")
public class FavoriteVacancyEntity {

    @Id
    @Column(name = "vacancy_id")
    private String id;

    @Column(name = "vacancy_name")
    private String name;

    @Column(name = "data_create")
    private String data_create;

    @Column(name = "area_id")
    private String areaId;

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

    @Column(name = "employer_id")
    private String employer_id;

    @Column(name = "employer_name")
    private String employer_name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer views_count;

    public FavoriteVacancyEntity() {
    }

    public FavoriteVacancyEntity(String id,
                                 String name,
                                 String areaId,
                                 String areaName,
                                 Integer salary_from,
                                 Integer salary_to,
                                 String salary_currency,
                                 Boolean salary_gross,
                                 String created_at,
                                 String comment,
                                 String employer_id,
                                 String employer_name)
    {
        this.id = id;
        this.name = name;
        this.data_create = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.areaId = areaId;
        this.areaName = areaName;
        this.salary_from = salary_from;
        this.salary_to = salary_to;
        this.salary_currency = salary_currency;
        this.salary_gross = salary_gross;
        this.created_at = created_at;
        this.comment = comment;
        this.employer_id = employer_id;
        this.employer_name = employer_name;
        this.views_count = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData_create() {
        return data_create;
    }

    public void setData_create(String data_create) {
        this.data_create = data_create;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
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

    public String getEmployer_id() { return employer_id; }

    public void setEmployer_id(String employer_id) { this.employer_id = employer_id; }

    public String getEmployer_name() { return employer_name; }

    public void setEmployer_name(String employer_name) { this.employer_name = employer_name; }
}
