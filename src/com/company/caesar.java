import java.util.*;

class Caesar{
    
    public static String encrypt(String text, int key) {
        
         //check key limits 
         //if key is negative
        if (key <= -1) {
            key = (key % 26) + 26; 
        }
        
        else {
            key = key%26;
        }
        
        
        //append to result
        StringBuilder result = new StringBuilder();
        
        //loop through until the end of the text length
        int len = text.length(); 
        for (int i = 0; i < len; i++) {
            
            // checking each letter individually location
            char c = text.charAt(i);
            
            
            //check if the character is a letter
            if(Character.isLetter(c)) {
                //check if letter is a lowercase 
                if(Character.isLowerCase(c)){
                    //shift the character. ch present character that is shifted
                    char ch =(char)(c + key);
                    //if letter bigger than z, shift letter to the beginning
                    if (ch > 'z') {
                        //change letter to loop back to beginning if pass 'z'
                        //a = 97 .... z = 122

                        ch = (char)((((c - 97) + key ) % 26 ) + 97);

                        //put the shift character to the string result.
                        result.append(ch);
                    }
				    else { 
				        result.append(ch); 
				    }
                    
                }
                //check if letter is uppercase
                else if(Character.isUpperCase(c)){
                    char ch =(char)(c + key);
                    //if letter bigger than Z, shift letter to the beginning
                    if (ch > 'Z') {
                        //change letter to loop back to beginning if pass 'Z'

                        ch = (char)((((c - 65) + key ) % 26 ) + 65);
				        result.append(ch);
                    }
                    else {
                        result.append(ch);
                    }
                }
            }
            // if character is not a letter, do not change character and just append the character
            else{
                result.append(c);
            }
        }
        //return string representation 
        return result.toString();
    }
    
    public static String decrypt(String text, int key) {
        
         //check shift limits 
         //if shift is negative, allow shift to go backward
        if (key <= -1) {
            key = (key % 26) + 26; 
        }
        
        else {
            key = key%26;
        }
        
        
        //result for the engrypt text
        StringBuilder result = new StringBuilder();
        
        //loop through each letter 
        int len = text.length(); 
        for (int i = 0; i < len; i++) {
            
            // checking each letter individually location
            char c = text.charAt(i);
            
            
            //check if the character is a letter
            if(Character.isLetter(c)) {
                //check if letter is a lowercase 
                if(Character.isLowerCase(c)){
                    char ch =(char)(c - key);
                     //if letter bigger than s, shift letter back to the end
                    if (ch < 'a') {
                        //change letter to loop back to end if less than 'a'

                        ch = (char)(((26 + (c - 97) - key ) % 26 ) + 97);
				        result.append(ch);
                    }
				    else { 
				        result.append(ch); 
				    }
                    
                }
                //check if letter is uppercase
                else if(Character.isUpperCase(c)){
                    char ch =(char)(c - key);
                    //if letter bigger than A, shift letter back to the end
                    if (ch < 'A') {
                       //change letter to loop back to end if less than 'A'
                        
                        ch = (char)(((26 + (c - 65) - key ) % 26 ) + 65);
                        
				        result.append(ch);
                    }
                    else {
                        result.append(ch);
                    }
                }
            }
            // if character is not a letter, do not change character and just append 
            else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String processString(String text, int key, int mode) { 

        String resultText = " ";
            // encrypt mode 
            if (mode == 0) {
                resultText += encrypt(text, key);
            }
            //decrypt mode 
            else if (mode == 1) {
                resultText += decrypt(text, key); 
            }
        return resultText; 
    }


    public static void main(String []args){
        String text = "Thisz z Z is a test message 1255644//3 z x y ";
        String cipher = encrypt (text, 27);
        System.out.println("Encrypt txt:  "  + cipher);
        String cipher2 = decrypt (cipher, 27); 
        System.out.println("Decrypt txt:  " + cipher2);
        System.out.println("");

        int key = 1;
        String cipher10 = processString("helloworld", key, 0);
        System.out.println("Ciphertext =  " + cipher10);
        String cipher11 = processString(cipher10, key, 1);
        System.out.println("Ciphertext =  " + cipher11);
        
        
        // tool for reading user input 
        Scanner in = new Scanner(System.in);

        // Print "Write a message: "
        System.out.println("Write a message: ");
        
        //get input
        String texts = in.nextLine();
        
        
        if (texts.isEmpty()) {
            System.out.println("Scanner is empty");
        }
        // if the is not empty, proceed to print, encrypt, and decrypt msg
        else if(!texts.isEmpty()) {
            
            // Print the message written by the user
            System.out.println("message is: " + texts);
            String cipher3 = encrypt (texts, 27);
            System.out.println("Encrypt txt:  "  + cipher3);
            String cipher4 = decrypt (cipher3, 27); 
            System.out.println("Decrypt txt:  "  + cipher4);
        }
        in.close();
        
     }
}