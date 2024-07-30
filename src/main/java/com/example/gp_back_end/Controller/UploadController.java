package com.example.gp_back_end.Controller;


import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private UploadService service;


//    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect (HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }


    @PostMapping("/import/students")
    public String importStudentData(@RequestParam("file") MultipartFile file) {
        try {
            service.importData(file);
            return "Data imported successfully!";
        } catch (IOException e) {
            return "Failed to import data: " + e.getMessage();
        }
    }

    @GetMapping("/students")
    public List<UploadStudentModel> getAllStudents() {
        return service.getAllData();
    }

    @PostMapping("/import/lecturers")
    public String importLecturerData(@RequestParam("file") MultipartFile file) {
        try {
            service.importLecturerData(file);
            return "Data imported successfully!";
        } catch (IOException e) {
            return "Failed to import data: " + e.getMessage();
        }
    }

    @GetMapping("/lecturers")
    public List<UploadLecturerModel> getAllLecturers() {
        return service.getAllLecturerData();
    }
}
