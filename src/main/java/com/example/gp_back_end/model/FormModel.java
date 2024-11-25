package com.example.gp_back_end.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "forms")
public class FormModel {
    @Id
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private Boolean template;
    private Boolean published;
    private String name;
    private String description;
    private String content;
    private Integer visits;
    private Integer submissions;
    private String shareURL;

    @DBRef
    private List<FormSubmissionModel> formSubmissions;
}
