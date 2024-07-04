package com.etd.dto;

import com.etd.enumeration.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id;

    private String name;

    private String password;

    private UserRoleEnum role;
}
