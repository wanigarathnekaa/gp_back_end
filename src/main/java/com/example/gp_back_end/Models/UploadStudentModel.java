package com.example.gp_back_end.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class UploadStudentModel {
    @Id
    private String id;
    private String regNumber;
    private String Name;
    private String indexNumber;
    private String Email;
    private String NIC;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
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
        return "UploadModel{" +
                "id='" + id + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", Name='" + Name + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", NIC='" + NIC + '\'' +
                '}';
    }
}
