package com.example.gp_back_end.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userRoles")
public class UserRoleModel {
    @Id
    private String id;
    private String roleName;
    private String roleDescription;
    private List<String> selectedPrivileges;
}
