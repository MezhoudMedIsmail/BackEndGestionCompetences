package org.example.backendamine.Controller;


import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Auth.AuthenticationRequest;
import org.example.backendamine.Entities.Auth.AuthenticationResponse;
import org.example.backendamine.Entities.Auth.RegisterRequest;
import org.example.backendamine.Service.Impl.AuthenticationService;
import org.example.backendamine.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    // Singup function
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    //Login function
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
