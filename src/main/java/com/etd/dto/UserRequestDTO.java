package com.etd.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "User Name should not be blank")
    private String name;

    @NotBlank(message = "Password should not be blank")
    private String password;
}
