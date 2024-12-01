package com.example.gp_back_end.model;

import com.example.gp_back_end.user.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UploadLecturerModel implements UserDetails {
    @Id
    private String id;
    private String lecturerId;
    private String name;
    private String email;
    private String nic;
    private Role role;
    private String course;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return nic;
    }

    @Override
    public String getUsername() {
        return lecturerId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "UploadLecturerModel{" +
                "id='" + id + '\'' +
                ", lecturerId='" + lecturerId + '\'' +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", NIC='" + nic + '\'' +
                ", role=" + role +
                '}';
    }
}
