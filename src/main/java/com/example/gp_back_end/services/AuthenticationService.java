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
                .name(request.getName())
                .indexNumber(request.getIndexNumber())
                .email(request.getEmail())
                .nic(request.getNic())
                .role(Role.STUDENT)
                .roleName("Student")
                .build();
        studentRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse singleLecturerRegister(RegisterRequest request) {
        var lecturer = UploadLecturerModel.builder()
                .regNumber(request.getRegNumber())
                .name(request.getName())
                .email(request.getEmail())
                .nic(request.getNic())
                .password(request.getNic())
                .role(Role.LECTURER)
                .roleName("Lecturer")
                .build();
        lecturerRepository.save(lecturer);
        var jwtToken = jwtService.generateToken(lecturer);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        System.out.println(request);

        try {
            var x=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getRegNumber(),
                            request.getNic()
                    )
            );
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        var user = studentLoginRepository.findByRegNumberAndNic(request.getRegNumber(),request.getNic());
        if (user.isPresent()) {
            var jwtToken = jwtService.generateToken(user.get());
            var role = user.get().getRoleName();
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .message("Welcome " + user.get().getName())
                    .roleName(role)
                    .build();
        }else{
            return AuthenticationResponse.builder()
                    .message("Invalid username or password")
                    .build();
        }
    }

    public AuthenticationResponse authenticateLecturer(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLecturerId(),
                        request.getNic()
                )
        );
        var user = lecturerLoginRepository.findByRegNumber(request.getLecturerId())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
