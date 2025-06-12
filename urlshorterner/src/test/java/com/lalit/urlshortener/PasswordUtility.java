package com.lalit.urlshortener;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("secret");
        System.out.println(encoder.encode("secret"));
        System.out.println("admin");
        System.out.println(encoder.encode("admin"));

    }


}