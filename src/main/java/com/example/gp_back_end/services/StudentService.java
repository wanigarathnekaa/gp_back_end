package com.example.gp_back_end.services;

import com.example.gp_back_end.model.StudentDetailsModel;
import com.example.gp_back_end.repository.StudentDetailsRepository;
import com.example.gp_back_end.repository.StudentLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    public List<StudentDetailsModel> getAllStudents(){
        List<StudentDetailsModel> students = studentDetailsRepository.findAll();
        System.out.println("Fetched Students: " +students);
        return students;
    }
    public Optional<StudentDetailsModel> getStudentByRegNo(String regNo){
        return studentDetailsRepository.findByRegNo(regNo);
    }

    public StudentDetailsModel getUserById(String id){
        return studentDetailsRepository.findById(id).orElse(null);
    }

}
