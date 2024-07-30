package com.example.gp_back_end.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lecturers")
public class UploadLecturerModel {
    @Id
    private String id;
    private String lecturerId;
    private String Name;
    private String Email;
    private String NIC;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    @Override
    public String toString() {
        return "UploadLecturerModel{" +
                "id='" + id + '\'' +
                ", lecturerId='" + lecturerId + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", NIC='" + NIC + '\'' +
                '}';
    }
}
