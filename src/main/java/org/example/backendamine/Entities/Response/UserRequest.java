package org.example.backendamine.Entities.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendamine.Entities.Role;
import org.example.backendamine.Entities.TypeDepartement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private TypeDepartement departement;
    private Long matricule;
    private String phone;
    private boolean status;
    private String password;
    private Role role;

}
