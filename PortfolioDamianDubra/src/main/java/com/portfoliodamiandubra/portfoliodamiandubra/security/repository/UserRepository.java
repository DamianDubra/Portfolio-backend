/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.repository;

import com.portfoliodamiandubra.portfoliodamiandubra.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author dubra
 */
@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
   Optional<User> findByUserName(String userName);
   boolean existByUserName(String username);
   boolean existByEmail(String email);
}
