package com.samuelClass.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class IsPasswordValid implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
