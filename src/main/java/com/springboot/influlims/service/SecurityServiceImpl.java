package com.springboot.influlims.service;

import com.springboot.influlims.service.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

//	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Object findLoggedInUser() {
		return SecurityContextHolder.getContext().getAuthentication().getDetails();
	}

	@Override
	public String findLoggedInUsername() {
		Object userDetails = findLoggedInUser();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	@Override
	public void autoLogin(String login, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(login);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(authenticationToken);

		if (authenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//			System.out.println("Successfully " + login + " auto logged in with password " + password);
//			logger.debug(String.format("Successfully %s auto logged in", username));
		}
	}
}
