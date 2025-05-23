package com.deepak.quizapplication.service;

import com.deepak.quizapplication.dao.UsersDao;
import com.deepak.quizapplication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


// UserDetailsService is part of Spring boot Security framework which assists in User Login and Authentication TODO

@Service
public class UserService {

    @Autowired
    private UsersDao usersDao;

    //TODO complete Exception Handling
    public ResponseEntity<String> addUser(Users user) {
        // validate the fields before storing into the db
        if ((user.getName() == null || user.getName().equals("") || user.getAge() == null) || user.getName().length() >= 36 || user.getAge() < 18) {
            return new ResponseEntity<>("Please enter correct information:" + user, HttpStatus.BAD_REQUEST);
        }
        //TODO - other desired validations

        usersDao.save(user);
        return new ResponseEntity<>("SUCCESS: " + user.getUsername() + " has been successfully registered", HttpStatus.CREATED);
    }

    public ResponseEntity<Users> login(String userName) {
        Users user = usersDao.findByUsername(userName);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // TODO - next phase using security framework
    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersDao.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder().username(userObj.getUsername())
                    .password(userObj.getPassword()).build();
        } else {
            // benefit of having Security framework, it handles the negative scenario
            throw new UsernameNotFoundException(username);
        return null;
    }
     */
}
