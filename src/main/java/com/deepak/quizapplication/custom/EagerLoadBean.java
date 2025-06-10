package com.deepak.quizapplication.custom;

// TODO use this class to initialize components early

import org.springframework.stereotype.Component;

//@Component
public class EagerLoadBean {

    public EagerLoadBean() {
        System.out.println("EagerBean loaded!!");
    }

}
