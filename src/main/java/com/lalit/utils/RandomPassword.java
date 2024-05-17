package com.lalit.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomPassword {
	 public  String generate(int length) {
		    String s1="0123456789abcdeABCDE";
	        StringBuilder password = new StringBuilder(length);
	        Random random = new Random(System.nanoTime());

	        for (int i = 0; i < length; i++) {
	            
	            int position = random.nextInt(s1.length());
	            password.append(s1.charAt(position));
	        }

	        return password.toString();
	    }

}
