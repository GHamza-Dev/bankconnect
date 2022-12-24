package dev.dashboard.bankconnect.auth;

import dev.dashboard.bankconnect.dto.AuthResponse;
import dev.dashboard.bankconnect.dto.Response;
import dev.dashboard.bankconnect.user.CustomUserDetails;
import dev.dashboard.bankconnect.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public Response authenticate(String email, String password) {

        CustomUserDetails userDetails = null;

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            userDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwt = jwtUtil.generateToken(userDetails);
            return new AuthResponse("Authentication succeeded!", jwt);
        } catch (BadCredentialsException e) {
            throw e;
        }
    }
}
