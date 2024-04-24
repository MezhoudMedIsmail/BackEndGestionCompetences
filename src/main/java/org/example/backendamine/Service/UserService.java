package org.example.backendamine.Service;

import org.example.backendamine.Entities.Response.UserResponse;
import org.example.backendamine.Entities.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getUser();
    UserResponse updateUser(long id,User user);
    User getUserById(long userId);
}
