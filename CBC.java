import java.util.Arrays;

public class CBC {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static int[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};
    private static String[] iv = Conversions.binaryToString(cbcIV);

    /* Method for
     * encrypting using cipher block chaining
     * First xor the input with either 
     * the iv (only for the first input)
     * or the output of the encryption
     * of the previous input
     * Then encrypt with the key
     * The output is then xor-ed with the
     * next input
     */
    public static String[] encryptCBC(String[] block, String[] key) {
        String[] blockPadded = Conversions.padding(block); // Pads the plaintext
        // Initializing variables
        int initVect =0;
        int p = 0;
        int sum = 0;
        String[] encrypted = new String[blockPadded.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");

        for(int i = 0; i < blockPadded.length; i++) {
            for(int j = 0; j < blockPadded[i].length(); j++) {
                p = Integer.valueOf(blockPadded[i].substring(j, j + 1));

                // If we should XOR the plaintext and the initial vector
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = Conversions.XOR(p, initVect);
                }

                // Otherwise XOR the plaintext with the last output, ie XOR x3 with y2
                else {
                    // Getting the right digit of the previous output
                    initVect = Integer.valueOf(encrypted[i - 1].substring(j, j + 1));
                    sum = Conversions.XOR(p, initVect);
                }
                encrypted[i] += Integer.toString(sum);
            }
        }
        encrypted = Conversions.eBox(encrypted, key);
        return encrypted;
    }

    

    /* Method for decrypting using
     * the cipher block chaining method
     * Decrypt the input with the key,
     * and either XOR with the initial vector
     * (for the first input)
     * or XOR with the previous output
     */
    public static String decryptedCBC(String[] encryption, String[] key) {
        // Initializing variables
        int decryptVal = 0;
        int initVect = 0;
        int sum = 0;
        String[] decrypted = new String[encryption.length];
        String result = "";

        // Filling the array with empty strings to it isn't filled with null values
        Arrays.fill(decrypted, "");
        decrypted = Conversions.deBox(encryption, key);

        for(int i = 0; i < encryption.length; i++) {
            for(int j = 0; j < encryption[i].length(); j++) {
                decryptVal = Integer.valueOf(decrypted[i].substring(j, j + 1));

                // If we should XOR sum with the initialization vector
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = Conversions.XOR(decryptVal, initVect);
                }

                // Otherwise XOR sum with the previous output, ie XOR y3 with x2
                else {
                    initVect = Integer.valueOf(encryption[i - 1].substring(j, j + 1));
                    sum = Conversions.XOR(decryptVal, initVect);
                }

                decrypted [i] += Integer.toString(sum);
            }
        }
        result = Conversions.asciiToChar(Conversions.binaryToASCII(decrypted));
        return result;
    }
}
