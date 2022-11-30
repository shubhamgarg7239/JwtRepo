package com.pc.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailsService userDetailsService ;
	@Autowired
	private JwtTokenHelper jwtTokenHelper ;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get token
		String requestToken  = request.getHeader("Authorization") ;
		
		//token => Bearer 2366lkihu)
		System.out.println(requestToken);
		
		String userName = null ;
		String token =null ;
		
		if(requestToken !=null  && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7) ;
			
			try {
 				userName = this.jwtTokenHelper.getUserNameFromToken(token) ;
			}
			catch(IllegalArgumentException e) { System.out.println("unable token does not begin with Bearer");}
			catch(ExpiredJwtException e) { System.out.println("Jwt tokken has expired");}
			catch(MalformedJwtException e) {System.out.println("Invalid jwt"); }
		}
		else System.out.println("jwt token does not begin with Bearer");
		
		// Once we get the token , now validate ;
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication() ==null) {
			UserDetails userDetails =  this.userDetailsService.loadUserByUsername(userName) ;
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				//shi chal rha h --> do authentication.
				
				UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities()) ;
				userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
			}else {
				System.out.println("Invalid jwt token");
			}
		}
		else {System.out.println("Username is null or context is not null");}
		
		filterChain.doFilter(request, response);
		
	}

}
