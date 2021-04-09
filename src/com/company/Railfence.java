package com.company;

import java.util.Arrays;

public class Railfence implements CipherInterface{
    int key;

    public Railfence(String key){
        setKey(key);
    }

    @Override
    public boolean setKey(String key) {
        this.key = Integer.parseInt(key);
        return true;
    }

    @Override
    public String encrypt(String plaintext) {
        char [] plainText = plaintext.toCharArray();
        StringBuilder [] railfence = new StringBuilder[key];
        for(int i = 0; i < key; i++){
            railfence[i] = new StringBuilder();
        }
        for(int i = 0; i < plainText.length; i++){
            railfence[i%key].append(plainText[i]);
        }
        StringBuilder encryption = new StringBuilder();
        for(int i = 0; i < railfence.length; i++){
            encryption.append(railfence[i]);
        }
        return encryption.toString();
    }


    @Override
    public String decrypt(String ciphertext) {
        char [] cipherText = ciphertext.toCharArray();
        int cipherLength = ciphertext.length();
        int generalRowLength = cipherLength/key;
        int extraCharacterRows = cipherLength%key;
        int firstRowLength = generalRowLength+Math.min(extraCharacterRows,1);
        char [] decryption = new char[cipherLength];
        int cipherIndex = 0;
        boolean extraCharacter = true;
        for(int i = 0; i < key; i++){
            if(i >= extraCharacterRows){
                extraCharacter = false;
            }
            int lastEvaluationIndex = extraCharacter ? key*firstRowLength : key*generalRowLength;
            for(int j = i; j < lastEvaluationIndex;j+=key){
                decryption[j] = cipherText[cipherIndex];
                cipherIndex++;
            }
        }
        return String.valueOf(decryption);
    }
}
