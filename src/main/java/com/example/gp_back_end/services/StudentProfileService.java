package com.example.gp_back_end.services;

import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public UploadStudentModel getProfileById(String id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }
}
