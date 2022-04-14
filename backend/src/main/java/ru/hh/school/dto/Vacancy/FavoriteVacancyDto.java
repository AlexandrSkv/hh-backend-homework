package ru.hh.school.dto.Vacancy;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.hh.school.dto.Area;
import ru.hh.school.dto.Employer.EmployerShortDto;
import ru.hh.school.dto.Salary;

@JsonPropertyOrder({ "id", "name", "data_create", "area", "salary", "created_at", "employer", "popularity", "views_count", "comment"})
public class FavoriteVacancyDto {

    private String id;
    private String name;
    private String data_create;
    private Area area;
    private Salary salary;
    private String created_at;
    private EmployerShortDto employerShortDto;
    private String popularity;
    private Integer views_count;
    private String comment;

    public FavoriteVacancyDto() {}

    public FavoriteVacancyDto(String id,
                              String name,
                              String data_create,
                              Area area,
                              Salary salary,
                              String created_at,
                              EmployerShortDto employerShortDto,
                              Integer views_count,
                              String comment,
                              Integer popularRate) {
        this.id = id;
        this.name = name;
        this.data_create = data_create;
        this.area = area;
        this.salary = salary;
        this.created_at = created_at;
        this.employerShortDto = employerShortDto;
        this.comment = comment;
        this.views_count = views_count;
        if (views_count < popularRate) {
            this.popularity = "REGULAR";
        }
        else {
            this.popularity = "POPULAR";
        }
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getData_create() { return data_create; }

    public void setData_create(String data_create) { this.data_create = data_create; }

    public Area getArea() { return area; }

    public void setArea(Area area) { this.area = area; }

    public Salary getSalary() { return salary; }

    public void setSalary(Salary salary) { this.salary = salary; }

    public String getCreated_at() { return created_at; }

    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public EmployerShortDto getEmployer() { return employerShortDto; }

    public void setEmployer(EmployerShortDto employerShortDto) { this.employerShortDto = employerShortDto; }

    public String getPopularity() { return popularity; }

    public void setPopularity(String popularity) { this.popularity = popularity; }

    public Integer getViews_count() { return views_count; }

    public void setViews_count(Integer views_count) { this.views_count = views_count; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

}
