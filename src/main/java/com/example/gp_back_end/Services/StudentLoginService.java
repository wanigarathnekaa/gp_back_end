package com.example.gp_back_end.Services;

import com.example.gp_back_end.Models.UploadStudentModel;
import com.example.gp_back_end.Repositories.StudentLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentLoginService {

    @Autowired
    private StudentLogin studentLogin;

    public Optional<UploadStudentModel> login(String regNumber, String NIC) {
        Optional<UploadStudentModel> studentOpt = studentLogin.findByRegNumber(regNumber);
        if (studentOpt.isPresent() && studentOpt.get().getNIC().equals(NIC)) {
            return studentOpt;
        }
        return Optional.empty();
    }
}
