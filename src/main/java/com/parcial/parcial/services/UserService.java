package com.parcial.parcial.services;

import com.parcial.parcial.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(Long id);
    Boolean createUser(User user);
    List<User> allUsers();
    Boolean updateUser(long id, User user);
    String login(User user);
}
