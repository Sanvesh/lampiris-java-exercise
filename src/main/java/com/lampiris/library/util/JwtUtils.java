package com.lampiris.library.util;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Autowired
	Environment env;
	
	public String createToken(String subject) {
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.key"))
				.compact();
	}
	
	public boolean isValidJwtToken(String jwt) {
		boolean isValid = true;
		String subject = null;
		
		try {
			subject = Jwts.parser()
								.setSigningKey(env.getProperty("token.key"))
								.parseClaimsJws(jwt)
								.getBody()
								.getSubject();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			isValid = false;
		}
		
		if(null == subject || subject.isEmpty()) 
				isValid = false;
			
		return isValid;
	}
}
