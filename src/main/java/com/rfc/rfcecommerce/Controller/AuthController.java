package com.rfc.rfcecommerce.Controller;

import com.rfc.rfcecommerce.Entity.User;
import com.rfc.rfcecommerce.Repository.IUserRepo;
import com.rfc.rfcecommerce.Service.auth.AuthServiceImpl;
import com.rfc.rfcecommerce.Service.auth.IAuthService;
import com.rfc.rfcecommerce.dto.AuthenticationRequest;
import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;
import com.rfc.rfcecommerce.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final IUserRepo userRepo;
    private final JwtUtil jwtUtil;
    @Autowired
    private IAuthService authService;
    public static final String HEADER_STRING = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";
    @PostMapping("/authenticate")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            String responseBody = new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .toString();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HEADER_STRING, TOKEN_PREFIX + jwt);

            return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
        }

        // Handle the case where the optionalUser is not present
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser (@RequestBody SignupRequest signupRequest){
        System.out.println("signupRequest");
        System.out.println(signupRequest.toString());
        if (authService.hasUserwithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("user already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto =authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
