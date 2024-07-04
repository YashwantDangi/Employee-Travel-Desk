package com.etd.service.impl;

import com.etd.dto.UserDTO;
import com.etd.entity.User;
import com.etd.repositories.UserRepository;
import com.etd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> listOfUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public UserDTO authenticateUser(String username, String password) {
        List<User> listOfUser = listOfUsers();
        UserDTO userDto = new UserDTO();
        for(User u: listOfUser){
            if(u.getName().equals(username) && u.getPassword().equals(password)){
                userDto.setId(u.getId());
                userDto.setName(u.getName());
                userDto.setPassword(u.getPassword());
                userDto.setRole(u.getRole());
                break;
            }
        }
        return userDto;
    }
}
