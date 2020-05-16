package com.example.tvseriestrackingwebapp.backend.service;

import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class UserService {


    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long count() {
        return userRepository.count();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public boolean save(User user) {
        if(isValid(user)) {
            try {
                userRepository.save(user);
                return true;
            } catch (DataIntegrityViolationException e) {
                System.out.println("User already exists");
            }
        }
        return false;
    }

    public User findById(Integer id) {

        for (User u : userRepository.findAll()) {
            if(u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public User findUserByUsernameAndPassword(String username, String password) {

        for (User u : userRepository.findAll()) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;

            }
        }
        return null;

    }

    public static boolean usernameValidator(String username) {
        String pattern = ".{8,30}";
        return username.matches(pattern);
    }

    public static boolean passwordValidator(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    public static boolean emailValidator(String email) {
        String pattern = "^(.+)@(.+)$";
        return email.matches(pattern);
    }

    public static boolean isValid(User user) {
        if(usernameValidator(user.getUsername()) &&
                passwordValidator(user.getPassword()) &&
                emailValidator(user.getEmail()) &&
                user.getFirstName() != null &&
                user.getLastName() != null) {
            return true;
        }
        return false;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
}
