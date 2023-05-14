package org.kstopa;

public class Main {

    public static void main(String[] args) {
        new HttpServer().server().start("localhost", 8080);
    }

}