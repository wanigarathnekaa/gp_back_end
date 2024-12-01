package com.example.gp_back_end.services;

import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.repository.LecturerLoginRepository;
import com.example.gp_back_end.repository.StudentLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Autowired
    private LecturerLoginRepository lecturerLoginRepository;

    public Optional<UploadStudentModel> studentLogin(String regNumber, String NIC) {
        Optional<UploadStudentModel> studentOpt = studentLoginRepository.findByRegNumber(regNumber);
        if (studentOpt.isPresent() && studentOpt.get().getNic().equals(NIC)) {

            return studentOpt;
        }
        return Optional.empty();
    }

    public Optional<UploadLecturerModel> lecturerLogin(String lecturerId, String NIC) {
        Optional<UploadLecturerModel> lecOpt = lecturerLoginRepository.findByRegNumber(lecturerId);
        if (lecOpt.isPresent() && lecOpt.get().getNic().equals(NIC)) {

            return lecOpt;
        }
        return Optional.empty();
    }
}
