/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.repository;

import com.portfoliodamiandubra.portfoliodamiandubra.security.entity.Rol;
import com.portfoliodamiandubra.portfoliodamiandubra.security.enums.RolName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dubra
 */
@Repository
public interface RolRepository extends JpaRepository <Rol, Integer> {
    Optional<Rol>findByRolName(RolName rolName);
}
