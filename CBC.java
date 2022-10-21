import java.util.Arrays;

public class CBC {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static char[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};

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
    public static String encryptCBC(String text, String key) {
        // Initializing variables
        int runsCount = 0;
        int remaining= text.length() % 5;
        String result = "";
        char[] input = text.toCharArray();
        char[] keyChar = key.toCharArray();
        char[][] splitInput = new char[runsCount][text.length()];
        
        // Split the data up into an array of arrays of 5 character blocks
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < text.length(); j++) {
                splitInput[i][j] = input[i * 5 + j];
            }
        }
        
        if()


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
        int counter = 0;

        // Filling the array with empty strings to it isn't filled with null values
        Arrays.fill(decrypted, "");
        

        for(int i = 0; i < encryption.length; i++) {
            decrypted[i] = Conversions.deBox(encryption, key, counter);
            counter++;
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
