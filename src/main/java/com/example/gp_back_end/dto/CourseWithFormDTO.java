package com.example.gp_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithFormDTO {
    private String courseCode;
    private String courseName;
    private String formUrl;
}
