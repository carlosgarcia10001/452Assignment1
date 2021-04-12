package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class RowTransposition implements CipherInterface{
    ArrayList<Integer> key;
    int keyLength;
    public RowTransposition(String key){
        this.keyLength = 0;
        setKey(key);
    }
    @Override
    public boolean setKey(String key) {
        this.key = keyParser(key);
        return true;
    }

    public ArrayList<Integer> keyParser(String key){
        char [] k = key.toCharArray();
        ArrayList<Integer> parsedKey = new ArrayList<>();
        int index = 0;
        while(index < k.length){
           StringBuilder currentNumber = new StringBuilder();
           while(index < k.length && Character.isDigit(k[index])){
               currentNumber.append(k[index]);
               index++;
           }
           if(currentNumber.length() > 0){
               int row = Integer.parseInt(currentNumber.toString());
               keyLength = Math.max(keyLength, row);
               parsedKey.add(row-1);
           }
           else{
               index++;
           }
        }
        return parsedKey;
    }

    public ArrayList<Integer> getKey(){
        return this.key;
    }

    public int getKeyLength(){
        return this.keyLength;
    }

    @Override
    public String encrypt(String plaintext) {
        char [] plainText = plaintext.toCharArray();
        char [][] matrix = createMatrix(plainText);
        StringBuilder encryption = new StringBuilder();
        int rowLength = plainText.length/this.keyLength;
        for(int i = 0; i < keyLength; i++) {
            for (int j = 0; j < rowLength; j++) {
                encryption.append(matrix[j][key.get(i)]);
            }
        }
        return encryption.toString();
    }

    public char [][] createMatrix(char [] plainText){
        int rowLength = plainText.length/this.keyLength;
        char [][] matrix = new char[rowLength][keyLength];
        int index = 0;
        loop1:
        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < keyLength; j++){
                if(index >= plainText.length){
                    break loop1;
                }
                matrix[i][j] = plainText[index];
                index++;
            }
        }
        return matrix;
    }

    @Override
    public String decrypt(String ciphertext) {
        int rowLength = ciphertext.length()/keyLength;
        char [][] decryptionMatrix = new char[rowLength][keyLength];
        char [] cipherText = ciphertext.toCharArray();
        int cipherIndex = 0;
        for(int i = 0; i < keyLength; i++){
            for(int j = 0; j < rowLength; j++){
                decryptionMatrix[j][key.get(i)] = cipherText[cipherIndex];
                cipherIndex++;
            }
        }
        StringBuilder decryption = new StringBuilder();
        for(int i = 0; i < decryptionMatrix.length; i++){
            for(int j = 0; j < decryptionMatrix[i].length; j++){
                decryption.append(decryptionMatrix[i][j]);
            }
        }
        return decryption.toString();
    }
}
