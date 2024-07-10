package com.example.gp_back_end.Services;

import com.example.gp_back_end.Functions.UploadReader;
import com.example.gp_back_end.Models.UploadLecturerModel;
import com.example.gp_back_end.Models.UploadModel;
import com.example.gp_back_end.Repositories.LecturerRepository;
import com.example.gp_back_end.Repositories.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadService {
    @Autowired
    private UploadRepository uploadRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private UploadReader uploadReader;

    public void importData(MultipartFile file) throws IOException {
        List<UploadModel> models = uploadReader.readStudentExcel(file);
        uploadRepository.saveAll(models);
    }

    public List<UploadModel> getAllData() {
        return uploadRepository.findAll();
    }

    public void importLecturerData(MultipartFile file) throws IOException {
        List<UploadLecturerModel> models = uploadReader.readLecturerExcel(file);
        lecturerRepository.saveAll(models);
    }

    public List<UploadLecturerModel> getAllLecturerData() {
        return lecturerRepository.findAll();
    }
}

