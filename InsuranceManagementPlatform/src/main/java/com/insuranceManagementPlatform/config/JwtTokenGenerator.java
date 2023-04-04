package com.insuranceManagementPlatform.config;

import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenGenerator extends OncePerRequestFilter {

	/** generating jwt token.
	 * @param request     : allow request information for http servlets.
	 * @param response    : the servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods.
	 * @param filterChain : filterChain is an object provided by the servlet container giving a view into the invocation chain of a filtered request for a resource.
	 * @return : filterChain.
	 * @throws : ServletException in case of any exception occurred in servlet and incase of any input output any exception occurs IOException is thrown.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {

			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

			String jwt = Jwts.builder().setIssuer("Abhishek").setSubject("JWT Token")
					.claim("username", authentication.getName())
					.claim("authorities", populateAuthorities(authentication.getAuthorities())).setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + 30000000)) // expiration time of 8 hours
					.signWith(key).compact();

			response.setHeader(SecurityConstants.JWT_HEADER, jwt);

		}

		filterChain.doFilter(request, response);

	}

	/**
	 * @param collection : collection of authorities.
	 * @return : String of authorities.
	 */
	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {

		Set<String> authoritiesSet = new HashSet<>();

		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);

	}

	 /**this make sure that this filter will execute only for first time when client call the /api/login at first time.
	 * @param request : allow request information for http servlets.
	 * @return : the request endpoint of login without using filter.
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		return !request.getServletPath().equals("/api/logIn");
	}
}
