package com.example.bankproject.jwt;


import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
	
	private final JwtUtil ju;
	
	public AuthFilter(JwtUtil ju) {
		this.ju = ju;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(!uri.startsWith("/member/") && !uri.startsWith("/admin/")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = null;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if("token".equals(c.getName())) {
					token = c.getValue();
					break;
				}
			}
			if(token == null) {
				sendError(response, "토큰 없음");
				return;
			}
		} else {
			sendError(response, "토큰 없음");
			return;
		}
		
		
		if(!ju.isValid(token)) {
			response.sendRedirect("/msg=notoken");
			return;
		}
		
		if(ju.isValid(token)) {
			String username = ju.getUsername(token);
			String role = ju.getRole(token);
			
			List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
			UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(username, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
	
	private void sendError(HttpServletResponse response, String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\": \"" + message + "\"}");
	}

}
