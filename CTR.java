import java.util.Arrays;

public class CTR {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static int[] ctrIV = {1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1};
    private static String[] iv = new String[35];

    /* Method for implementing an IV
     * with a 16 bit counter
     * First 19 bits will be the randomly generated IV
     */
    // Return String[]
    public static void formalCTRiv(int counter, int[] ctrIV) {
        
    }

    /* Method for encryption using
     * Counter Mode
     * First encrypt the IV
     * (that includes a 16 bit counter)
     * with the key
     * Then XOR the result with the input
     * to produce the output
     * 
     * The encryption method is the decryption method too
     */
    public static String[] encryptDecryptCTR(String[] plaintext, String[] key) {
        // Initializing variables
        int initVect = 0;
        int k = 0;
        int p = 0;
        int encrypt = 0;
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        // Fill encrypted with non-null values
        Arrays.fill(encrypted, "");

        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));
                initVect = Integer.valueOf(iv[i].substring(j, j + 1));

                encrypt = XOR(initVect, k); // Actual eBox operation
                sum = XOR(encrypt, p); // XOR-ing

                encrypted[i] += Integer.toString(sum);
            }
        }

        return encrypted;
    }

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    public static String decryptCTR(String[] encryption, String[] key) {
        // Conversions from the Conversions class
        String[] decrypted = encryptDecryptCTR(encryption, key);
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
