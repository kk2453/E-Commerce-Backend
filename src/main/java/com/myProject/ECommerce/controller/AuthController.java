package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.config.JwtProvider;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.repository.UserRepository;
import com.myProject.ECommerce.request.LoginRequest;
import com.myProject.ECommerce.response.AuthResponse;
import com.myProject.ECommerce.service.implementation.CustomUserServiceImplementation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
//when using all args constructor, you dont have to write @autowired above each dependency.
public class AuthController {


    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementation customUserServiceImplementation;
    //inject here
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
        //IF YOU HAD A DTO consider using model mapper as it saves a lot of time. here are steps on how to use it
        //it will basically automatically map all the fields from you request body to the actual copy you are going to save
        // you will need to inject it in maven first and then create a bean in your ECommerceApplication class

        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null){
            throw new UserException("email is already in use with another account");
        }

//        User savedUser = modelMapper.map(user, User.class);

        User savedUser = userRepository.save(user);
        //Because you dont have a DTO you can straight up save it

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Signed up successfully");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authentication(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token, "login successful");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.ACCEPTED);
    }


    private Authentication authentication(String username, String password) {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}

