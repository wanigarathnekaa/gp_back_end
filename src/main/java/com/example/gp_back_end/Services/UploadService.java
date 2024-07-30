package com.example.gp_back_end.Services;

import com.example.gp_back_end.Functions.UploadReader;
import com.example.gp_back_end.Models.UploadLecturerModel;
import com.example.gp_back_end.Models.UploadStudentModel;
import com.example.gp_back_end.Repositories.LecturerRepository;
import com.example.gp_back_end.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private UploadReader uploadReader;

    public void importData(MultipartFile file) throws IOException {
        List<UploadStudentModel> models = uploadReader.readStudentExcel(file);
        studentRepository.saveAll(models);
    }

    public List<UploadStudentModel> getAllData() {
        return studentRepository.findAll();
    }

    public void importLecturerData(MultipartFile file) throws IOException {
        List<UploadLecturerModel> models = uploadReader.readLecturerExcel(file);
        lecturerRepository.saveAll(models);
    }

    public List<UploadLecturerModel> getAllLecturerData() {
        return lecturerRepository.findAll();
    }
}

