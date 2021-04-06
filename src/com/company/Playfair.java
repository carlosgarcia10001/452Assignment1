package com.company;

import java.util.HashMap;
import java.util.TreeSet;

public class Playfair implements CipherInterface {

    final int ROWLENGTH = 5;
    final int COLLENGTH = 5;

    public class Coordinates {
        int row;
        int col;

        public Coordinates(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public enum Crypt {
        ENCRYPTION,
        DECRYPTION
    }

    HashMap<Character, Coordinates> indexLookup; //Used to instantly obtain the location of a letter within the matrix
    char [][] letterMatrix;

    public Playfair(String key){
        letterMatrix = new char[ROWLENGTH][COLLENGTH];
        indexLookup = new HashMap<>();
        setKey(key);
    }

    @Override
    public boolean setKey(String key) {
        TreeSet<Character> alphabet = alphabet();
        int index = 0;
        for(int i = 0; i < key.length(); i++){
            char letter = key.charAt(i);
            if(addLetterToKey(letter, index, alphabet)){
                index++;
            }
        }
        int size = alphabet.size();
        for(int i = 0; i < size; i++){
            char letter = alphabet.first();
            if(addLetterToKey(letter, index, alphabet)){
                index++;
            }
        }
        indexLookup.put('j', indexLookup.get('i')); //i and j share the same location in the matrix
        return true;
    }

    //Creates a TreeSet that contains the characters a-z
    public TreeSet<Character> alphabet(){
        TreeSet<Character> alphabet = new TreeSet<>();
        char letter = 'a';
        while(letter <= 'z'){
            alphabet.add(letter);
            letter++;
        }
        alphabet.remove('j');
        return alphabet;
    }

    public boolean addLetterToKey(char letter, int index, TreeSet<Character> alphabet){
        if(alphabet.contains(letter)) {
            int row = index / ROWLENGTH;
            int col = index % COLLENGTH;
            letterMatrix[row][col] = letter;
            indexLookup.put(letter, new Coordinates(row, col));
            alphabet.remove(letter);
            return true;
        }
        return false;
    }

    @Override
    public String encrypt(String plainText) {
        plainText = preparePlainTextForEncryption(plainText);
        StringBuilder encryption = new StringBuilder();
        for(int i = 0; i < plainText.length(); i+=2){
            char firstLetter = plainText.charAt(i);
            char secondLetter = plainText.charAt(i+1);
            encryption.append(cryptPair(firstLetter, secondLetter, Crypt.ENCRYPTION));
        }
        return encryption.toString();
    }

    public static String preparePlainTextForEncryption(String plainText){
        StringBuilder preparedPlainText = new StringBuilder();
        for(int i = 0; i < plainText.length()-1; i++){
            char letter = Character.toLowerCase(plainText.charAt(i));
            char nextLetter = Character.toLowerCase(plainText.charAt(i+1));
            addPreparedCharacter(preparedPlainText, letter, nextLetter);
        }
        char last = plainText.charAt(plainText.length()-1);
        addPreparedCharacter(preparedPlainText, last);
        if(isOdd(preparedPlainText.length())){
            preparedPlainText.append('x');
        }
        return preparedPlainText.toString();
    }

    //Encrypts or decrypts a pair of letters
    public String cryptPair(char letter1, char letter2, Crypt crypt){
        //When encrypting and letters are in the same row or col, you go right or down (+1).
        //When decrypting and letters are in the same row or col, you go up or left (-1 or +4).
        //+4 is equivalent to -1 because if the end is reached, the letter lookup is meant to wrap around
        //-1%5 (which would occur if evaluating a decryption at row or col index 0),
        //would produce an error, but 4%5 gives the intended result (4) and does not produce an error
        int cryptDifference = crypt==Crypt.ENCRYPTION ? 1 : 4;
        Coordinates coord1 = indexLookup.get(letter1);
        Coordinates coord2 = indexLookup.get(letter2);
        //By default, col1 and col2 are swapped
        int newRow1 = coord1.row;
        int newCol1 = coord2.col;
        int newRow2 = coord2.row;
        int newCol2 = coord1.col;
        if(sameRow(coord1, coord2)){
            newCol1 = (coord1.col+cryptDifference)%COLLENGTH;
            newCol2 = (coord2.col+cryptDifference)%COLLENGTH;
        }
        else if(sameCol(coord1, coord2)){
            newRow1 = (coord1.row+cryptDifference)%ROWLENGTH;
            newRow2 = (coord2.row+cryptDifference)%ROWLENGTH;
        }
        return letterMatrix[newRow1][newCol1] + "" + letterMatrix[newRow2][newCol2];
    }

    public boolean sameRow(Coordinates one, Coordinates two){
        return one.row ==two.row;
    }

    public boolean sameCol(Coordinates one, Coordinates two){
        return one.col == two.col;
    }

    public static boolean addPreparedCharacter(StringBuilder preparedText, char letter, char nextLetter){
        if(Character.isLetter(letter)){
            preparedText.append(letter);
            //If there are two letters in a row, the rule for Playfair is to add 'x'
            if(letter == nextLetter){
                preparedText.append('x');
            }
            return true;
        }
        return false;
    }

    public static boolean addPreparedCharacter(StringBuilder preparedText, char letter){
        if(Character.isLetter(letter)){
            preparedText.append(letter);
            return true;
        }
        return false;
    }

    public static boolean isOdd(int num){
        return num%2==1;
    }

    @Override
    public String decrypt(String ciphertext) {
        StringBuilder decryption = new StringBuilder();
        for(int i = 0; i < ciphertext.length(); i+=2){
            char firstLetter = ciphertext.charAt(i);
            char secondLetter = ciphertext.charAt(i+1);
            decryption.append(cryptPair(firstLetter, secondLetter, Crypt.DECRYPTION));
        }
        return decryption.toString();
    }

}
