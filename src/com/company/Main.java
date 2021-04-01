package com.company;

public class Main {

    public static void main(String[] args) {
	   /* String cipherName = args[0];
	    String key = cleanKey(args[1]);
	    String crypt = args[2];
	    String inputFile = args[3];
	    String outputFile = args[4]; */
	    Playfair playfair = new Playfair("playfair example");
	    System.out.println(playfair.decrypt(playfair.encrypt("jack jump iggit")));
    }

    public static String cleanKey(String key){
		key = key.replaceAll("\\s", "");
		return key.toLowerCase();
	}
}
