package ru.hh.school.dto.Employer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.LinkedHashMap;

@JsonPropertyOrder({ "id", "name", "description", "area" })
public class EmployerDto {

    private Integer id;
    private String name;
    private String description;
    private LinkedHashMap<String,String> area;

    public EmployerDto(Integer id, String name, String description, LinkedHashMap<String,String> area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setArea(LinkedHashMap<String,String> area){this.area = area; }

    public LinkedHashMap<String,String> getArea () { return area; }


}
