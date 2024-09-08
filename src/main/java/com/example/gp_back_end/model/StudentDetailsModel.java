package com.example.gp_back_end.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "studentDetails")
public class StudentDetailsModel {
    @Id
    private String id;
    private String userName;
    private String regNo;
    private String indexNo;
    private String size;
    private String phone;
    private String issuedDate;
    private String returnedDate;
    private String status;

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public String getSize() {
        return size;
    }

    public String getPhone() {
        return phone;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentDetailsModel{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", regNo='" + regNo + '\'' +
                ", indexNo='" + indexNo + '\'' +
                ", size='" + size + '\'' +
                ", phone='" + phone + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", returnedDate='" + returnedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
