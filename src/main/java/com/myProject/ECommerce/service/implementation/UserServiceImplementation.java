package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.config.JwtProvider;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.repository.UserRepository;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException{
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id: "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException{
        String email = jwtProvider.getEmailFromToken(jwt);
        User user =userRepository.findByEmail(email);
        return user;
    }

}
