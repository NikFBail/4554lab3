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
        int k = 0;
        int p = 0;
        int encrypt = 0;
        int sum = 0;
        String[] encrypted = new String[blockPadded.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");

        for(int i = 0; i < blockPadded.length; i++) {
            for(int j = 0; j < blockPadded[i].length(); j++) {
                p = Integer.valueOf(blockPadded[i].substring(j, j + 1));
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                // If we should XOR the plaintext and the initial vector
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = XOR(p, initVect);
                }

                // Otherwise XOR the plaintext with the last output, ie XOR x3 with y2
                else {
                    // Getting the right digit of the previous output
                    initVect = Integer.valueOf(encrypted[i - 1].substring(j, j + 1));
                    sum = XOR(p, initVect);
                }
                encrypted[i] += Integer.toString(sum);
            }
            
        }
        encrypted = eBox(encrypted, key);
        return encrypted;
    }

    // Encryption box operations
    // What happens in the Ebox stays in the Ebox
    public static String[] eBox(String[] text, String[] key) {
        // Initializing variables
        int keyVal = 0;
        int textVal = 0;
        String[] result = new String[text.length];

        System.out.println(Arrays.toString(text));
        // Doing the right shift
        text = Conversions.rightShift(text);

        Arrays.fill(result, "");
        for(int i = 0; i < text.length; i++) {
            for(int j = 0; j < text[i].length(); j++) {
                textVal = Integer.valueOf(text[i].substring(j, j + 1));
                keyVal = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                result[i] += Integer.toString(XOR(textVal, keyVal));
            }
        }
        return result;
    }

    /* Method for decrypting using
     * the cipher block chaining method
     * Decrypt the input with the key,
     * and either XOR with the initial vector
     * (for the first input)
     * or XOR with the previous output
     */
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
                e = Integer.valueOf(encryption[i].substring(j, j + 1));
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));
                
                sum = XOR(e, k);

                // If we should XOR sum with the initialization vector
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    decrypt = XOR(sum, initVect);
                }

                // Otherwise XOR sum with the previous output, ie XOR y3 with x2
                else {
                    initVect = Integer.valueOf(encryption[i - 1].substring(j, j + 1));
                    decrypt = XOR(sum, initVect);
                }

                decrypted [i] += Integer.toString(decrypt);
            }
        }

        return decrypted;
    }

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    public static String decryptCBC(String[] encryption, String[] key) {
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
