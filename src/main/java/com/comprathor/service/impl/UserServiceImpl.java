package com.comprathor.service.impl;

import com.comprathor.model.UserModel;
import com.comprathor.repository.UserRepo;
import com.comprathor.repository.entity.User;
import com.comprathor.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    public List<UserModel> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(this::convertToUserModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel saveUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return convertToUserModel(savedUser);
    }

    @Override
    public UserModel updateUser(int id, User updatedUser) {
        Optional<User> existingUserOptional = userRepo.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setEmail(updatedUser.getEmail());
            //existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setName(updatedUser.getName());
            existingUser.setRoles(updatedUser.getRoles());

            userRepo.save(existingUser);
            return convertToUserModel(existingUser);
        } else {
            throw new RuntimeException("Error, usuario no encontrado");
        }
    }

    @Override
    public String deleteUser(int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return "Usuario eliminado";
        } else {
            throw new RuntimeException("Error, usuario no encontrado");
        }
    }

    @Override
    public UserModel getUserById(int id) {
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            return convertToUserModel(userOptional.get());
        } else {
            throw new RuntimeException("Error, usuario no encontrado");
        }
    }

    @Override
    public List<UserModel> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOptional = userRepo.findByEmail(userDetails.getUsername());
            return userOptional.map(user -> Collections.singletonList(convertToUserModel(user)))
                    .orElse(Collections.emptyList());
        }
        return Collections.emptyList();
    }




    private UserModel convertToUserModel(User user) {
        return UserModel.builder()
                .id_user(user.getId_user())
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getRoles())
                .build();
    }
}
