package com.otus.mita.simpleservice.controllers;

import com.otus.mita.simpleservice.dto.UserDTO;
import com.otus.mita.simpleservice.model.Error;
import com.otus.mita.simpleservice.model.User;
import com.otus.mita.simpleservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.zip.DataFormatException;

@RestController
public class Controller {

    private UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{userId}")
    public User findUserById(@PathVariable(value="userId") Integer userId) throws DataFormatException {
        User user = userService.getUserById(userId);
        if(user != null){
            return user;
        }
        else throw new DataFormatException();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDTO userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable(value="userId") Integer userId, @RequestBody @Valid UserDTO userDto) throws DataFormatException {
        userService.updateUser(userDto, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value="userId") Integer userId) throws DataFormatException {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<Error> handleException(DataFormatException e){
        Error exceptionResponse = new Error(0, "string");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<Error> handleException(ConstraintViolationException e){
        Error exceptionResponse = new Error(0, "string");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
