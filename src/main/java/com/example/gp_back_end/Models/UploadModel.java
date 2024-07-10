package com.example.gp_back_end.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class UploadModel {
    @Id
    private String id;
    private String regNumber;
    private String Name;
    private String indexNumber;
    private String Email;
    private String NIC;
    private Date DOB;
    private String Address;
    private String Gender;
    private String Phone;
    private String guaName;
    private String guaPhone;

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

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGuaName() {
        return guaName;
    }

    public void setGuaName(String guaName) {
        this.guaName = guaName;
    }

    public String getGuaPhone() {
        return guaPhone;
    }

    public void setGuaPhone(String guaPhone) {
        this.guaPhone = guaPhone;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "id='" + id + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", Name='" + Name + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", NIC='" + NIC + '\'' +
                ", DOB=" + DOB +
                ", Address='" + Address + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Phone='" + Phone + '\'' +
                ", guaName='" + guaName + '\'' +
                ", guaPhone='" + guaPhone + '\'' +
                '}';
    }
}
