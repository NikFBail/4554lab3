import java.util.Arrays;

public class CBC {
    
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
    public static String[] encryptCBC(String[] plaintext, String[] key) {
        int initVect =0; // Holder for one digit in the binary string of iv
        int k = 0; // Holder for one digit in the binary string of the key
        int p = 0; // Holder for one digit in the binary string of plaintext
        int encrypt = 0;
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");
        // Goes through the array
        for(int i = 0; i < plaintext.length; i++) {
            // Goes through plaintext[i]
            for(int j = 0; j < plaintext[i].length(); j++) {
                // Gets one digit of the binary string key
                // Use i % key.length so the key will loop around
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));
                // Gets one digit of the binary string plaintext
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));

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

                encrypt = XOR(sum, k);
                encrypted[i] += Integer.toString(encrypt);

            }
        }

        return encrypted;
    }

    /* Method for decrypting using
     * the cipher block chaining method
     * Decrypt the input with the key,
     * and either XOR with the initial vector
     * (for the first input)
     * or XOR with the previous output
     */
    public static String[] decrypted(String[] encryption, String[] key) {
        int k =0; // Holder for one digit in the binary string of key
        int e = 0; // Holder for one digit in the binary string of encryption
        int initVect = 0; // Holder for one digit in the binary string of iv
        int decrypt = 0;
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
