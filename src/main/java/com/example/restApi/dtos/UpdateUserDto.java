package com.example.restApi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateUserDto {

    private long userId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String gender;
    @NotEmpty
    private int age;
    @NotEmpty
    private String address;
}
