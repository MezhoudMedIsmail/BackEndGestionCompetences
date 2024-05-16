package org.example.backendamine.Entities.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendamine.Entities.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private String departement;
    private Long matricule;
    private String phone;
    private boolean status;
    private String password;
    private Role role;
}
