package com.example.atmserver.demo.security.controller;


import com.example.atmserver.demo.exception.InvalidUsernameAndPasswordException;
import com.example.atmserver.demo.exception.NumberOfFailedAttemptExceedException;
import com.example.atmserver.demo.security.entity.AuthRequest;
import com.example.atmserver.demo.security.entity.User;
import com.example.atmserver.demo.security.repository.UserRepository;
import com.example.atmserver.demo.security.service.CustomUserDetailsService;
import com.example.atmserver.demo.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to ATM system !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
            User user=userRepository.findByUserName(authRequest.getUserName());
            if (user.getFailedAttempt()>=3){
                throw new NumberOfFailedAttemptExceedException();
            }


        }
        catch (NumberOfFailedAttemptExceedException e){
            throw new NumberOfFailedAttemptExceedException();
        }
        catch (Exception ex) {

            User user=userRepository.findByUserName(authRequest.getUserName());
            int failedAttempt=user.getFailedAttempt();
            failedAttempt++;
            user.setFailedAttempt(failedAttempt);
            userRepository.save(user);
           // return null;
            throw new InvalidUsernameAndPasswordException();
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }



}
