package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.StudentDetailsModel;
import com.example.gp_back_end.services.StudentDetailsService;
import com.example.gp_back_end.services.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentDetailsController {

    @Autowired
    private StudentDetailsService studentService;
    @GetMapping("/all")
    public List<StudentDetailsModel> getAllStudents(){
        System.out.println("Inside getAllStudents");
        List<StudentDetailsModel> students = studentService.getAllStudents();
        System.out.println("Students Retrieved :" + students);
        return students;
    }
}
