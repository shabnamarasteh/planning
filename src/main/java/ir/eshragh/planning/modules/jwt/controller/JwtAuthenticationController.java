package ir.eshragh.planning.modules.jwt.controller;

import ir.eshragh.planning.architecture.annotation.AuthAnnotation;
import ir.eshragh.planning.architecture.interfaces.controller.RestControllerInterface;
import ir.eshragh.planning.modules.admins.entity.Admin;
import ir.eshragh.planning.modules.jwt.ApiResponse;
import ir.eshragh.planning.modules.jwt.JwtTokenUtil;
import ir.eshragh.planning.modules.jwt.model.JwtRequest;
import ir.eshragh.planning.modules.jwt.model.ResponseToken;
import ir.eshragh.planning.modules.jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AuthAnnotation
@RestController
@CrossOrigin
public class JwtAuthenticationController implements RestControllerInterface {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken(authentication);
//        return ResponseEntity.ok((new ResponseToken(jwt,true)));
        return ResponseEntity.ok(new ResponseToken(jwt,true).getResponse());
    }


    @RequestMapping(value = "/signup" , method = RequestMethod.POST)
    public ResponseEntity<?> registerUser( @RequestBody JwtRequest signUpRequest) {
        if(userDetailsService.existsByUsername(signUpRequest.getUsername())) {

            return new ResponseEntity(new ApiResponse(false, "Username is already taken!",0).getFaultResponse(),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Admin admin = new Admin(signUpRequest.getUsername(), signUpRequest.getPassword());

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin result = userDetailsService.save(admin);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully").getSuccessResponse());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}

