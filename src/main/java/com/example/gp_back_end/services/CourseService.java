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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Map<String, Object>> getStudentCourses(CourseRequest request) {
        // Fetch user courses by registration number
        List<CourseModel> userCourses = courseRepository.findByYearAndSemester(request.getYear(), request.getSemester());

        // Build the response structure
        List<Map<String, Object>> result = new ArrayList<>();

        for (CourseModel course : userCourses) {
            // Fetch associated form details
            FormModel form = formRepository.findFormIdByCourse(course.getCourseCode());

            // Skip courses with no associated form
            if (form == null) {
                continue;
            }

            // Determine if the form is filled or not
            boolean isFilled = formService.hasUserSubmittedForm(form.getId(), request.getRegNumber());

            // Fetch lecturers for the course
            List<UploadLecturerModel> lecturers = lecturerRepository.findByCourseContaining(course.getCourseCode());

            // Transform lecturer details to the desired format
            List<Map<String, String>> lecturerDetails = lecturers.stream()
                    .map(lecturer -> Map.of(
                            "name", lecturer.getName(),
                            "email", lecturer.getEmail()
                    ))
                    .collect(Collectors.toList());

            // Build the course data
            Map<String, Object> courseData = new HashMap<>();
            courseData.put("moduleName", course.getCourseName());
            courseData.put("moduleCode", course.getCourseCode());
            courseData.put("lecturers", lecturerDetails);
            courseData.put("formName", form.getName());
            courseData.put("distributedAt", form.getCreatedAt());
            courseData.put("status", isFilled ? "Filled" : "Not Filled");
            courseData.put("dueDate", LocalDate.of(2024, 12, 1)); // Set due date to December 1st, 2024
            courseData.put("isNew", form.getCreatedAt() != null
                    && form.getCreatedAt().isAfter(LocalDate.now().minusDays(7).atStartOfDay()));

            // Add to the result list
            result.add(courseData);
        }

        return result;
    }

}
