package com.deepak.quizapplication.custom;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// TODO use this class to lazy load the components
//@Component
@Lazy
public class LazyLoadBean {

    public LazyLoadBean() {
        System.out.println("Lazy Load Object Created");
    }
}
