package com.lampiris.library.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lampiris.library.model.LogonRequest;
import com.lampiris.library.util.JwtUtils;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtUtils jwtUtils;

	@Autowired
	public AuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {

			LogonRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(),
					LogonRequest.class);

			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword(), new ArrayList<>()));

		} catch (BadCredentialsException e) {
			throw new RuntimeException("Incorrect username or password.", e);

		} catch (AuthenticationException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((User) authResult.getPrincipal()).getUsername();
		String accessToken = jwtUtils.createToken(userName);

		response.addHeader("access-token", accessToken);
	}

}
