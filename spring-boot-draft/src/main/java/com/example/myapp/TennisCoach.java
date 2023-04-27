package com.example.myapp;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Stop doing it";
    }
}
