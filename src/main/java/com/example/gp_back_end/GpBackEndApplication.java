package com.example.gp_back_end;

import com.example.gp_back_end.auth.AuthenticationService;
import com.example.gp_back_end.auth.RegisterRequest;
import com.example.gp_back_end.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GpBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpBackEndApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var student = RegisterRequest.builder()
                    .firstname("Student")
                    .lastname("Wanig")
                    .email("student@admin.com")
                    .password("password")
                    .role(Role.STUDENT)
                    .build();
            System.out.println("Student token: " + service.register(student).getAccessToken());
        };
    }

}
