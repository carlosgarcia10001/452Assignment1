package com.company;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {

	public static String getTextLocationInput(String filename)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			return br.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public static void outputCipher(String filename, String cipher)
	{
		try (BufferedWriter writer = new BufferedWriter(java.nio.file.Files.newBufferedWriter(java.nio.file.Paths.get(filename), java.nio.charset.StandardCharsets.US_ASCII))) {
			writer.write(cipher, 0, cipher.length());
		} catch (IOException e) {
			return;
		}
	}

    public static void main(String[] args) {
		if(args[0] == null || args[1] == null || args[2] == null || args[3] == null || args[4] == null) { 
			System.out.println("Invalid usage.\n Usage: ./cipher <CIPHER NAME> <KEY> <ENC/DEC> <INPUTFILE> <OUTPUT FILE>");
			return;
		}
		String cipherName = args[0];
		String key = args[1];
	    String method = args[2].toLowerCase();
		String defaultMethod = "encrypt";
		String inputPath = args[3];
		String outputPath = args[4];

		System.out.println("Reading in contents from " + inputPath + ".");
		
	    String cipherInput = getTextLocationInput(inputPath);
		String cipher = null;
		String output = null;


		if(cipherName.equals("PLF"))
		{
			Playfair playfair = new Playfair(key);
			output = method.equals(defaultMethod) ? playfair.encrypt(cipherInput) : playfair.decrypt(cipherInput);
			cipher = playfair.encrypt(cipherInput);
			assert playfair.decrypt(cipher).equals(cipherInput);	
		}
		else if(cipherName.equals("RTS"))
		{
			// Not working
			RowTransposition rowTransposition = new RowTransposition(key);
			output = method.equals(defaultMethod) ? rowTransposition.encrypt(cipherInput) : rowTransposition.decrypt(cipherInput);
			cipher = rowTransposition.encrypt(cipherInput);
			assert rowTransposition.decrypt(cipher).equals(cipherInput);	
		}
		else if(cipherName.equals("RFC"))
		{
			// Not working
			Railfence railfence = new Railfence(key);
			output = method.equals(defaultMethod) ? railfence.encrypt(cipherInput) : railfence.decrypt(cipherInput);
			cipher = railfence.encrypt(cipherInput);
			assert railfence.decrypt(cipher).equals(cipherInput);
		}
		else if(cipherName.equals("VIG"))
		{
			Vigenre vigenre = new Vigenre(key);
			if(!vigenre.keySet) { System.out.println("Key length for Vigenre must be >= 2"); return; } 
			output = method.equals(defaultMethod) ? vigenre.encrypt(cipherInput) : vigenre.decrypt(cipherInput);
			cipher = vigenre.encrypt(cipherInput);
			assert vigenre.decrypt(cipher).equals(cipherInput);
		}
		else if(cipherName.equals("CES"))
		{
			Caesar caesar = new Caesar(key);
			output = method.equals(defaultMethod) ? caesar.encrypt(cipherInput) : caesar.decrypt(cipherInput);
			cipher = caesar.encrypt(cipherInput);
			assert caesar.decrypt(cipher).equals(cipherInput);
		}
		else
		{
			// Bad input
			System.out.println("Invalid Cipher Name Given.");
			System.out.println("Expected either PLF, RTS, RFC, VIG, or CES but received " + cipherName + ".");
		}

		System.out.println("Writing output to " + outputPath + ".");

		outputCipher(outputPath, output);
    }

    public static String cleanKey(String key){
		key = key.replaceAll("\\s", "");
		return key.toLowerCase();
	}
}
