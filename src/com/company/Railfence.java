package com.company;

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
        return null;
    }
}
