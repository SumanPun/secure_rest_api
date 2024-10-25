package com.example.restApi.controllers;

import com.example.restApi.exceptions.ApiException;
import com.example.restApi.dtos.JwtAuthRequest;
import com.example.restApi.dtos.JwtAuthResponse;
import com.example.restApi.dtos.UserDto;
import com.example.restApi.security.JwtTokenHelper;
import com.example.restApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        this.authenticate(request.getEmail(), request.getPassword());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String userName, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException ex){
            System.out.println("bad credential exception");
            throw new ApiException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registerUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
    }
}
