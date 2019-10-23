package com.kishkan.epam.task3;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        Class entityClass = null;
        try {
            entityClass = Class.forName("com.kishkan.epam.task3.Entity");
            Entity instance = (Entity) entityClass.newInstance();
            Method[] methods = entityClass.getDeclaredMethods();
            for (Method method:methods) {
                System.out.print(method);
                System.out.print(" - Annotations: " + Arrays.toString(method.getAnnotations()) + "\n");

            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
