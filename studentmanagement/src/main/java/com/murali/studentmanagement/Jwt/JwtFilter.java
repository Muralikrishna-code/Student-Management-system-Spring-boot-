package com.murali.studentmanagement.Jwt;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtservice;
    private final UserDetailsService userDetailservice;
    public JwtFilter(JwtService jwtservice, UserDetailsService userDetailservice)
    { this.jwtservice=jwtservice;
        this.userDetailservice=userDetailservice;
    }
@Override
protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
             throws ServletException, IOException 
{   String authHeader=request.getHeader("Authorization");
String token=null;
String username=null;
if(authHeader !=null&&authHeader.startsWith("Bearer "))
{
    token=authHeader.substring(7);
    try{
    username=jwtservice.extractUsername(token);
    }
    catch(Exception e)
    { System.out.println("Invalid JWT Token");

    }
}
if(username!=null&& SecurityContextHolder.getContext().getAuthentication() == null)
{
    UserDetails userDetails=userDetailservice.loadUserByUsername(username);
    if(jwtservice.validateToken(token))
    {
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
  
    }
}

        filterChain.doFilter(request, response);

}
}
