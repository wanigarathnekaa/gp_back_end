package com.example.gp_back_end.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "formSubmissions")
public class FormSubmissionModel {
    @Id
    private String id;
    private String formId;
    @CreatedDate
    private  LocalDateTime createdAt = LocalDateTime.now();
    private String content;

    @DBRef
    private FormModel form;
}
