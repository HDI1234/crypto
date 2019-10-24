/*
   Autokey encryption and decryption
*/
import java.util.*;
import java.lang.*;
import java.math.*;

public class Autokey{
    //private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args){
        String word = "MAIRA";
        String key = "P";  //15 - P

        String enc = encryptLine(word,key);
        System.out.println("Plaintext : "+word);
        System.out.println("Encrypted : "+enc);
        System.out.println("Decrypted : "+AutoDecryption(enc,key));

    }

    public static String encryptLine(String word, String key) //Encrypts word character by character and returns concatenated encrypted String
    {
        HashMap<Character, Integer> charMap =  new HashMap<Character, Integer>(); //Hashmap to represent the top row of the Porta Cipher A-Z

        /**
         * Populates Hashmap
         */
        charMap.put('A', 0);
        charMap.put('B', 1);
        charMap.put('C', 2);
        charMap.put('D', 3);
        charMap.put('E', 4);
        charMap.put('F', 5);
        charMap.put('G', 6);
        charMap.put('H', 7);
        charMap.put('I', 8);
        charMap.put('J', 9);
        charMap.put('K', 10);
        charMap.put('L', 11);
        charMap.put('M', 12);
        charMap.put('N', 13);
        charMap.put('O', 14);
        charMap.put('P', 15);
        charMap.put('Q', 16);
        charMap.put('R', 17);
        charMap.put('S', 18);
        charMap.put('T', 19);
        charMap.put('U', 20);
        charMap.put('V', 21);
        charMap.put('W', 22);
        charMap.put('X', 23);
        charMap.put('Y', 24);
        charMap.put('Z', 25);

        HashMap<Character, Integer> charMapKey =  new HashMap<Character, Integer>(); //Hashmap to represent the key column of the Porta Cipher eg. AB, BC, DE etc.

        /**
         * Populates Hashmap
         */
        charMapKey.put('A', 0);
        charMapKey.put('B', 0);
        charMapKey.put('C', 1);
        charMapKey.put('D', 1);
        charMapKey.put('E', 2);
        charMapKey.put('F', 2);
        charMapKey.put('G', 3);
        charMapKey.put('H', 3);
        charMapKey.put('I', 4);
        charMapKey.put('J', 4);
        charMapKey.put('K', 5);
        charMapKey.put('L', 5);
        charMapKey.put('M', 6);
        charMapKey.put('N', 6);
        charMapKey.put('O', 7);
        charMapKey.put('P', 7);
        charMapKey.put('Q', 8);
        charMapKey.put('R', 8);
        charMapKey.put('S', 9);
        charMapKey.put('T', 9);
        charMapKey.put('U', 10);
        charMapKey.put('V', 10);
        charMapKey.put('W', 11);
        charMapKey.put('X', 11);
        charMapKey.put('Y', 12);
        charMapKey.put('Z', 12);

        char encryptionArray[] = {'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //Array to store first half of the cipher
        char encryptionArray2[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'}; //Array to store second half of the cipher

        /**
         * Convert the word to upper-case
         * Convert the key to upper-case
         */
        word = word.toUpperCase();
        key = key.toUpperCase();

        /**
         * Creates a new StringBuilder
         * Loops for length of word and appends to builder
         * Ensures that the key repeats if word is longer than the key
         */
        String subkey = key + word;
        subkey = subkey.substring(0,subkey.length()-key.length());

        String sb = "";

        for(int i = 0; i < word.length(); i++) //Loop for the length of the word
        {
            int lookup = 0; //Used to look for the encrypted character
            char wordLetter = word.charAt(i); //Word Character at position i
            char keyLetter = subkey.charAt(i); //Key Character at position i

            if(wordLetter >= 'A' && wordLetter <= 'Z') //Only encrypt letters
            {
                if(charMap.get(wordLetter) > 12 && charMap.get(wordLetter) <= 25) //If word is in the second half of the cipher A-M
                {
                    lookup = ((charMap.get(wordLetter)-charMapKey.get(keyLetter)) % 13); //Calculate position of encrypted character
                    sb += encryptionArray2[lookup]; //Search array 2 and append encrypted character to sb
                }

                else //Otherwise If word is in the first half of the cipher N-Z
                {
                    lookup = ((charMap.get(wordLetter)+charMapKey.get(keyLetter)) % 13); //Calculate position of encrypted character
                    sb += encryptionArray[lookup]; //Search array and append encrypted character to sb
                }
            }

            else //Add non-letter characters to the encryptedWord
            {
                sb += word.charAt(i);
            }
        }

        return sb;
    }

    public static String AutoDecryption(String word,String key){
        HashMap<Character, Integer> charMap =  new HashMap<Character, Integer>(); //Hashmap to represent the top row of the Porta Cipher A-Z

        /**
         * Populates Hashmap
         */
        charMap.put('A', 0);
        charMap.put('B', 1);
        charMap.put('C', 2);
        charMap.put('D', 3);
        charMap.put('E', 4);
        charMap.put('F', 5);
        charMap.put('G', 6);
        charMap.put('H', 7);
        charMap.put('I', 8);
        charMap.put('J', 9);
        charMap.put('K', 10);
        charMap.put('L', 11);
        charMap.put('M', 12);
        charMap.put('N', 13);
        charMap.put('O', 14);
        charMap.put('P', 15);
        charMap.put('Q', 16);
        charMap.put('R', 17);
        charMap.put('S', 18);
        charMap.put('T', 19);
        charMap.put('U', 20);
        charMap.put('V', 21);
        charMap.put('W', 22);
        charMap.put('X', 23);
        charMap.put('Y', 24);
        charMap.put('Z', 25);

        HashMap<Character, Integer> charMapKey =  new HashMap<Character, Integer>(); //Hashmap to represent the key column of the Porta Cipher eg. AB, BC, DE etc.

        /**
         * Populates Hashmap
         */
        charMapKey.put('A', 0);
        charMapKey.put('B', 0);
        charMapKey.put('C', 1);
        charMapKey.put('D', 1);
        charMapKey.put('E', 2);
        charMapKey.put('F', 2);
        charMapKey.put('G', 3);
        charMapKey.put('H', 3);
        charMapKey.put('I', 4);
        charMapKey.put('J', 4);
        charMapKey.put('K', 5);
        charMapKey.put('L', 5);
        charMapKey.put('M', 6);
        charMapKey.put('N', 6);
        charMapKey.put('O', 7);
        charMapKey.put('P', 7);
        charMapKey.put('Q', 8);
        charMapKey.put('R', 8);
        charMapKey.put('S', 9);
        charMapKey.put('T', 9);
        charMapKey.put('U', 10);
        charMapKey.put('V', 10);
        charMapKey.put('W', 11);
        charMapKey.put('X', 11);
        charMapKey.put('Y', 12);
        charMapKey.put('Z', 12);

        char decryptionArray[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //Array to store first half of the cipher

        /**
         * Convert the word to upper-case
         * Convert the key to upper-case
         */
        word = word.toUpperCase();
        key = key.toUpperCase();

        String subkey = key + word;
        subkey = subkey.substring(0,subkey.length()-key.length());

        String sb = "";

        int len = word.length();
        String current = subkey;

        for(int i = 0; i < word.length(); i++) //Loop for the length of the word
        {
            int lookup = 0; //Used to look for the encrypted character
            char wordLetter = word.charAt(i); //Word Character at position i
            char keyLetter = current.charAt(i); //Key Character at position i

            if(wordLetter >= 'A' && wordLetter <= 'Z') //Only encrypt letters
            {
                lookup = ((charMap.get(wordLetter)+charMapKey.get(keyLetter)) % 26); //Calculate position of decrypted character
                lookup = (lookup<0)? lookup + 26 : lookup;
                sb+=decryptionArray[lookup]; //Search array 2 and append decrypted character to sb
                current += decryptionArray[lookup];
            }

            else //Add non-letter characters to the encryptedWord
            {
                sb += word.charAt(i);
            }
        }
        /*for(int x=0;x<len;x++){
            int get1 = alpha.indexOf(text.charAt(x));
            int get2 = alpha.indexOf(current.charAt(x));

            int total = (get1 - get2)%26;
            total = (total<0)? total + 26 : total;
            sb += alpha.charAt(total);

            current += alpha.charAt(total);
        }*/
        return sb;
    }
}
