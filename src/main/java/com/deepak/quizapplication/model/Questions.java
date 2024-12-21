package com.deepak.quizapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity ia a lightweight persistence domain object. It represents a table in a relational database.
 * And each entity corresponds to a row in that table.
 *
 * Object Relation Mapping
 *
 * Data - Lombok - getter and setter
 */
@Data
@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel; // ignore typo bec in the db, I mentioned difficuly_level
    private String category;
}
