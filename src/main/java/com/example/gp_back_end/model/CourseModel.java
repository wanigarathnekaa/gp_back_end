package com.example.gp_back_end.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "course")
public class CourseModel {
    @Id
    private String id;
    private String courseCode;
    private String courseName;
    private String credit;
    private String year;
    private String semester;
}
