package ru.hh.school.dto.Employer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.hh.school.dto.Area;

@JsonPropertyOrder({ "id", "name", "data_create", "description", "area", "comment", "popularity", "views_count" })
public class FavoriteEmployerDto {

    private String id;
    private String name;
    private String data_create;
    private String description;
    private Area area;
    private String comment;
    private Integer views_count;
    private String popularity;

    public FavoriteEmployerDto(){

    }

    public FavoriteEmployerDto(
            String id,
            String name,
            String description,
            Area area,
            String data_create,
            String comment,
            Integer views_count,
            Integer popularRate)
    {
        this.id = id;
        this.name = name;
        this.description= description;
        this.area = area;
        this.data_create = data_create;
        this.comment = comment;
        this.views_count = views_count;
        if (views_count < popularRate) {
            this.popularity = "REGULAR";
        }
        else {
            this.popularity = "POPULAR";
        }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) { this.area = area; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public Integer getViews_count() { return views_count; }

    public void setViews_count(Integer views_count) { this.views_count = views_count; }

    public String getPopularity() { return popularity; }

    public void setPopularity(String popularity) { this.popularity = popularity; }

}
