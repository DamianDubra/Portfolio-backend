/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.dto;


import javax.validation.constraints.NotBlank;

/**
 *
 * @author dubra
 */
public class LoginUser {
    @NotBlank
     private String userName;
   
     @NotBlank
       private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
