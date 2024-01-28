package com.comprathor.controller;

import com.comprathor.model.UserModel;
import com.comprathor.repository.entity.User;
import com.comprathor.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserModel> saveUser(@RequestBody User user) {
        UserModel result = userService.saveUser(user);
        if (result.getId_user() > 0) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(404).build();
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/own")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<UserModel>> getMyDetails() {
        List<UserModel> userDetails = userService.getUserDetails();
        return ResponseEntity.ok(userDetails);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        UserModel result = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        String result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable int id) {
        UserModel user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
