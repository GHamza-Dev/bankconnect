package dev.dashboard.bankconnect.auth;

import dev.dashboard.bankconnect.dto.AuthRequest;
import dev.dashboard.bankconnect.dto.Response;
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
        try {
            String email = request.getEmail()+":CLIENT";
            Response response = authService.authenticate(email,request.getPassword());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(403).body(new Response(e.getMessage(),403));
        }
    }

    @PostMapping("/agent")
    public ResponseEntity<?> authenticateAgent(@Valid @RequestBody AuthRequest request){
        try {
            String email = request.getEmail()+":AGENT";
            Response response = authService.authenticate(email,request.getPassword());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(403).body(new Response(e.getMessage(),403));
        }
    }
}
