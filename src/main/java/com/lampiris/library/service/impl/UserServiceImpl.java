package com.lampiris.library.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * That is just for a demo exercise i make it hard-coded user name & password. In order to login to 
	 * application use that credentials.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("lampiris", bCryptPasswordEncoder.encode("lampiris"), new ArrayList<>());
	}

}
