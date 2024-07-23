package com.example.gp_back_end.services;


import com.example.gp_back_end.auth.AuthenticationRequest;
import com.example.gp_back_end.auth.AuthenticationResponse;
import com.example.gp_back_end.auth.RegisterRequest;
import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.repository.LecturerLoginRepository;
import com.example.gp_back_end.repository.LecturerRepository;
import com.example.gp_back_end.repository.StudentLoginRepository;
import com.example.gp_back_end.repository.StudentRepository;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final StudentLoginRepository studentLoginRepository;
    private final LecturerLoginRepository lecturerLoginRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse singleStudentRegister(RegisterRequest request) {
        var user = UploadStudentModel.builder()
                .regNumber(request.getRegNumber())
                .Name(request.getName())
                .indexNumber(request.getIndexNumber())
                .Email(request.getEmail())
                .NIC(request.getNIC())
                .role(Role.STUDENT)
                .build();
        studentRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse singleLecturerRegister(RegisterRequest request) {
        var lecturer = UploadLecturerModel.builder()
                .lecturerId(request.getLecturerId())
                .Name(request.getName())
                .Email(request.getEmail())
                .NIC(request.getNIC())
                .role(Role.LECTURER)
                .build();
        lecturerRepository.save(lecturer);
        var jwtToken = jwtService.generateToken(lecturer);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateStudent(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getRegNumber(),
                        request.getNIC()
                )
        );
        var user = studentLoginRepository.findByRegNumber(request.getRegNumber())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateLecturer(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLecturerId(),
                        request.getNIC()
                )
        );
        var user = lecturerLoginRepository.findByLecturerId(request.getLecturerId())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}