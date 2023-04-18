/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.entity;

import com.portfoliodamiandubra.portfoliodamiandubra.security.enums.RolName;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author dubra
 */
@Entity


public class Rol {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;
    
    public Rol() {
}
    public Rol(@NotNull RolName rolName){
        this.rolName = rolName;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public RolName getRolName() {
        return rolName;
    }
    
    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }
   
}
