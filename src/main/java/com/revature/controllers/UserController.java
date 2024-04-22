package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.BankAccount;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDAO userDAO;
    private UserService userService;

    @Autowired
    public UserController(UserDAO userDAO, UserService userService) {
        this.userDAO = userDAO;
        this.userService = userService;
    }

    // getALl
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userDAO.findAll();
        return ResponseEntity.ok(users);
    }

    //get by userId
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable int userId){
         return userService.getUserById(userId);
    }

    //add new user
    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody User newUser){
        return userService.addNewUser(newUser);
    }

    // update user with new User Object
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User updatedUser){
        return userService.updateUser(userId, updatedUser);
    }

    // get all accounts that belong to a certain userId
    @GetMapping("/accounts/{userId}")
    public ResponseEntity<List<BankAccount>> getAccountsByUserId(@PathVariable int userId){
        return userService.getAccountsByUserId(userId);
    }

    // update username
}
