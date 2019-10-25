import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class porta1 {

        // Big O: Best = O(N). Worst = 0(N)
        // The statement inside the while loop runs as many times as there are lines in the BufferedReader
        public static void main(String[] args)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter plaintext : " );
            String word = input.next();
            System.out.print("Enter key : " );
            String key = input.next();

            String enc = encryptLine(word,key);
            System.out.println("Plaintext : "+word);
            System.out.println("Encrypted : "+enc);
            System.out.println("Decrypted : "+decryptLine(enc,key));

        }

        // Big O: Best = O(log(n)). Worst = O(log(n))
        // The rational behind this being O(log(n)) is that it contains searching of
        // a HashMap which would be O(log(n)) and would be the slowest of all the big O notations for the various expressions in this method.

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

    public static String decryptLine(String word, String key) //Decrypts word character by character and returns concatenated decrypted String
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


}
