package com.rfc.rfcecommerce.controller;

import com.rfc.rfcecommerce.entity.User;
import com.rfc.rfcecommerce.repository.IUserRepo;
import com.rfc.rfcecommerce.service.auth.IAuthService;
import com.rfc.rfcecommerce.dto.AuthenticationRequest;
import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;
import com.rfc.rfcecommerce.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
            return new ResponseEntity<>("Incorrect username or password.", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            String responseBody = new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .toString();
            System.out.println(jwt);


            HttpHeaders headers = new HttpHeaders();
            response.addHeader("Access-Control-Expose-Headers",HEADER_STRING);
            response.addHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, Origin, " +
                    "X-Requested-With, Content-Type, Accept, X-Custom-header ");
            headers.add(HEADER_STRING, TOKEN_PREFIX + jwt);

            return new ResponseEntity<>(responseBody , headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<Object> signupUser (@RequestBody SignupRequest signupRequest){

        if (authService.hasUserwithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("user already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto =authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
