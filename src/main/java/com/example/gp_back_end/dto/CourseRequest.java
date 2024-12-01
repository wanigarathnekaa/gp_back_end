package com.example.gp_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String regNumber;
    private String year;
    private String semester;
    private List<String> course;
}
