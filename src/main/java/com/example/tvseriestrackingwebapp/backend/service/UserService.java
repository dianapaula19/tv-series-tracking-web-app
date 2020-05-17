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


    public void save(User user){
        userRepository.save(user);
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
        String pattern = "(?=.*[0-9])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    public static boolean emailValidator(String email) {
        String pattern = "^(.+)@(.+)$";
        return email.matches(pattern);
    }

    public List<String> isValid(User user) {
        List<String> notifications = new ArrayList<>();
        if(!usernameValidator(user.getUsername())) {
            notifications.add("The username must be between 8 and 30 characters");
        }
        if(!passwordValidator(user.getPassword())) {
            notifications.add("The password must be at least 8 characters and contain at least one number");
        }
        if(!emailValidator(user.getEmail())) {
            notifications.add("The email must respect the proper format(example: johnsmith@example.com)");
        }
        if(user.getFirstName() == null || user.getFirstName().length() == 0) {
            notifications.add("The first name field must not be blank");
        }
        if(user.getLastName() == null || user.getLastName().length() == 0) {
            notifications.add("The last name must not be blank");
        }
        if(this.findByUsername(user.getUsername())) {
            notifications.add("The username already exists");
        }
        if(this.findByEmail(user.getEmail())) {
            notifications.add("There's already an user registered with this email");
        }
        if(notifications.size() == 0) {
            return null;
        }
        return notifications;
    }

    public boolean findByUsername(String username) {
        for (User u : userRepository.findAll()) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean findByEmail(String email) {
        for (User u : userRepository.findAll()) {
            if(u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
}
