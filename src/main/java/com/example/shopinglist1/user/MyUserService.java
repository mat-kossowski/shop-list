package com.example.shopinglist1.user;

import com.example.shopinglist1.payload.request.LoginRequest;
import com.example.shopinglist1.payload.request.RegisterRequest;
import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.secutiry.jwt.JwtUtils;
import com.example.shopinglist1.secutiry.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MyUserService implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public MyUserService(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }



    @Override
    public boolean isUserExistsByEmail(RegisterRequest registerRequest) {
        return userRepository.existsByEmail(registerRequest.getEmail());
    }

    @Override
    public boolean isUserExistsByUserName(RegisterRequest registerRequest) {
        return userRepository.existsByUserName(registerRequest.getUserName());
    }

    @Override
    public ResponseCookie authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        System.out.println("post w loginiie");
        System.out.println(authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return jwtCookie;
    }

    @Override
    public ResponseCookie logoutUser() {
        return jwtUtils.getCleanJwtCookie();
    }

    @Override
    public ResponseEntity<MessageResponse> addNewUser(RegisterRequest registerRequest) {
        if (isUserExistsByEmail(registerRequest) || isUserExistsByUserName(registerRequest)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(registerRequest.getUserName(),registerRequest.getEmail(),registerRequest.getRole(),
                encoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
