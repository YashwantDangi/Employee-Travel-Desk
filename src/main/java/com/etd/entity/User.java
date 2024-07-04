package com.etd.entity;

import com.etd.enumeration.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    //	User Id
    @Id
    @Column(name = "User_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //	User name
    @Column(name = "User_Name")
    private String name;

    //	User name
    @Column(name = "User_Password")
    private String password;

    @Column(name="User_Role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
