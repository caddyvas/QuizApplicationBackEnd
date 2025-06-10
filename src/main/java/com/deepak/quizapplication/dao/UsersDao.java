package com.deepak.quizapplication.dao;

import com.deepak.quizapplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * data-jpa package takes care of operations like fetching data from DB, saving data.
 * It needs two things - <tableName, primaryKeyType>
 */

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {
    // method used during login which retrieves specific user based on the parameter
    Optional<Users> findByUsername(String username);
}
