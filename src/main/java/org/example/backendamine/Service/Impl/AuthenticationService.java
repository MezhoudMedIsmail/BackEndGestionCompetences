package org.example.backendamine.Service.Impl;


import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Auth.AuthenticationRequest;
import org.example.backendamine.Entities.Auth.AuthenticationResponse;
import org.example.backendamine.Entities.Auth.RegisterRequest;
import org.example.backendamine.Entities.Role;
import org.example.backendamine.Entities.User;
import org.example.backendamine.Repository.UserRepository;
import org.example.backendamine.Service.EmailService;
import org.example.backendamine.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .status(true)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        User u = utilisateurRepository.save(user);
        emailService.sendEmailWithTemplate(u);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );
        var user = utilisateurRepository.findByEmail(authenticationRequest.getEmail()).get();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}