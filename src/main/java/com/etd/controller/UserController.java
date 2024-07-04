package com.etd.controller;


import com.etd.dto.UserDTO;
import com.etd.dto.UserRequestDTO;
import com.etd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Handler for user authentication
     * @param userRequestDto
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<?> authenticate(@RequestBody UserRequestDTO userRequestDto){
        System.out.println(userRequestDto.getName());
        System.out.println(userRequestDto.getPassword());

        UserDTO userDTO=userService.authenticateUser(userRequestDto.getName(), userRequestDto.getPassword());
        if(userDTO.getName()!=null) {
            System.out.println(userDTO.getId());
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
