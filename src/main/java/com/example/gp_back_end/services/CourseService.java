package com.example.gp_back_end.services;

import com.example.gp_back_end.dto.CourseRequest;
import com.example.gp_back_end.dto.CourseWithFormDTO;
import com.example.gp_back_end.model.CourseModel;
import com.example.gp_back_end.model.FormModel;
import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.repository.CourseRepository;
import com.example.gp_back_end.repository.FormRepository;
import com.example.gp_back_end.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final FormRepository formRepository;
    private final FormService formService;

    public List<CourseModel> getUserCourses(CourseRequest request) {
        // Query the repository to find courses by year and semester
        return courseRepository.findByYearAndSemester(request.getYear(), request.getSemester());
    }

    public List<CourseModel> getLecturerCourses(List<String> courseCodes) {
        return courseRepository.findByCourseCodeIn(courseCodes);
    }

    public List<CourseModel> getUserCoursesByID( String regNumber) {
        UploadLecturerModel user = lecturerRepository.findByRegNumber(regNumber);
        List<String> courseModels = user.getCourse();;
        return courseRepository.findByCourseCodeIn(courseModels);
    }

    public List<CourseWithFormDTO> getNotFilledCourses(CourseRequest request) {
        String regNumber = request.getRegNumber();
        String year = request.getYear();
        String semester = request.getSemester();

        // Fetch all courses for the given year and semester
        List<CourseModel> allCourses = courseRepository.findByYearAndSemester(year, semester);

        // Filter and map courses to include form URL
        return allCourses.stream()
                .filter(course -> {
                    // Lookup formId using the course code
                    FormModel form = formRepository.findFormIdByCourse(course.getCourseCode());
                    String formId = form.getId();
                    if (formId == null) {
                        // Skip courses with no associated form
                        return false;
                    }
                    // Check if the user has not submitted the form
                    return !formService.hasUserSubmittedForm(formId, regNumber);
                })
                .map(course -> {
                    // Lookup formId again to fetch the form URL
                    FormModel form = formRepository.findFormIdByCourse(course.getCourseCode());
                    String formId = form.getId();
                    String formUrl = formId != null ? form.getShareURL() + formId : null;

                    // Map course to a DTO with the form URL
                    return new CourseWithFormDTO(
                            course.getCourseCode(),
                            course.getCourseName(),
                            formUrl
                    );
                })
                .collect(Collectors.toList());
    }

    public List<CourseWithFormDTO> getFilledCourses(CourseRequest request) {
        String regNumber = request.getRegNumber();
        String year = request.getYear();
        String semester = request.getSemester();

        // Fetch all courses for the given year and semester
        List<CourseModel> allCourses = courseRepository.findByYearAndSemester(year, semester);
        System.out.println(allCourses);

        // Filter and map courses to include form URL
        return allCourses.stream()
                .filter(course -> {
                    // Lookup formId using the course code
                    FormModel form = formRepository.findFormIdByCourse(course.getCourseCode());
                    System.out.println(form);
                    String formId = form.getId();
                    if (formId == null) {
                        // Skip courses with no associated form
                        return false;
                    }
                    // Check if the user has not submitted the form
                    return formService.hasUserSubmittedForm(formId, regNumber);
                })
                .map(course -> {
                    // Lookup formId again to fetch the form URL
                    FormModel form = formRepository.findFormIdByCourse(course.getCourseCode());
                    String formId = form.getId();
                    String formUrl = formId != null ? form.getShareURL() + formId : null;

                    // Map course to a DTO with the form URL
                    return new CourseWithFormDTO(
                            course.getCourseCode(),
                            course.getCourseName(),
                            formUrl
                    );
                })
                .collect(Collectors.toList());
    }
}
