package ru.hh.school.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "employer")
public class FavoriteEmployerEntity {

    @Id
    @Column(name = "employer_id")
    private String id;

    @Column(name = "employer_name")
    private String name;

    @Column(name = "data_create")
    private String data_create;

    @Column(name = "description")
    private String description;

    @Column(name = "area_id")
    private String areaId;

    @Column(name = "areaName")
    private String areaName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer views_count;

    public FavoriteEmployerEntity() {
    }

    public FavoriteEmployerEntity(String id,
                                  String name,
                                  String description,
                                  String areaId,
                                  String areaName,
                                  String comment) {
        this.id = id;
        this.name = name;
        this.data_create = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.description = description;;
        this.areaId = areaId;
        this.areaName = areaName;
        this.comment = comment;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
