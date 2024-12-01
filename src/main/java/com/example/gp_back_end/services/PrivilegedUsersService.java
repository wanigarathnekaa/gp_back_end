package com.example.gp_back_end.services;

import com.example.gp_back_end.repository.PrivilegedUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PrivilegedUsersService {

    private final PrivilegedUsersRepository privilegedUsersRepository;

    @Autowired
    public PrivilegedUsersService(PrivilegedUsersRepository privilegedUsersRepository) {
        this.privilegedUsersRepository = privilegedUsersRepository;
    }

    public PrivilegedUsersModel addPrivilegedUser(String roleName, String name, String email, String nic) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PrivilegedUsersModel newPrivilegedUser = new PrivilegedUsersModel();
        newPrivilegedUser.setRoleName(roleName);
        newPrivilegedUser.setName(name);
        newPrivilegedUser.setEmail(email);
        newPrivilegedUser.setNic(nic);
        newPrivilegedUser.setPassword(passwordEncoder.encode(nic));

        return privilegedUsersRepository.save(newPrivilegedUser);
    }
}
