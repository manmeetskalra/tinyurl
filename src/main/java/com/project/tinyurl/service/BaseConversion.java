package com.project.tinyurl.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversion {

    private final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    private final int base = allowedCharacters.length;

    public String encode(long input){
        var encodedString = new StringBuilder();

        if(input == 0){
            return String.valueOf(allowedCharacters[0]);
        }

        while(input>0){
            encodedString.append(allowedCharacters[(int) input%base]);
            input /=base;
        }

        return encodedString.toString();
    }

    public long decode(String input){

        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}
