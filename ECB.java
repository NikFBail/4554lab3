import java.util.Arrays;

public class ECB {

    /* Method for adding the
     * key and plaintext together
     * to encrypt the plaintext
     * Takes two binary string arrays
     * and adds them together
     */
    public static String[] encrypted(String[] block, String[] key) {
        // Conversions from the Conversions class
        String[] blockPadded = Conversions.padding(block); // Pads the plaintext
        String[] plaintext = Conversions.rightShift(blockPadded); // Creating the plaintext

        // Initializing variables
        int k =0; // Holder for one digit in the binary string of key
        int p = 0; // Holder for one digit in the binary string of plaintext
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");

        // Goes through the array
        for(int i = 0; i < plaintext.length; i++) {
            // Goes through plaintext[i]
            for(int j = 0; j < plaintext[i].length(); j++) {
                // Gets the integer value of one digit in the binary of plaintext[i]
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));

                // Gets the integer value of one digit in the binary of key[i]
                // Use i % key.length so the key will loop around
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                sum = XOR(p, k); // XOR two binary digits

                // Converts sum to a string and adds it to encrypted[i]
                encrypted[i] += Integer.toString(sum);
            }
        }
        return encrypted;
    }

    /* Method for decrypting using
     * the Electronic Code Book method
     * For decryption, the encrypted text
     * is the input, and the output will
     * be the original plaintext
     */
    public static String[] decrypted(String[] encryption, String[] key) {
        // Initializing variables
        int k =0; // Holder for one digit in the binary string of key
        int e = 0; // Holder for one digit in the binary string of encryption
        int sum = 0;
        String[] decrypted = new String[encryption.length];

        // Filling the array with empty strings to it isn't filled with null values
        Arrays.fill(decrypted, "");

        for(int i = 0; i < encryption.length; i++) {
            for(int j = 0; j < encryption[i].length(); j++) {
                // Gets the integer value of one digit in the binary of encryption[i]
                e = Integer.valueOf(encryption[i].substring(j, j + 1));

                // Gets the integer value of one digit in the binary of key[i]
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                sum = XOR(e, k); // XOR two binary digits

                // Converts sum to a string and adds it to decrypted[i]
                decrypted[i] += Integer.toString(sum);
            }
        }

        return decrypted;
    }

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    public static String decrypt(String[] encryption, String[] key) {
        // Conversions from the Conversions class
        String[] decrypted = decrypted(encryption, key);
        String[] removeShift = Conversions.removeShift(decrypted);
        int[] ascii = Conversions.binaryToASCII(removeShift);
        String word = Conversions.asciiToChar(ascii);

        return word;
    }

    // XOR method
    public static int XOR(int x1, int x2) {
        int sum = 0;
        sum = (x1 + x2) % 2;
        return sum;
    }
}
