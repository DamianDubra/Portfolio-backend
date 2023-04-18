/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliodamiandubra.portfoliodamiandubra.security.jwt;

import com.portfoliodamiandubra.portfoliodamiandubra.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author dubra
 */
public class JwtTokenFilter extends OncePerRequestFilter{
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProviders jwtProviders;
    
    @Autowired
    UserDetailsServiceImpl userDetailService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
   try{
       String token = getToken(req);
       if(token != null && jwtProviders.validateToken(token)){
           String userName = jwtProviders.getUserNameFromToken(token);
           UserDetails userDetails = userDetailService.loadUserByUsername(userName);
           UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken (userDetails, null, userDetails.getAuthorities());
       SecurityContextHolder.getContext().setAuthentication(auth);
       }
       
   }catch (Exception e){
   logger.error("fail dofilter");
   }
   filterChain.doFilter(req, res);
    }
 private String getToken(HttpServletRequest request){
     String header = request.getHeader("Authorization");
     if(header != null && header.startsWith("Bearer"))
     
     return header.replace("Bearer", "");
     return null;
 }
     
}
