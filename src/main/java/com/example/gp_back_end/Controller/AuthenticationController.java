package com.example.gp_back_end.Controller;

import com.example.gp_back_end.auth.AuthenticationRequest;
import com.example.gp_back_end.auth.AuthenticationResponse;
import com.example.gp_back_end.auth.RegisterRequest;
import com.example.gp_back_end.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/student")
    public ResponseEntity<AuthenticationResponse> registerStudent(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.singleStudentRegister(request));
    }


    @PostMapping("/authenticate/user")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequest request

    ){

        return ResponseEntity.ok(authenticationService.authenticateUser(request));
    }

    @PostMapping("/register/lecturer")
    public ResponseEntity<AuthenticationResponse> registerLecturer(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.singleLecturerRegister(request));
    }


    @PostMapping("/authenticate/lecturer")
    public ResponseEntity<AuthenticationResponse> authenticateLecturer(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticateLecturer(request));
    }

}
