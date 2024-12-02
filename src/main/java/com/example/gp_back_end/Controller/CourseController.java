package com.example.gp_back_end.Controller;

import com.example.gp_back_end.dto.CourseRequest;
import com.example.gp_back_end.dto.CourseWithFormDTO;
import com.example.gp_back_end.model.CourseModel;
import com.example.gp_back_end.services.CourseService;
import com.example.gp_back_end.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private UploadService service;
    @Autowired
    private CourseService courseService;

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

    @GetMapping("/allCourses")
    public List<CourseModel> getAllCourses() {
        return service.getAllCourseData();
    }

    @PostMapping("/studentCourses")
    public List<CourseModel> getUserCourses(@RequestBody CourseRequest request) {
        System.out.println(request.getYear());
        return courseService.getUserCourses(request);
    }

    @PostMapping("/lecturerCourses")
    public List<CourseModel> getLecturerCourses(@RequestBody CourseRequest request) {
        System.out.println(request.getCourse());
        return courseService.getLecturerCourses(request.getCourse());
    }

    @PostMapping("/notFilledCourse")
    public List<CourseWithFormDTO> getNotFilledCourse(@RequestBody CourseRequest request) {
        //Getting Course that are not filled by an user
        return courseService.getNotFilledCourses(request);
    }

    @PostMapping("/FilledCourse")
    public List<CourseWithFormDTO> getFilledCourse(@RequestBody CourseRequest request) {
        //Getting Course that are not filled by an user
        return courseService.getFilledCourses(request);
    }
}
