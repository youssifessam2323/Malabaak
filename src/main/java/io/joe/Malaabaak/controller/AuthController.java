package io.joe.Malaabaak.controller;


import io.joe.Malaabaak.model.AuthRequest;
import io.joe.Malaabaak.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequest authRequest){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authRequest.getEmail(),
//                        authRequest.getPassword()
//                )
//        );

        final UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());

        if(user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some error has occurred");


        return ResponseEntity.ok(jwtUtil.generateToken(user));
    }
}
