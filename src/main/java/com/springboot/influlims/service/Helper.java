package com.springboot.influlims.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

@Service
public class Helper {

	private static final String host = "localhost:8080/";

	public Collection<? extends GrantedAuthority> getRoles() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}

//	TODO
//	@Cacheable(value = "season")
	public String getSeason() {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();

		int week = cal.get(Calendar.WEEK_OF_YEAR);
		Integer year = cal.get(Calendar.YEAR);

		return (week > 20 && week < 40)
				? year.toString()
				: String.valueOf(year - 1) + " - " + year.toString();
	}

	public String checkAuth(HttpServletRequest request) {
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			String redirect = request.getHeader("referer");
			if(redirect != null && redirect.startsWith(host)) return "redirect:" + redirect;
			return "redirect:/main";
		}
		return null;
	}
}
