package com.example.gp_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GpBackEndApplication {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Coding! \n Let's create the Back End Application";
    }

    public static void main(String[] args) {

        SpringApplication.run(GpBackEndApplication.class, args);
    }

}
