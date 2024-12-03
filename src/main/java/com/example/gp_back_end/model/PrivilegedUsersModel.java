package com.example.gp_back_end.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class PrivilegedUsersModel {
    @Id
    private String id;
    private String roleName;
    private String name;
    private String email;
    private String nic;
    private String password;
    private String regNumber;
}
