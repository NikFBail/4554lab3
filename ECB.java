import java.util.Arrays;

public class ECB {

    /* Method for adding the
     * key and plaintext together
     * to encrypt the plaintext
     * Takes two binary string arrays
     * and adds them together
     */
    public static String[] encryptedECB(String[] block, String[] key) {
        // Conversions from the Conversions class
        String[] blockPadded = Conversions.padding(block); // Pads the plaintext
        String[] encrypted = new String[block.length];

        encrypted = Conversions.eBox(blockPadded, key);
        return encrypted;
    }

    /* Method for decrypting using
     * the Electronic Code Book method
     * For decryption, the encrypted text
     * is the input, and the output will
     * be the original plaintext
     */
    public static String decryptedECB(String[] encryption, String[] key) {
        // Initializing variables
        String[] decrypted = new String[encryption.length];
        int[] ascii = new int[decrypted.length];
        String word = "";

        decrypted = Conversions.deBox(encryption, key);
        ascii = Conversions.binaryToASCII(decrypted);
        word = Conversions.asciiToChar(ascii);
        return word;
    }

    // XOR method
    public static int XOR(int x1, int x2) {
        int sum = 0;
        sum = (x1 + x2) % 2;
        return sum;
    }
}
