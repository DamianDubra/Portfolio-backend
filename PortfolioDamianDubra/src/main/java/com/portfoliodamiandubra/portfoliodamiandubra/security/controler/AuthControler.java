/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.controler;

import com.portfoliodamiandubra.Dto.Mensaje;
import com.portfoliodamiandubra.portfoliodamiandubra.security.dto.JwtDto;
import com.portfoliodamiandubra.portfoliodamiandubra.security.dto.LoginUser;
import com.portfoliodamiandubra.portfoliodamiandubra.security.dto.NewUser;
import com.portfoliodamiandubra.portfoliodamiandubra.security.entity.Rol;
import com.portfoliodamiandubra.portfoliodamiandubra.security.entity.User;
import com.portfoliodamiandubra.portfoliodamiandubra.security.enums.RolName;
import com.portfoliodamiandubra.portfoliodamiandubra.security.jwt.JwtProviders;
import com.portfoliodamiandubra.portfoliodamiandubra.security.service.RolService;
import com.portfoliodamiandubra.portfoliodamiandubra.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthControler {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProviders jwtProviders;

    @PostMapping ("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos o emial invalido"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existByUserName(newUser.getUserName())) {
            return new ResponseEntity(new Mensaje("nombre en uso"), HttpStatus.BAD_REQUEST);
                
        }
        if (userService.existByEmail(newUser.getEmail())) {
            return new ResponseEntity(new Mensaje("es emial ya existe"), HttpStatus.BAD_REQUEST);

        }
        User user = 
                new User(newUser.getName(),newUser.getUserName(), newUser.getEmail(), 
                        passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        
        user.setRol(roles);
        userService.save(user);
            return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);    
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProviders.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
