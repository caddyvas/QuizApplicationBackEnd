package com.deepak.quizapplication.service;

import com.deepak.quizapplication.dao.UsersDao;
import com.deepak.quizapplication.exception.UserValidationException;
import com.deepak.quizapplication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public ResponseEntity<String> addUser(Users user) {
        try {
            // validate the fields before storing into the db
            if ((user.getName() == null || user.getName().equals("") || user.getAge() == null) || user.getName().length() >= 36 || user.getAge() < 18) {
                return new ResponseEntity<>("Ensure information entered is valid and not missing any fields!" + user, HttpStatus.BAD_REQUEST);
            }
            //TODO - other desired validations

            // encrypt password using Bcrypt
            user.setPassword(encoder.encode(user.getPassword()));
            usersDao.save(user);
            return new ResponseEntity<>("SUCCESS: " + user.getUsername() + " has been successfully registered", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error in saving info!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> login(String userName) throws UserValidationException {
        Optional<Users> user = usersDao.findByUsername(userName);
        if (user.isPresent()) {
            Map<String, Object> mapResponse = new HashMap<>();
            mapResponse.put("Response Successful", user);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            throw new UserValidationException("User does not exist " + userName);
        }
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            // generate Token
            return jwtService.generateToken(user.getUsername());
        } else {
            return "failed";
        }
    }
}
