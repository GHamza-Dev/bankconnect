package dev.dashboard.bankconnect.auth;

import dev.dashboard.bankconnect.dto.AuthRequest;
import dev.dashboard.bankconnect.dto.AuthResponse;
import dev.dashboard.bankconnect.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class Auth {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/user")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        }catch (BadCredentialsException e){
            System.out.println("Authentication failed: "+e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());


        if (userDetails != null){
            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(jwt,"Authenticated succeeded!"));
        }
        return ResponseEntity.status(400).body("Wrong credentials!");
    }
}
