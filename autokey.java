import java.util.*;
import javax.swing.JOptionPane;
public class GFG {

    public static void main(String[] args) {

        //Combination of XOR Cipher and ROT13 Cipher
        String word = JOptionPane.showInputDialog("Plaintext");
        String key1 = JOptionPane.showInputDialog("Key 1");
        String key2 = JOptionPane.showInputDialog("Key 2");


        String enc1 = rot13(word); //ENCRYPT PLAINTEXT WITH ROT13 CIPHER
        String enc2 = encryptPorta(enc1, key1); //ENCRYPT 1st ENCRYPTED TEXT WITH XOR CIPHER
        String enc3 = encryptAutokey(enc2, key2);
        String dec1 = decryptAutokey(enc3, key2);
        String dec2 = decryptPorta(dec1, key1); //DECRYPT 2nd ENCRYPTED TEXT WITH XOR CIPHER
        String dec3 = rot13(dec2); //DECRYPT LAST TEXT WITH ROT13 CIPHER

        JOptionPane.showMessageDialog(null,"Encrypted ROT13 Text:  " + enc1 + "\r\n\n"
                + "Encrypted Porta Text:  " + enc2 + "\r\n\n"
                + "Encrypted Autokey Text:  " + enc3 + "\r\n\n"
                + "Decrypted Autokey Text:  " + dec1 + "\r\n\n"
                + "Decrypted Porta Text:  " + dec2 + "\r\n\n"
                + "Decrypted ROT13 Text:  " + dec3 );
//        System.out.println(enc1); //Display
//        System.out.println(encryptPorta("humaira","maira"));
//        System.out.println(enc2); //All
//        System.out.println(dec1); //Encrypted/Decrypted
//        System.out.println(dec2); //Text


    }

    //ROT13 ENCRYPTED/DECRYPTED FUNCTION
    //Nak decrypt and encrypt dua dua pakai function sama
    //ROT13 cipher substitute letter
    public static String rot13(String word){
        String cipher = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;

            cipher += c;
        }

        return cipher;
    }

    public static String decryptPorta(String word, String key) //Decrypts word character by character and returns concatenated decrypted String
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

        char decryptionArray[] = {'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //Array to store first half of the cipher
        char decryptionArray2[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'}; //Array to store second half of the cipher

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
        StringBuilder builder = new StringBuilder(word.length() + key.length() - 1);

        while (builder.length() < word.length())
        {
            builder.append(key);
        }

        builder.setLength(word.length()); //Sets length to word length
        String keyToUse = builder.toString(); //Elongated key

        String decryptedLine = ""; //Decrypted word

        StringBuilder sb = new StringBuilder(); //New StringBuilder to create decrypted word from characters

        for(int i = 0; i < word.length(); i++) //Loop for length of the word
        {
            char wordLetter = word.charAt(i);  //Character at position i in the word
            char keyLetter = keyToUse.charAt(i); //Character at position i in the key
            int lookup = 0; //Integer used to look up encrypted character in the decryption array

            if(wordLetter >= 'A' && wordLetter <= 'Z') //Only encrypt letters
            {
                if(charMap.get(wordLetter) > 12) //If word is in the second half of the cipher A-M
                {
                    lookup = ((charMap.get(wordLetter)-charMapKey.get(keyLetter)) % 13); //Calculate position of decrypted character
                    sb.append(decryptionArray2[lookup]); //Search array 2 and append decrypted character to sb
                }

                else //Otherwise If word is in the first half of the cipher N-Z
                {
                    lookup = ((charMap.get(wordLetter)+charMapKey.get(keyLetter)) % 13); //Calculate position of decrypted character
                    sb.append(decryptionArray[lookup]); //Search array and append decrypted character to sb
                }
            }

            else //Add non-letter characters to the decryptedWord
            {
                sb.append(word.charAt(i));
            }
        }

        decryptedLine = sb.toString(); ////Add non-letter characters to the encryptedWord

        return decryptedLine;
    }

    public static String encryptPorta(String word, String key) //Encrypts word character by character and returns concatenated encrypted String
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
        StringBuilder builder = new StringBuilder(word.length() + key.length() - 1); //New StringBuilder for the extended key

        while (builder.length() < word.length())
        {
            builder.append(key);
        }

        builder.setLength(word.length()); //Sets length to word length

        String keyToUse = builder.toString(); //Elongated key

        String encryptedLine = ""; //Encrypted word

        StringBuilder sb = new StringBuilder(); //New StringBuilder to create encrypted word from characters


        for(int i = 0; i < word.length(); i++) //Loop for the length of the word
        {
            int lookup = 0; //Used to look for the encrypted character
            char wordLetter = word.charAt(i); //Word Character at position i
            char keyLetter = keyToUse.charAt(i); //Key Character at position i

            if(wordLetter >= 'A' && wordLetter <= 'Z') //Only encrypt letters
            {

                if(charMap.get(wordLetter) > 12 && charMap.get(wordLetter) <= 25) //If word is in the second half of the cipher A-M
                {
                    lookup = ((charMap.get(wordLetter)-charMapKey.get(keyLetter)) % 13); //Calculate position of encrypted character
                    sb.append(encryptionArray2[lookup]); //Search array 2 and append encrypted character to sb
                }

                else //Otherwise If word is in the first half of the cipher N-Z
                {
                    lookup = ((charMap.get(wordLetter)+charMapKey.get(keyLetter)) % 13); //Calculate position of encrypted character
                    sb.append(encryptionArray[lookup]); //Search array and append encrypted character to sb
                }
            }

            else //Add non-letter characters to the encryptedWord
            {
                sb.append(word.charAt(i));
            }
        }

        encryptedLine = sb.toString(); //Concatenated String

        return encryptedLine;
    }

    private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encryptAutokey(String text,String key){
        int len = text.length();

        String subkey = key + text;
        subkey = subkey.substring(0,subkey.length()-key.length());

        String sb = "";
        for(int x=0;x<len;x++){
            int get1 = alpha.indexOf(text.charAt(x));
            int get2 = alpha.indexOf(subkey.charAt(x));

            int total = (get1 + get2)%26;

            sb += alpha.charAt(total);
        }

        return sb;
    }

    public static String decryptAutokey(String text,String key){
        int len = text.length();

        String current = key;
        String sb ="";

        for(int x=0;x<len;x++){
            int get1 = alpha.indexOf(text.charAt(x));
            int get2 = alpha.indexOf(current.charAt(x));

            int total = (get1 - get2)%26;
            total = (total<0)? total + 26 : total;
            sb += alpha.charAt(total);

            current += alpha.charAt(total);
        }
        return sb;
    }

}
