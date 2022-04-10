package ru.hh.school.dto.Vacancy;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@JsonPropertyOrder({ "id", "name", "data_create", "area", "salary", "created_at", "employer", "popularity", "views_count", "comment"})
public class FavoriteVacancyDto {
    private Integer id;
    private String name;
    private String data_create;
    private LinkedHashMap<String,String> area = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> salary = new LinkedHashMap<>();
    private String created_at;
    private Integer employer;
    private String popularity;
    private Integer views_count;
    private String comment;

    public FavoriteVacancyDto(Integer id,
                              String name,
                              LocalDateTime data_create,
                              Integer areaId,
                              String areaName,
                              Integer salaryFrom,
                              Integer salaryTo,
                              String salaryCurrency,
                              Boolean salaryGross,
                              String created_at,
                              Integer employer,
                              Integer views_count,
                              String comment,
                              Integer popularRate) {
        this.id = id;
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.data_create = data_create.format(formatter);

        this.area.put("id",String.valueOf(areaId));
        this.area.put("name",areaName);

        this.salary.put("from",salaryFrom);
        this.salary.put("to",salaryTo);
        this.salary.put("currency",salaryCurrency);
        this.salary.put("gross",salaryGross);

        this.created_at = created_at;
        this.employer = employer;
        this.comment = comment;
        this.views_count = views_count;
        if (views_count < popularRate) {
            this.popularity = "REGULAR";
        }
        else {
            this.popularity = "POPULAR";
        }
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

    public String getData_create() {
        return data_create;
    }

    public void setData_create(String data_create) {
        this.data_create = data_create;
    }

    public LinkedHashMap<String, String> getArea() {
        return area;
    }

    public void setArea(LinkedHashMap<String, String> area) {
        this.area = area;
    }

    public LinkedHashMap<String, Object> getSalary() {
        return salary;
    }

    public void setSalary(LinkedHashMap<String, Object> salary) {
        this.salary = salary;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getEmployer() {
        return employer;
    }

    public void setEmployer(Integer employer) {
        this.employer = employer;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Integer getViews_count() {
        return views_count;
    }

    public void setViews_count(Integer views_count) {
        this.views_count = views_count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
