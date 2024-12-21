package com.deepak.quizapplication.dao;

import com.deepak.quizapplication.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * data-jpa package takes care of operations like fetching data from DB, saving data.
 * It needs two things - <tableName, primaryKeyType>
 */

@Repository
public interface QuestionsDao extends JpaRepository<Questions, Integer> {
    List<Questions> findByCategory(String category);

    @Query(value= "SELECT * FROM questions q Where q.category=:category ORDER_BY RANDOM() LIMIT :numQ", nativeQuery = true )
    List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
