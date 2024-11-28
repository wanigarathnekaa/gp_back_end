package com.example.gp_back_end.Controller;

import com.example.gp_back_end.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private UploadService service;

    @PostMapping("/import")
    public String importCourseData(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        try {
            service.importCourseData(file);
            return "Data imported successfully!";
        } catch (Exception e) {
            return "Failed to import data: " + e.getMessage();
        }
    }

    @GetMapping("/courses")
    public String getAllCourses() {
        return service.getAllCourseData().toString();
    }
}
