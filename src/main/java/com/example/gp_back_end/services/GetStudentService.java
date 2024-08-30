package com.example.gp_back_end.services;

import com.example.gp_back_end.model.GetStudentModel;
import com.example.gp_back_end.repository.GetStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetStudentService {
    @Autowired
    private GetStudentRepository getStudentRepository;

    public List<GetStudentModel> getAllStudents() {
        return getStudentRepository.findAll();
    }

    public Optional<GetStudentModel> getStudentByRegNumber(String regNumber) {
        return getStudentRepository.findByRegNumber(regNumber);
    }
}
