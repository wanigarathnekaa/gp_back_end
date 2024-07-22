package com.example.gp_back_end.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lecturer")
public class LecturerController {
    @GetMapping
    public String get(){
        return "GET::LecturerController";
    }

    @PostMapping
    public String post(){
        return "POST::LecturerController";
    }

    @PutMapping
    public String put(){
        return "PUT::LecturerController";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE::LecturerController";
    }

}
