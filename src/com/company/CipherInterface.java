package com.company;

public interface CipherInterface {
    boolean setKey(String key);
    String encrypt(String plaintext);
    String decrypt(String cipherText);
}
