/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.service;

import com.portfoliodamiandubra.portfoliodamiandubra.security.entity.User;
import com.portfoliodamiandubra.portfoliodamiandubra.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dubra
 */
@Service
@Transactional
public class UserService {
 
    @Autowired
    UserRepository userRepository;
    
    public Optional<User> getByUserName(String userName){
    return userRepository.findByUserName(userName);
            }
    public boolean existByUserName(String userName) {
        return userRepository.existByUserName(userName);
    }
    public boolean existByEmail(String email) {
        return userRepository.existByEmail(email);
    }
    public void save(User user){
        userRepository.save(user);
    }
}
