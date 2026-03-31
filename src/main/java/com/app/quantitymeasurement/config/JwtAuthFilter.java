package com.app.quantitymeasurement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException , IOException{
		
		// read authorizaton header
		String authHeader = request.getHeader("Authorization");
		
		//check token is bearer or not
		if(authHeader != null  && authHeader.startsWith("Bearer")) {
			String token = authHeader.substring(7);
			
			if(jwtUtil.validateToken(token)) {
				String username = jwtUtil.extractUsername(token);
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		 filterChain.doFilter(request, response);
	}
}
