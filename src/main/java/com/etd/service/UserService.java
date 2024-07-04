package com.etd.service;

import com.etd.dto.UserDTO;
import com.etd.entity.User;

import java.util.List;

public interface UserService {
    public List<User> listOfUsers();
    public UserDTO authenticateUser(String username, String password);
}
