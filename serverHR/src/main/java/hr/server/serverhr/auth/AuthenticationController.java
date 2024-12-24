package hr.server.serverhr.auth;


import hr.server.serverhr.config.jwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HrMangement/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final jwtService jwtservice;
    private final AuthenticationService service;

    UserDetails userDetails;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return  ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return  ResponseEntity.ok(service.autheticate(request));

    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        if (jwtservice.isTokenValiddd(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }
    }

    private final AuthenticationService authenticationService;
    @GetMapping("/session/{token}")
    public ResponseEntity<UserDetails> getUserFromToken(@PathVariable String token) {
        return authenticationService.getUserFromToken(token);
    }

}
