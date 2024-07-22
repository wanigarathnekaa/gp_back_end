package com.example.gp_back_end.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @GetMapping
    public String get(){
        return "GET::StudentController";
    }

    @PostMapping
    public String post(){
        return "POST::StudentController";
    }

    @PutMapping
    public String put(){
        return "PUT::StudentController";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE::StudentController";
    }

}
