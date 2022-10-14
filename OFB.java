import java.util.Arrays;

public class OFB {
    
    private static int[] ofbIV = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1};
    private static String[] iv = Conversions.binaryToString(ofbIV);

    /* Method for encryption using
     * Output Feedback
     * First encrypt either the iv
     * (only for the first input) with
     * the key, or the previous eBox
     * gets encrypted with the key
     * Then XOR the result with the input
     * Need to store the eBox encryptions
     */
    public static String[] encryptOFB(String[] plaintext, String[] key) {
        int initVect = 0;
        int k = 0;
        int p = 0;
        int encrypt = 0;
        int sum = 0;
        String[] eBox = new String[plaintext.length]; // Array for storing the values of the encryption box
        String[] encrypted = new String[plaintext.length];

        // Filling the arrays with "" so they aren't null
        Arrays.fill(encrypted, "");
        Arrays.fill(eBox, "");
        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                // If we use the initial vector for encryption
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = XOR(initVect, k);
                    eBox[i] += Integer.toString(sum); // Storing the value of the eBox encryption
                }
                // Else use the value of the previous eBox fo encryption
                else {
                    initVect = Integer.valueOf(eBox[i - 1].substring(j, j + 1));
                    sum = XOR(initVect, k);
                    eBox[i] += Integer.toString(sum); // Storing the value of the eBox encryption
                }

                encrypt = XOR(p, sum);
                encrypted[i] += Integer.toString(encrypt);
            }
        }
        return encrypted;
    }

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    // public static String decryptCFB(String[] encryption, String[] key) {
    //     String[] decrypted = decrypted(encryption, key);
    //     String[] removeShift = Conversions.removeShift(decrypted);
    //     int[] ascii = Conversions.binaryToASCII(removeShift);
    //     String word = Conversions.asciiToChar(ascii);

    //     return word;
    // }

    // XOR method
    public static int XOR(int x1, int x2) {
        int sum = 0;
        sum = (x1 + x2) % 2;
        return sum;
    }
}
