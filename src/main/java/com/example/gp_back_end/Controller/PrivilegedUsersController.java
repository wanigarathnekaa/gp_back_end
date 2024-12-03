package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.PrivilegedUsersModel;
import com.example.gp_back_end.services.PrivilegedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/privilegedUsers")
@CrossOrigin("*")
public class PrivilegedUsersController {

    private final PrivilegedUsersService privilegedUsersService;

    // Constructor Injection
    @Autowired
    public PrivilegedUsersController(PrivilegedUsersService privilegedUsersService) {
        this.privilegedUsersService = privilegedUsersService;
    }

    @PostMapping("/add")
    public String addPrivilegedUser(@RequestBody PrivilegedUsersModel privilegedUsersModel) {
        PrivilegedUsersModel createdPrivilegedUser = privilegedUsersService.addPrivilegedUser(
                privilegedUsersModel.getRoleName(),
                privilegedUsersModel.getName(),
                privilegedUsersModel.getEmail(),
                privilegedUsersModel.getNic()
        );

        if (createdPrivilegedUser != null && createdPrivilegedUser.getId() != null) {
            return "Privileged user created successfully!";
        } else {
            return "Failed to create privileged user.";
        }
    }
}
