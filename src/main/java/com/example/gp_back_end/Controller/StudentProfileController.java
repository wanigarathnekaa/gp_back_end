package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.services.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/{id}")
    public UploadStudentModel getProfileById(@PathVariable String id) {
        return studentProfileService.getProfileById(id);
    }
}
