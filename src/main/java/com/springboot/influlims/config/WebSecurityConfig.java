package com.springboot.influlims.config;

import com.springboot.influlims.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	private String sequrityKey = Long.toString(new Random().nextLong());

//	@Autowired
//	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**", "/login", "/registration").permitAll()

				.antMatchers("/admin").hasAnyRole("SUPERADMIN", "ADMIN", "MODERATOR", "TEST", "MANAGE_USERS_TEST", "MANAGE_USERS_NOTEST", "MANAGE_USERS_ALL")

				.antMatchers("/add-sample").hasAnyRole("SUPERADMIN", "ADMIN", "USER", "TEST", "ADD_ALL", "ADD_SAMPLE")
				.antMatchers("/add-extraction").hasAnyRole("SUPERADMIN", "ADMIN", "USER", "TEST", "ADD_ALL", "ADD_EXTRACTION")
				.antMatchers("/add-pcr").hasAnyRole("SUPERADMIN", "ADMIN", "USER", "TEST", "ADD_ALL", "ADD_PCR")

				.antMatchers("/samples").not().hasRole("VIEW_NO")

				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/main",true)
				.failureForwardUrl("/login?error")
				.usernameParameter("login")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
		;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "userDetailsServiceImpl")
	public UserDetailsService detailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean(name = "authenticationProvider")
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(detailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//	@Bean
//	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider("influenza.spb.ru", "ldap://192.168.1.1:3268", "dc=influenza,dc=spb,dc=ru");
//		provider.setConvertSubErrorCodesToExceptions(true);
//		provider.setUseAuthenticationRequestCredentials(true);
//
//		return provider;
//	}

}
