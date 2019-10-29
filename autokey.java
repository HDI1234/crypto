import java.util.*;

public class autokey {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter Plaintext : " );
        String word = input.next();
        System.out.print("Enter XOR Key : " );
        char xorkey = input.next().charAt(0);

        String enc1 = rot13(word);
        String enc2 = encryptDecrypt(enc1, xorkey);
        String dec1 = encryptDecrypt(enc2, xorkey);
        String dec2 = rot13(dec1);

        System.out.println(enc1);
        System.out.println(enc2);
        System.out.println(dec1);
        System.out.println(dec2);


    }

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

    public static String encryptDecrypt(String inputString, char xorkey) {
            // Define String to store encrypted/decrypted String
            String outputString = "";

            // calculate length of input string
            int len = inputString.length();

            for (int i = 0; i < len; i++)
            {
                outputString = outputString +
                        Character.toString((char) (inputString.charAt(i) ^ xorkey));
            }

            return outputString;
        }
        
}
