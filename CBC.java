public class CBC {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static char[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};

    /* Method of encryption using
     * Cipher Block Chaining
     * First XOR the input with either
     * the iv or with the previous output
     * Then encrypt the result to get the encryption
     */
    public static String encryptCBC(String text, String key, String iv) {
        int runsCount = text.length() / 5;
        int remaining = text.length() % 5;
        String result = "";
        String temp = "";
        char[] input = text.toCharArray();
        char[][] splitInput = new char[runsCount][5];
        
        //Split the data up into an array of arrays of 5 character blocks
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 5; j++) {
                splitInput[i][j] = input[i * 5 + j];
            }
        }

        //XOR and encrypt the IV with the first block, use the result to XOR and encrypt the next block, and so on
        for(int i = 0; i < runsCount; i++) {
            temp = new String(Conversions.encryptPlusXOR(splitInput[i], key.toCharArray(), iv));
            result += temp;
            iv = temp;
        }

        //Pad the last block with 0s if there is a remainder, and then finish off encrypting the result
        if(remaining > 0) {
            char[] remainingChars = new char[5];
            for(int i = 0; i < 5; i++) {
                if(i >= remaining) {
                    remainingChars[i] = (char) 0;
                } else {
                    int clean = (runsCount * 5);
                    remainingChars[i] = input[clean + i];
                }

            }
            temp = new String(Conversions.encryptPlusXOR(remainingChars, key.toCharArray(), iv));
            result += temp;
        }

        return result;
    }

    /* Method of decryption using
     * Cipher Block Chaining
     * The input is inversely encrypted
     * Then XOR the result with either
     * the iv or the previous input
     * to get the final decryption
     */
    public static String decryptCBC(String encryption, String key, String iv) {
        int runs = encryption.length() / 35;
        int dec = 0;
        String result = "";
        String temp1 = "";
        String temp2 = "";
        char[] input = encryption.toCharArray();
        char[][] splitInput = new char[runs][35];

        //Split up the input into an array of arrays of 35 bit blocks
        for(int i = 0; i < runs; i++) {
            for(int j = 0; j < 35; j++) {
                splitInput[i][j] = input[i * 35 + j];
            }
        }

        //For every array of 35 bit blocks, decrypt using the key and then XOR the IV to that key for use later.
        //From there, convert the block into its ASCII representation one character (7 bits) at a time, adding the XORD IV along the way. Return the result.
        for(int i = 0; i < runs; i++) {
            temp1 = new String(Conversions.decryptNoChars(splitInput[i], key.toCharArray()));
            char[] xoring = Conversions.XOR(temp1.toCharArray(), iv.toCharArray());
            temp2 = "";
            for(int j = 0; j < xoring.length; j++) {
                temp2 += xoring[j];
                if(temp2.length() == 7) {
                    dec = Integer.parseInt(temp2, 2);
                    result += (char) dec;
                    temp2 = "";
                }
            }
            iv = String.valueOf(splitInput[i]);
        }

        return result;
    }
}