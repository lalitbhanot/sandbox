package com.lalit.urlshortener;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordmatcher {

    public static void main(String[] args) {
        String hashFromDb = "$2a$10$FTbGwkkuBN9Mg3jqMdCJe.30BVeahxrARn.3LraBqUERekvE2lZfy"; // Replace with your real DB value
        String rawPassword = "admin";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Match: " + encoder.matches(rawPassword, hashFromDb));
    }
}
