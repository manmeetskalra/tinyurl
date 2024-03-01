package com.project.tinyurl.service;

import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class BaseConversion {

    private final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    private final int base = allowedCharacters.length;

    public String encode(long input){
        StringBuilder encodedString = new StringBuilder();

        if(input == 0){
            return String.valueOf(allowedCharacters[0]);
        }

        while(input>0){
            encodedString.append(allowedCharacters[(int)input%base]);
            input /=base;
        }
        encodedString.reverse();
        encodedString.insert(0,"www.");
        encodedString.append(".tinyurl");
        return encodedString.toString();
    }

    public long decode(String str){
        String input = str.split("\\.")[1];
            int id = 0;

            for (int i = 0; i < input.length(); i++)
            {
                if ('a' <= input.charAt(i) && input.charAt(i) <= 'z')
                    id = id * 62 + input.charAt(i) - 'a';
                if ('A' <= input.charAt(i) && input.charAt(i) <= 'Z')
                    id = id * 62 + input.charAt(i) - 'A' + 26;
                if ('0' <= input.charAt(i) && input.charAt(i) <= '9')
                    id = id * 62 + input.charAt(i) - '0' + 52;
            }
            return id;
    }
}
