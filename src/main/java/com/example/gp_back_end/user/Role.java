package com.example.gp_back_end.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.gp_back_end.user.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    STUDENT(
            Set.of(
                    STUDENT_FORM_SUBMITTER
            )
    ),
    LECTURER(
            Set.of(
                    LECTURER_FORM_SUBMITTER
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_ROLE_CREATOR,
                    ADMIN_USER_CREATOR,
                    ADMIN_UPDATE_USER,
                    ADMIN_DELETE_USER,
                    ADMIN_FORM_CREATOR,
                    ADMIN_FORM_EDITOR,
                    ADMIN_FORM_DELETE,
                    ADMIN_FORM_PUBLISHER,
                    ADMIN_SUBMISSION_VIEWER,
                    ADMIN_SUBMISSION_EDITOR,
                    ADMIN_PEER_ADDER
            )
    )
    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
