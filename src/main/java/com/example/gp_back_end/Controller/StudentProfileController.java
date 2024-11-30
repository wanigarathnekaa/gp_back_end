package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.services.StudentProfileService;
import com.example.gp_back_end.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;
    private final StudentService studentService;

    public StudentProfileController(StudentProfileService studentProfileService, StudentService studentService) {
        this.studentProfileService = studentProfileService;
        this.studentService = studentService;
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestParam String regNumber,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {
        if (regNumber.isEmpty() || currentPassword.isEmpty() || newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        try {
            String response = studentService.changePassword(regNumber, currentPassword, newPassword);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable String id) {
        try {
            UploadStudentModel profile = studentProfileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Profile not found for id: " + id);
        }
    }
}
