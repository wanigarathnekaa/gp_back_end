package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.UserRoleModel;
import com.example.gp_back_end.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userRole")
public class UserRoleController {

    private final UserRoleService userRoleService;

    // Constructor Injection
    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/addRole")
    public String addRole(@RequestBody UserRoleModel userRoleModel) {
        UserRoleModel createdRole = userRoleService.addRole(
                userRoleModel.getRoleName(),
                userRoleModel.getRoleDescription(),
                userRoleModel.getSelectedPrivileges()
        );

        if (createdRole != null && createdRole.getId() != null) {
            return "User role created successfully!";
        } else {
            return "Failed to create user role.";
        }
    }

}
