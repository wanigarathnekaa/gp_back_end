package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.services.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/lecturer")
public class LecturerLoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<UploadLecturerModel> lecturer = loginService.lecturerLogin(loginRequest.getLecturerId(), loginRequest.getNIC());
        return lecturer.map(value -> "Welcome " + value.getName())
                .orElse("Invalid credentials");
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String lecturerId;
        private String NIC;
    }
}
