package com.comprathor.service;

import com.comprathor.model.UserModel;
import com.comprathor.repository.entity.User;

import java.util.List;

public interface IUserService {

    UserModel saveUser(User user);

    List<UserModel> getAllUsers();

    List<UserModel> getUserDetails();

    UserModel updateUser(int id, User updatedUser);

    String deleteUser(int id);

    UserModel getUserById(int id);
}
