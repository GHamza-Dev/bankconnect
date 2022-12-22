package dev.dashboard.bankconnect.auth;

import dev.dashboard.bankconnect.dto.AuthRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/user")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest request){
        String email = request.getEmail()+":USER";
        return ResponseEntity.ok(authService.authenticate(email,request.getPassword()));
    }

    @PostMapping("/agent")
    public ResponseEntity<?> authenticateAgent(@Valid @RequestBody AuthRequest request){
        String email = request.getEmail()+":AGENT";
        return ResponseEntity.ok(authService.authenticate(email,request.getPassword()));
    }
}
