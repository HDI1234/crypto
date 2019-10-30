import java.util.*;
import javax.swing.JOptionPane;
public class GFG {

    public static void main(String[] args) {

        //Combination of XOR Cipher and ROT13 Cipher
        String word = JOptionPane.showInputDialog("Plaintext");
        char xorkey = JOptionPane.showInputDialog("XOR Key").charAt(0);

        //Scanner input = new Scanner(System.in);
        //System.out.print("Enter Plaintext : " ); //Plaintext input
        //String word = input.next();
        //System.out.print("Enter XOR Key : " ); //XOR KEY input (SATU HURUF SAHAJA)
        //char xorkey = input.next().charAt(0);

        String enc1 = rot13(word); //ENCRYPT PLAINTEXT WITH ROT13 CIPHER
        String enc2 = encryptDecrypt(enc1, xorkey); //ENCRYPT 1st ENCRYPTED TEXT WITH XOR CIPHER
        String dec1 = encryptDecrypt(enc2, xorkey); //DECRYPT 2nd ENCRYPTED TEXT WITH XOR CIPHER
        String dec2 = rot13(dec1); //DECRYPT LAST TEXT WITH ROT13 CIPHER

        JOptionPane.showMessageDialog(null,"Encrypted ROT13 Text  " + enc1 + "\r\n\n"
                + "Encrypted XOR Text  " + enc2 + "\r\n\n"
                + "Decrypted ROT13 Text  " + dec1 + "\r\n\n"
                + "Decrypted XOR Text  " + dec2 );
        System.out.println(enc1); //Display
        System.out.println(enc2); //All
        System.out.println(dec1); //Encrypted/Decrypted
        System.out.println(dec2); //Text


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

    //XOR ENCRYPTED/DECRYPTED FUNCTION
    //Nak decrypt and encrypt dua dua pakai function sama
    //XOR Cipher
    public static String encryptDecrypt(String plainText, char xorkey) {
            // Define String to store encrypted/decrypted String
            String result = "";

            // calculate length of input string
            int len = plainText.length();

            for (int i = 0; i < len; i++)
            {
                result = result + (char) (plainText.charAt(i) ^ xorkey);
            }

            return result;
        }

}
