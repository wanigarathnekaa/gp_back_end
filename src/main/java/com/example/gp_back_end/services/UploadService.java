package com.example.gp_back_end.services;

import com.example.gp_back_end.function.UploadReader;
import com.example.gp_back_end.model.CourseModel;
import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.repository.CourseRepository;
import com.example.gp_back_end.repository.LecturerRepository;
import com.example.gp_back_end.repository.StudentRepository;
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
    private CourseRepository courseRepository;

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

    public void importCourseData(MultipartFile file) throws IOException {
        List<CourseModel> models = uploadReader.readCourseExcel(file);
        courseRepository.saveAll(models);
    }

    public List<CourseModel> getAllCourseData() {
        return courseRepository.findAll();
    }
}

