/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.service;

import com.portfoliodamiandubra.portfoliodamiandubra.security.entity.Rol;
import com.portfoliodamiandubra.portfoliodamiandubra.security.enums.RolName;
import com.portfoliodamiandubra.portfoliodamiandubra.security.repository.RolRepository;

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
public class RolService {
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> getByRolName (RolName rolname){
        return rolRepository.findByRolName(rolname);
    }
    
}
