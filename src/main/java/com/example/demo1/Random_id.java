package com.example.demo1;

import java.util.Random;

public class Random_id {

    public static String getId() {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        while(sb.length() < 9){
            int index = rd.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
