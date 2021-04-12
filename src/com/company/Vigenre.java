package com.company;

public class Vigenre implements CipherInterface{
    char [] key;
    final int ALPHABETOFFSET = 97;
    final int ALPHABETLENGTH = 26;
    Boolean keySet = false;
    public Vigenre(String key){
        this.keySet = setKey(key);

    }

    @Override
    public boolean setKey(String key) {
        this.key = key.toCharArray();
        return true;
    }

    @Override
    public String encrypt(String plaintext) {
        char [] plainText = plaintext.toCharArray();
        StringBuilder encryption = new StringBuilder();
        for(int i = 0; i < plainText.length; i++){
            int row = convertLetterToIndex(key[i]);
            int col = convertLetterToIndex(plainText[i]);
            encryption.append((char)(ALPHABETOFFSET+(row+col)%ALPHABETLENGTH));
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
            int col = convertLetterToIndex(cipherText[i]);
            char letter = (char)(ALPHABETOFFSET+((ALPHABETLENGTH-row+col)%ALPHABETLENGTH));
            decryption.append(letter);
        }
        return decryption.toString();
    }
}
