package com.company;

public class Main {

    public static void main(String[] args) {
		String cipherName = args[0];
	    String key = cleanKey(args[1]);
	    String method = args[2];
	    String inputFile = args[3];
	    String outputFile = args[4];
	    Railfence railfence = new Railfence("3");
	    String encryption = railfence.encrypt("meetmeafterthetogaparty");
	    System.out.println(encryption);
	    System.out.println(railfence.decrypt(encryption));
    }

    public static String cleanKey(String key){
		key = key.replaceAll("\\s", "");
		return key.toLowerCase();
	}
}
