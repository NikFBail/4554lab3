public class ECB {

    /* Encryption method using
     * Electronic Code Book
     * Encrypts the input to
     * produce the encryption
     */
    public static String encryptECB(String text, String key) {
        // Initializing variables
        int remaining= text.length() % 5;
        int runsCount = text.length() / 5;
        String result = "";
        String temp;
        char[] input = text.toCharArray();
        char[][] splitInput = new char[runsCount][5];
        
        // Split the data up into an array of arrays of 5 character blocks
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 5; j++) {
                splitInput[i][j] = input[i * 5 + j];
            }
        }

        // Encrypt every block with the key
        for(int i = 0; i < runsCount; i++) {
            temp = new String(Conversions.eBox(splitInput[i], key.toCharArray()));
            result += temp;
        }

        // Pad the last block with 0s if there is a remainder, then finish encrypting
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
            temp = new String(Conversions.eBox(remainingChars, key.toCharArray()));
            result += temp;
        }

        return result;
    }

    /* Decryption method using
     * Electronic Code Book
     * The input is inversely encrypted
     * to produce the final decryption
     */
    public static String decryptECB(String input, String key) {
        // Initializing variables
        int runsCount = input.length() / 35;
        String result = "";
        String temp;
        char[] fullInput = input.toCharArray();
        char[][] splitInput = new char[runsCount][35];

        // Split up the input into an array of arrays of 35 bit blocks
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 35; j++) {
                splitInput[i][j] = fullInput[i * 35 + j];
            }
        }
        // For every array of 35 bit blocks, decrypt using the key and return the resulting character array
        for(int i = 0; i < runsCount; i++) {
            temp = new String(Conversions.deBox(splitInput[i], key.toCharArray()));
            result += temp;
        }

        return result;
    }
}