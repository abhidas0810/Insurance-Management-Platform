package com.insuranceManagementPlatform.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

	/** validating jwt token.
	 * @param request     : allow request information for http servlets.
	 * @param response    : the servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods.
	 * @param filterChain : filterChain is an object provided by the servlet container giving a view into the invocation chain of a filtered request for a resource.
	 * @return : filterChain.
	 * @throws : ServletException in case of any exception occurred in servlet and incase of any input output any exception occurs IOException is thrown.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		String jwt= request.getHeader(SecurityConstants.JWT_HEADER);

		
		if(jwt != null) {
						
			try {

				//extracting the word Bearer
				jwt = jwt.substring(7);
				
				SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

				Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				
				String username= String.valueOf(claims.get("username"));
				
				String authorities= (String)claims.get("authorities");		
				
				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				
				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, auths);

				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid Token received..");
			}
			
		}
		
		filterChain.doFilter(request, response);	
	}
	
	/**this time this validation filter has to be executed for all the apis except the /api/login api
	 * @param request : allow request information for http servlets.
	 * @return : the request endpoint of login without using filter.
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
		return request.getServletPath().equals("/api/logIn");
	}
}
