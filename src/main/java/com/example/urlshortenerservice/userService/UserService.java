package com.example.urlshortenerservice.userService;

import com.example.urlshortenerservice.userService.dto.UserRegistrationDto;
import com.example.urlshortenerservice.userService.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
}
