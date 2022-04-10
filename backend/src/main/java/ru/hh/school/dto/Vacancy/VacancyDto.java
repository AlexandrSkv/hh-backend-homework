package ru.hh.school.dto.Vacancy;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;

@JsonPropertyOrder({ "id", "name", "area", "salary", "createdAt", "employer" })
public class VacancyDto {

    private Integer id;
    private String name;
    private HashMap<String,String> area;
    private HashMap<String, Object> salary;
    private String created_at;
    private Integer employer;

    public VacancyDto(Integer id,
                      String name,
                      HashMap<String,String> area,
                      HashMap<String,Object> salary,
                      String created_at,
                      Integer employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.created_at = created_at;
        this.employer = employer;
    }

    public HashMap<String, Object> getSalary() {
        return salary;
    }

    public void setSalary(HashMap<String, Object> salary) {
        this.salary = salary;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String createdAt) {
        this.created_at = createdAt;
    }

    public Integer getEmployer() {
        return employer;
    }

    public void setEmployer(Integer employer) {
        this.employer = employer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArea(HashMap<String,String> area){this.area = area; }

    public HashMap<String,String> getArea () { return area; }


}
