import java.util.Arrays;

public class ECB {

    /* Method for encrypting using
     * the Electronic Code Book method
     * Just encrypts the input with the key
     * to produce the encryption
     */
    public static String[] encryptedECB(String[] block, String[] key) {
        // Conversions from the Conversions class
        String[] blockPadded = Conversions.padding(block); // Pads the plaintext
        String[] encrypted = new String[block.length];
        int counter = 0;
        
        for(int i = 0; i < blockPadded.length; i++) {
            encrypted[i] = Conversions.eBox(blockPadded, key, counter);
            counter++;
        }
        
        return encrypted;
    }

    /* Method for decrypting using
     * the Electronic Code Book method
     * For decryption, use the inverse
     * of encryption with the key
     */
    public static String decryptedECB(String[] encryption, String[] key) {
        // Initializing variables
        String[] decrypted = new String[encryption.length];
        int[] ascii = new int[decrypted.length];
        String word = "";
        int counter = 0;

        for(int i = 0; i < encryption.length; i++) {
            decrypted[i] = Conversions.deBox(encryption, key, counter);
            counter++;
        }

        decrypted = Conversions.removeShift(decrypted);  
        ascii = Conversions.binaryToASCII(decrypted);
        word = Conversions.asciiToChar(ascii);
        return word;
    }
}
