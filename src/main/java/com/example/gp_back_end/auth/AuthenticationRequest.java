package com.example.gp_back_end.auth;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String lecturerId;
    private String regNumber;
    private String nic;
}
 