import java.util.Arrays;

public class CFB {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static int[] cfbIV = {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1};
    private static String[] iv = Conversions.binaryToString(cfbIV);

    /* Method for encrypting using
     * Cipher Feedback
     * First encrypt either the
     * iv (only for the first input)
     * or the output of the encryption
     * of the previous input
     * Then XOR the encryption with
     * the input
     */
    public static String[] encryptCFB(String[] plaintext, String[] key) {
        // Initializing variables
        int initVect =0;
        int k = 0;
        int p = 0;
        int encrypt = 0;
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");

        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                // If the initialization vector goes through the encryption
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = XOR(initVect, k);
                }

                // Otherwise the previous output goes through the encryption, ie XOR k with y2 for determining y3
                else {
                    initVect = Integer.valueOf(encrypted[i - 1].substring(j, j + 1));
                    sum = XOR(initVect, k);
                }

                encrypt = XOR(p, sum);
                encrypted[i] += Integer.toString(encrypt);
            }
        }

        return encrypted;
    }

    public static String[] decrypted(String[] encryption, String[] key) {
        // Initializing variables
        int k =0;
        int e = 0;
        int initVect = 0;
        int decrypt = 0;
        int sum = 0;
        String[] decrypted = new String[encryption.length];

        // Filling the array with empty strings to it isn't filled with null values
        Arrays.fill(decrypted, "");

        for(int i = 0; i < encryption.length; i++) {
            for(int j = 0; j < encryption[i].length(); j++) {
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));
                e = Integer.valueOf(encryption[i].substring(j, j + 1));

                // If we should encrypt the initialization vector
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = XOR(initVect, k);
                }

                // Otherwise encrypt the previous output
                else {
                    initVect = Integer.valueOf(encryption[i - 1].substring(j, j + 1));
                    sum = XOR(initVect, k);
                }

                decrypt = XOR(sum, e);
                decrypted[i] += Integer.toString(decrypt);
            }
        }
        
        return decrypted;
    }

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    public static String decryptCFB(String[] encryption, String[] key) {
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
