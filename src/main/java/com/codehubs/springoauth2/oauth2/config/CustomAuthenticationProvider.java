package com.codehubs.springoauth2.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.codehubs.springoauth2.helpers.AuthenticateHelper;
import com.codehubs.springoauth2.model.User;
import com.codehubs.springoauth2.service.impl.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = null;
		try {
			user = userService.findUserByUserName(username);
			if (user == null) {
				throw new AuthenticationCredentialsNotFoundException("Username or password is invalid");
			}

			String hashPwd = AuthenticateHelper.generateSHA256(password);
			if (!hashPwd.equals(user.getPassword())) {
				throw new AuthenticationCredentialsNotFoundException("Username or password is invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
