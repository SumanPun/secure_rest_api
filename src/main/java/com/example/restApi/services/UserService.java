package com.example.restApi.services;

import com.example.restApi.dtos.UpdateUserDto;
import com.example.restApi.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(long id,UpdateUserDto userDto);
    UserDto getUserById(long id);
    List<UserDto> getAllUsers();
    boolean deleteUser(long id);
    UserDto registerNewUser(UserDto userDto);
}
