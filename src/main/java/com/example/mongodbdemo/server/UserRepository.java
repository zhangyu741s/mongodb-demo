package com.example.mongodbdemo.server;

import com.example.mongodbdemo.dto.UserDTO;

public interface UserRepository {
    public void saveUser(UserDTO user);

    public UserDTO findUserByUserName(String userName);

    public long updateUser(UserDTO user);

    public void deleteUserById(Long id);
}
