package com.example.restApi.controllers;
//
//import com.example.restApi.config.SecurityConfig;
//import com.example.restApi.payloads.UserDto;
//import com.example.restApi.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest {
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    private UserDto userDto;
//    private List<UserDto> userDtoList;
//
//    @BeforeEach
//    void setup(){
//        userDto = new UserDto();
//        userDto.setUserId(1L);
//        userDto.setEmail("test@test.com");
//
//        userDtoList = Arrays.asList(userDto);
//    }
//
//    @Test
//    void getById_ShouldReturnUser(){
//        when(userService.getUserById(1L)).thenReturn(userDto);
//        ResponseEntity<UserDto> response = userController.getById(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userDto, response.getBody());
//        verify(userService, times(1)).getUserById(1L);
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void getAllUsers_WithAdminRole_ShouldReturnAllUsers() {
//        when(userService.getAllUsers()).thenReturn(userDtoList);
//
//        ResponseEntity<List<UserDto>> response = userController.getAllUsers();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userDtoList, response.getBody());
//        verify(userService, times(1)).getAllUsers();
//    }
//
////    @Test
////    @WithMockUser(roles = "NORMAL")
////    void getAllUsers_WithUserRole_ShouldThrowAccessDeniedException() {
////        List<UserDto> userDtos =userController.getAllUsers();
////        //assertThrows(AccessDeniedException.class, () -> userController.getAllUsers());
////        verify(userService, never()).getAllUsers();
////    }
//
//}

//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.example.restApi.payloads.UserDto;
//import com.example.restApi.payloads.UserDto;
//import com.example.restApi.repositories.RoleRepository;
//import com.example.restApi.repositories.UserRepository;
//import com.example.restApi.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private RoleRepository roleRepository;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    // Test for getById endpoint
//
//    // Test for getAllUsers endpoint with security check
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void getAllUsers_WithAdminRole_ShouldReturnAllUsers() {
//        // Arrange
//        List<com.example.restApi.payloads.UserDto> users = new ArrayList<>();  // create a sample list of users
//        users.add(new com.example.restApi.payloads.UserDto());
//        when(userService.getAllUsers()).thenReturn(users);
//
//        // Act
//        ResponseEntity<List<com.example.restApi.payloads.UserDto>> response = userController.getAllUsers();
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(users, response.getBody());
//        verify(userService, times(1)).getAllUsers();
//    }
//
//    @Test
//    @WithMockUser(roles = "NORMAL")
//    void getAllUsers_WithNormalRole_ShouldThrowAccessDeniedException() throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Principal: " + auth.getPrincipal());
//        System.out.println("Authorities: " + auth.getAuthorities());
//
//        mockMvc.perform(get("/api/users/"))
//                .andExpect(status().isUnauthorized())
//                .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()));
//    }
//}

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.restApi.dtos.ApiResponse;
import com.example.restApi.dtos.UpdateUserDto;
import com.example.restApi.dtos.UserDto;
import com.example.restApi.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        UserDto userDto = new UserDto();  // create a sample UserDto
        userDto.setUserId(userId);
        when(userService.getUserById(userId)).thenReturn(userDto);

        // Act
        ResponseEntity<UserDto> response = userController.getById(userId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateUser_ShouldReturnUpdatedUserDto() throws Exception
    {
        long userId = 1L;
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUserId(userId);
        updateUserDto.setFirstName("John");
        updateUserDto.setLastName("Doe");
        updateUserDto.setAge(22);
        updateUserDto.setGender("Male");
        updateUserDto.setAddress("ktm");

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setAge(22);
        userDto.setAddress("Male");
        userDto.setAddress("ktm");

        when(userService.updateUser(userId,updateUserDto)).thenReturn(userDto);

        mockMvc.perform(put("/api/users/edit/{id}",userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.address").value("ktm"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteById_WithAdmin_ShouldReturnTrue_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        UserDto userDto = new UserDto();  // create a sample UserDto
        userDto.setUserId(userId);
        when(userService.deleteUser(userId)).thenReturn(true);

        // Act
        ResponseEntity<ApiResponse> response = userController.deleteUserById(userId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse apiResponse = response.getBody();
        assertNotNull(apiResponse);
        assertTrue(apiResponse.isSuccess());
        assertEquals("user successfully deleted with user id : " + userId, apiResponse.getMessage());

        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    @WithMockUser(roles = "NORMAL")
    void deleteById_WithNormalRole_ShouldThrowAccessDeniedException() throws Exception {
        mockMvc.perform(delete("/api/users/delete/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void getById_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 1L;
        when(userService.getUserById(userId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> userController.getById(userId));
        verify(userService, times(1)).getUserById(userId);
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllUsers_WithAdminRole_ShouldReturnAllUsers() {
        // Arrange
        List<UserDto> users = new ArrayList<>();  // create a sample list of users
        users.add(new com.example.restApi.dtos.UserDto());
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    @WithMockUser(roles = "NORMAL") // Simulates a user with the NORMAL role
    void getAllUsers_WithNormalRole_ShouldThrowAccessDeniedException() throws Exception {
        mockMvc.perform(get("/api/users/")) // Perform a GET request
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }
}


