package com.company;

public class Main {

    public static void main(String[] args) {
	   /* String cipherName = args[0];
	    String key = cleanKey(args[1]);
	    String crypt = args[2];
	    String inputFile = args[3];
	    String outputFile = args[4]; */
	    Vigenre vigenre = new Vigenre("deceptivedeceptivedeceptive");
	    String encryption = vigenre.encrypt("wearediscoveredsaveyourself");
	    System.out.println(vigenre.encrypt("wearediscoveredsaveyourself"));
	    System.out.println(vigenre.decrypt(encryption));
    }

    public static String cleanKey(String key){
		key = key.replaceAll("\\s", "");
		return key.toLowerCase();
	}
}
