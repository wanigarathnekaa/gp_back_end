package com.example.gp_back_end.Controller;

import com.example.gp_back_end.dto.CourseRequest;
import com.example.gp_back_end.model.CourseModel;
import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.services.CourseService;
import com.example.gp_back_end.services.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    @PostMapping("/course")
    public List<CourseModel> getCourse (@RequestBody CourseRequest request){
        System.out.println(request.getRegNumber());
        return courseService.getUserCoursesByID(request.getRegNumber());

    }
}
