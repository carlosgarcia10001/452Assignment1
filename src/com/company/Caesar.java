package com.company;
import java.util.*;

class Caesar implements CipherInterface {

    int key = -1;
    
    public Caesar(String key) {
        this.setKey(key);
    }

    @Override
    public boolean setKey(String key) {
        int parsedKey = Integer.parseInt(key);
        this.key = parsedKey <= -1 ? (parsedKey % 26) + 26 : parsedKey % 26;

        return true;
    }
    
    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        //loop through until the end of the text length
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            
            if(Character.isLetter(currentChar)) {
                //shift the character. ch present character that is shifted
                char ch = (char)(currentChar + this.key);
                //if letter bigger than z, shift letter to the beginning
                if (ch > 'z') {
                    //change letter to loop back to beginning if pass 'z'
                    //a = 97 .... z = 122
                    ch = (char)((((currentChar - 97) + this.key ) % 26 ) + 97);

                    //put the shift character to the string result.
                    result.append(ch);
                }
                else { 
                    result.append(ch); 
                }
            }
            // if character is not a letter, do not change character and just append the character
            else{
                result.append(currentChar);
            }
        }
        //return string representation 
        return result.toString();
        // Construct cipher
    }
    
    @Override
    public String decrypt(String text) {
        //check shift limits 
        //if shift is negative, allow shift to go backward

        //result for the engrypt text
        StringBuilder result = new StringBuilder();

        //loop through each letter 
        for (int i = 0; i < text.length(); i++) {
            // checking each letter individually location
            char currentChar = text.charAt(i);

            //check if the character is a letter
            if(Character.isLetter(currentChar)) {
                //check if letter is a lowercase 
                char ch =(char)(currentChar - this.key);
                    //if letter bigger than s, shift letter back to the end
                if (ch < 'a') {
                    //change letter to loop back to end if less than 'a'
                    ch = (char)(((26 + (currentChar - 97) - this.key ) % 26 ) + 97);
                    result.append(ch);
                }
                else { 
                    result.append(ch); 
                }
            }
            // if character is not a letter, do not change character and just append 
            else{
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public String processString(String text, int key, int mode) { 

        String resultText = " ";
            // encrypt mode 
            if (mode == 0) {
                resultText += encrypt(text);
            }
            //decrypt mode 
            else if (mode == 1) {
                resultText += decrypt(text);
            }
        return resultText; 
    }

    // public static void main(String []args){
    //     String text = "thisz z z is a test message 1255644//3 z x y ";
    //     String cipher = encrypt(text, 27);
    //     System.out.println("Encrypt txt:  "  + cipher);
    //     String cipher2 = decrypt(cipher, 27); 
    //     System.out.println("Decrypt txt:  " + cipher2);
    //     System.out.println("");

    //     int key = 1;
    //     String cipher10 = processString("helloworld", key, 0);
    //     System.out.println("Ciphertext =  " + cipher10);
    //     String cipher11 = processString(cipher10, key, 1);
    //     System.out.println("Ciphertext =  " + cipher11);
        
        
    //     // tool for reading user input 
    //     Scanner in = new Scanner(System.in);

    //     // Print "Write a message: "
    //     System.out.println("Write a message: ");
        
    //     //get input
    //     String texts = in.nextLine();
        
        
    //     if (texts.isEmpty()) {
    //         System.out.println("Scanner is empty");
    //     }
    //     // if the is not empty, proceed to print, encrypt, and decrypt msg
    //     else if(!texts.isEmpty()) {
            
    //         // Print the message written by the user
    //         System.out.println("message is: " + texts);
    //         String cipher3 = encrypt (texts, 27);
    //         System.out.println("Encrypt txt:  "  + cipher3);
    //         String cipher4 = decrypt (cipher3, 27); 
    //         System.out.println("Decrypt txt:  "  + cipher4);
    //     }
    //     in.close();
        
    //  }
}