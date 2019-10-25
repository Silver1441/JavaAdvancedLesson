package com.kishkan.epam.task3;

public class Entity {

    @Secured(i = 1)
    public void firstMethod() {
        System.out.println("First method is working");
    }

    @Secured(i = 2, str = "loose")
    private void secondMethod() {
        System.out.println("Second method is working");
    }

    public void thirdMethod() {
        System.out.println("Third method is working");
    }
}
