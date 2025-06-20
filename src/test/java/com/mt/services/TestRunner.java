package com.mt.services;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(EmployeeServiceTest.class);
        
        System.out.println("Tests run: " + result.getRunCount());
        System.out.println("Failures: " + result.getFailureCount());
        System.out.println("Ignored: " + result.getIgnoreCount());
        System.out.println("Success: " + result.wasSuccessful());
        
        for (Failure failure : result.getFailures()) {
            System.out.println("Failed: " + failure.toString());
        }
    }
}