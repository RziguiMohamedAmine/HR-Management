package hr.server.serverhr.auth;


import hr.server.serverhr.config.jwtService;
import hr.server.serverhr.entities.Role;
import hr.server.serverhr.entities.User;
import hr.server.serverhr.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final jwtService service;

    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .Role(Role.User)
                .build();
         repository.save(user);
        var jwtToken = service.generateToken(user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse autheticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<User> userOptional = repository.findByEmail(request.getEmail());
        User user = userOptional.orElseThrow(NoSuchElementException::new);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseEntity<UserDetails> getUserFromToken(String token) {
        UserDetails userDetails = service.getUserFromToken(token);
        return ResponseEntity.ok(userDetails);
    }






}




