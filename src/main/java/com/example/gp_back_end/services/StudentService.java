package com.example.gp_back_end.services;

import com.example.gp_back_end.model.StudentDetailsModel;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.repository.StudentDetailsRepository;
import com.example.gp_back_end.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String changePassword(String regNumber, String currentPassword, String newPassword) {
        UploadStudentModel student = studentRepository.findByRegNumber(regNumber)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (!student.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Incorrect password");
        }

        student.setPassword(newPassword);
        studentRepository.save(student);

        return "Password changed successfully";
    }

    public Optional<UploadStudentModel> findStudentByRegNumber(String regNumber) {
        return studentRepository.findByRegNumber(regNumber);
    }

}
