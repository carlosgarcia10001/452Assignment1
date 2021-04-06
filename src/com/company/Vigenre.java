package com.company;

public class Vigenre implements CipherInterface{
    char [] key;
    char [][] matrix;
    final int ALPHABETOFFSET = 97;
    final int ALPHABETLENGTH = 26;
    public Vigenre(String key){
        matrix = new char[26][26];
        setKey(key);
        setMatrix();
    }

    @Override
    public boolean setKey(String key) {
        this.key = key.toCharArray();
        return true;
    }

    public void setMatrix(){
        for(int i = 0; i < ALPHABETLENGTH; i++){
            for(int j = 0; j < ALPHABETLENGTH; j++){
                int index = (j+i)%ALPHABETLENGTH;
                char letter = (char) (ALPHABETOFFSET+(index));
                matrix[i][j] = letter;
            }
        }
    }

    public void printMatrix(){
        for(char [] i: matrix){
            for(char j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    @Override
    public String encrypt(String plaintext) {
        char [] plainText = plaintext.toCharArray();
        StringBuilder encryption = new StringBuilder();
        for(int i = 0; i < plaintext.length(); i++){
            int row = convertLetterToIndex(key[i]);
            int col = convertLetterToIndex(plainText[i]);
            encryption.append(matrix[row][col]);
        }
        return encryption.toString();
    }

    public int convertLetterToIndex(char letter){
        return (int)(letter-ALPHABETOFFSET);
    }

    @Override
    public String decrypt(String ciphertext) {
        char [] cipherText = ciphertext.toCharArray();
        StringBuilder decryption = new StringBuilder();
        for(int i = 0; i < cipherText.length; i++){
            int row = convertLetterToIndex(key[i]);
            char letter = (char)(ALPHABETOFFSET+((ALPHABETLENGTH-row+convertLetterToIndex(cipherText[i]))%ALPHABETLENGTH));
            decryption.append(letter);
        }
        return decryption.toString();
    }
}
