package org.example;

import org.example.controlles.Testing;

public class Main {
    public static void main(String[] args) {
        testing();
    }
    public static void testing(){
        Testing testing = new Testing();
        testing.executeTesting();
    }

}