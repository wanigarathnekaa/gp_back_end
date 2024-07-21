package com.example.gp_back_end.Controllers;

import com.example.gp_back_end.Models.UploadStudentModel;
import com.example.gp_back_end.Services.StudentLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/student") // Add this to avoid conflicts
public class StudentLoginController {

    @Autowired
    private StudentLoginService studentLoginService;

    @ApiIgnore
    @RequestMapping(value = "/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<UploadStudentModel> student = studentLoginService.login(loginRequest.getRegNumber(), loginRequest.getNIC());
        return student.map(value -> "Welcome " + value.getName())
                .orElse("Invalid credentials");
    }

    public static class LoginRequest {
        private String regNumber;
        private String NIC;

        public String getRegNumber() {
            return regNumber;
        }

        public void setRegNumber(String regNumber) {
            this.regNumber = regNumber;
        }

        public String getNIC() {
            return NIC;
        }

        public void setNIC(String NIC) {
            this.NIC = NIC;
        }
    }
}
