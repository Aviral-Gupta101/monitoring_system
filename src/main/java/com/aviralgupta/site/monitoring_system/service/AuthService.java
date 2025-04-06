package com.aviralgupta.site.monitoring_system.service;

import com.aviralgupta.site.monitoring_system.dto.UserAuthDto;
import com.aviralgupta.site.monitoring_system.entity.User;
import com.aviralgupta.site.monitoring_system.exception.custom_exceptions.UserAlreadyExistsException;
import com.aviralgupta.site.monitoring_system.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    public void signup(UserAuthDto userAuthDto){

        System.out.println("Inside auth service");

        String email = userAuthDto.getEmail();
        String password = userAuthDto.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);

        Optional<User> foundUserOptional = userRepo.findByEmail(email);

        if(foundUserOptional.isPresent())
            throw new UserAlreadyExistsException("User account already exists");

        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(email, hashedPassword);

        userRepo.save(newUser);
    }

}
