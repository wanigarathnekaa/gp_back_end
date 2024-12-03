package com.example.gp_back_end.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cloaks")
public class CloakModel {

    @Id
    private String id;
    private String name;
    private int smallCount;
    private int mediumCount;
    private int largeCount;

    public CloakModel(){};

    public CloakModel(String name, int smallCount, int mediumCount, int largeCount){
        this.name = name;
        this.smallCount = smallCount;
        this.mediumCount = mediumCount;
        this.largeCount = largeCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSmallCount() {
        return smallCount;
    }

    public int getMediumCount() {
        return mediumCount;
    }

    public int getLargeCount() {
        return largeCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSmallCount(int smallCount) {
        this.smallCount = smallCount;
    }

    public void setMediumCount(int mediumCount) {
        this.mediumCount = mediumCount;
    }

    public void setLargeCount(int largeCount) {
        this.largeCount = largeCount;
    }

    @Override
    public String toString() {
        return "CloakModel{" +
                "id='" + id + '\'' +
                "name='" + name + '\''+
                ", smallCount=" + smallCount +
                ", mediumCount=" + mediumCount +
                ", largeCount=" + largeCount +
                '}';
    }
}
