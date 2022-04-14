package ru.hh.school.dto.Vacancy;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.hh.school.dto.Area;
import ru.hh.school.dto.Employer.EmployerShortDto;
import ru.hh.school.dto.Salary;

@JsonPropertyOrder({ "id", "name", "area", "salary", "created_at", "employer" })
public class VacancyDto {

    private String id;
    private String name;
    private Area area;
    private Salary salary;
    private String created_at;
    private EmployerShortDto employer;

    public VacancyDto() {}

    public VacancyDto(String id,
                      String name,
                      Area area,
                      Salary salary,
                      String created_at,
                      EmployerShortDto employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.created_at = created_at;
        this.employer = employer;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Area getArea() { return area; }

    public void setArea(Area area) { this.area = area; }

    public Salary getSalary() { return salary; }

    public void setSalary(Salary salary) { this.salary = salary; }

    public String getCreated_at() { return created_at; }

    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public EmployerShortDto getEmployer() { return employer; }

    public void setEmployer(EmployerShortDto employer) { this.employer = employer; }

}
