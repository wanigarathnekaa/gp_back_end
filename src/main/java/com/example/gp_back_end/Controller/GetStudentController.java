package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.GetStudentModel;
import com.example.gp_back_end.services.GetStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class GetStudentController {

    @Autowired
    private GetStudentService getStudentService;

    @GetMapping
    public List<GetStudentModel> getAllStudents() {
        return getStudentService.getAllStudents();
    }

    @GetMapping("/regNumber")
    public Optional<GetStudentModel> getStudentByRegNumber(@PathVariable String regNumber) {
        return getStudentService.getStudentByRegNumber(regNumber);
    }
}
