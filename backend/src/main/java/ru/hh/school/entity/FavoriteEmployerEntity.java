package ru.hh.school.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employer")
public class FavoriteEmployerEntity {

    @Id
    @Column(name = "employer_id")
    private Integer id;

    @Column(name = "employer_name")
    private String name;

    @Column(name = "data_create")
    private LocalDateTime data_create;

    @Column(name = "description")
    private String description;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "areaName")
    private String areaName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer views_count;

    public FavoriteEmployerEntity() {
    }

    public FavoriteEmployerEntity(Integer id,
                                  String name,
                                  LocalDateTime data_create,
                                  String description,
                                  Integer areaId,
                                  String areaName,
                                  String comment,
                                  Integer views_count) {
        this.id = id;
        this.name = name;
        this.data_create = data_create;
        this.description = description;;
        this.areaId = areaId;
        this.areaName = areaName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
