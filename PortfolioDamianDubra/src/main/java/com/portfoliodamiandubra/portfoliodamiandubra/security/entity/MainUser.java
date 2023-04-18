/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.entity;

/**
 *
 * @author dubra
 */
import com.sun.istack.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Id;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity


public class MainUser implements UserDetails {
   
   
    private String name;
   
    
    @Column(unique=true)
    private String userName;
    

    private String email;
    
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    public MainUser() {
}
    public MainUser(@NotNull String name,@NotNull String userName, @NotNull String email, @NotNull String password, Collection<? extends GrantedAuthority> authorities ) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
}
    
    
    public static MainUser build (User user) {
        List<GrantedAuthority> authorities = user.getRol()
  .stream()
  .map(rol -> new SimpleGrantedAuthority(rol.getRolName().name()))
  .collect(Collectors.toList());
        return new MainUser (user.getEmail(), user.getName(), user.getPassword(),user.getUserName(), authorities);
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
   
    
    
}