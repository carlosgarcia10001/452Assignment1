package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
		/*String cipherName = args[0];
	    String method = args[2];
	    String inputFile = args[3];
	    String outputFile = args[4];*/
	    Railfence railfence = new Railfence("3");
	    String key = "3 4 2 1 5 6 7";
	    RowTransposition rowTransposition = new RowTransposition(key);
		String encryption = rowTransposition.encrypt("attackpostponeduntiltwoamxyz");
		System.out.println(encryption);
    }

    public static String cleanKey(String key){
		key = key.replaceAll("\\s", "");
		return key.toLowerCase();
	}
}
