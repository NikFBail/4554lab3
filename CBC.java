public class CBC {
    /* Method of encryption using
     * Cipher Block Chaining
     * First XOR the input with either
     * the iv or with the previous output
     * Then encrypt the result to get the encryption
     */
    public static String encryptCBC(String text, String key, String iv) {
        // Initializing variables
        int runsCount = text.length() / 5;
        int remaining = text.length() % 5;
        String result = "";
        String temp = "";
        char[] input = text.toCharArray();
        char[][] splitInput = new char[runsCount][5];
        
        // Split the plaintext into blocks of length 5
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 5; j++) {
                splitInput[i][j] = input[i * 5 + j];
            }
        }

        // eBox step with some modification
        // Allows for XOR-ing with either the iv or the previous encrypted result
        // Stores the previous encryption so it can be used for the next loop
        for(int i = 0; i < runsCount; i++) {
            temp = new String(Conversions.encryptPlusXOR(splitInput[i], key.toCharArray(), iv));
            result += temp;
            iv = temp;
        }

        // Checks if padding is needed (so if the length of the text wasn't a multiple of 5)
        // Then finishes the encryption method
        if(remaining > 0) {
            char[] remainingChars = new char[5];
            for(int i = 0; i < 5; i++) {
                if(i >= remaining) {
                    remainingChars[i] = (char) 0;
                } else {
                    remainingChars[i] = input[(runsCount * 5) + i];
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
        // Initializing variables
        int runsCount = encryption.length() / 35;
        int dec = 0;
        String result = "";
        String decrypt = "";
        String ivXOR = "";
        char[] input = encryption.toCharArray();
        char[][] splitInput = new char[runsCount][35];

        // Split up the input into 35 bit blocks
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 35; j++) {
                splitInput[i][j] = input[i * 35 + j];
            }
        }

        // For every array of 35 bit blocks, decrypt using the key and then XOR the IV to that key for use later
        // From there, convert each consecutive 7 bits into their ASCII table value,
        // adding the result of XOR-ing the key and IV every times
        for(int i = 0; i < runsCount; i++) {
            decrypt = new String(Conversions.decryptNoChars(splitInput[i], key.toCharArray()));
            char[] xoring = Conversions.XOR(decrypt.toCharArray(), iv.toCharArray());
            ivXOR = "";
            for(int j = 0; j < xoring.length; j++) {
                ivXOR += xoring[j];
                if(ivXOR.length() == 7) {
                    dec = Integer.parseInt(ivXOR, 2);
                    result += (char) dec;
                    ivXOR = "";
                }
            }
            iv = String.valueOf(splitInput[i]);
        }

        return result;
    }
}