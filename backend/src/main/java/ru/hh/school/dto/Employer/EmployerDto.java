package ru.hh.school.dto.Employer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.hh.school.dto.Area;

@JsonPropertyOrder({ "id", "name", "description", "area" })
public class EmployerDto {

    private String id;
    private String name;
    private String description;
    private Area area;

    public EmployerDto() {}

    public EmployerDto(String id, String name, String description, Area area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
