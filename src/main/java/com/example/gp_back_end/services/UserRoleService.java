package com.example.gp_back_end.services;

import com.example.gp_back_end.model.UserRoleModel;
import com.example.gp_back_end.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRoleModel addRole(String roleName, String roleDescription, List<String> selectedPrivileges) {
        UserRoleModel newRole = new UserRoleModel();
        newRole.setRoleName(roleName);
        newRole.setRoleDescription(roleDescription);
        newRole.setSelectedPrivileges(selectedPrivileges);

        return userRoleRepository.save(newRole);
    }

    public List<UserRoleModel> getAllRoles() {
        return userRoleRepository.findAll();
    }
}
