package com.insuranceManagementPlatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppConfig {

	/**
	 * @param http : the configuration creates a servlet filter.
	 * @return : http security filter.
	 * @throws Exception : if any exception occurs that is thrown.
	 */
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
				.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/api/clients").permitAll()
				.requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated().and()
				.addFilterAfter(new JwtTokenGenerator(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class).formLogin().and()
				.httpBasic();

		return http.build();

	}

	/**
	 * @return : BCrypt password encoder for encoding the password.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
}
