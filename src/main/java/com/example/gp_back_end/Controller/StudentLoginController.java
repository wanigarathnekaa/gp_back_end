package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.services.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/student") // Add this to avoid conflicts
public class StudentLoginController {

    @Autowired
    private LoginService loginService;

    private AuthenticationController authenticationController;

//    @ApiIgnore
    @RequestMapping(value = "/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<UploadStudentModel> student = loginService.studentLogin(loginRequest.getRegNumber(), loginRequest.getNIC());
        return student.map(value -> "Welcome " + value.getName())
                .orElse("Invalid credentials");
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String RegNumber;
        private String NIC;
    }
}
