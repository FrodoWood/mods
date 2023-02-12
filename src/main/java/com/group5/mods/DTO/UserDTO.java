package com.group5.mods.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty(message = "{registration.validation.name}")
    private String name;
    @NotEmpty(message = "{registration.validation.username}")
    private String username;
    @NotEmpty(message = "{registration.validation.email}")
    private String email;
    @NotEmpty(message = "{registration.validation.password}")
    private String password;
    @NotEmpty(message = "{registration.validation.roles}")
    private String roles;
}
