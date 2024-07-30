package com.example.gp_back_end.auth;

import com.example.gp_back_end.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String lecturerId;
    private String regNumber;
    private String name;
    private String indexNumber;
    private String email;
    private String nic;
    private Role role;
}
