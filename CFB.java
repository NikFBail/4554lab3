public class CFB {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static String cfbIV = "00111010110011011100011101100100011";

    public static String encryptCFB(String plaintext, String key, String iv) {
        // Initializing variables
        String result = "";
        String temp = "";
        int runsCount = plaintext.length() / 5;
        int remaining = plaintext.length() % 5;
        char[] input = plaintext.toCharArray();
        char[][] splitInput = new char[runsCount][5];
        

        // Split the plaintext into blocks of length 5
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 5; j++) {
                splitInput[i][j] = input[i * 5 + j];
            }
        }

        // Encrypt the iv for the first block, then XOR with the input to get the output
        // The previous output is then used to encrypt with the key to start the next encryption loop
        for(int i = 0; i < runsCount; i++) {
            char[] encryptIV = Conversions.encryptBinary(iv.toCharArray(), key.toCharArray());
            char[] res = Conversions.XOR(Conversions.convertToBinary(splitInput[i]), encryptIV);
            temp = String.valueOf(res);
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
                    int clean = (runsCount * 5);
                    remainingChars[i] = input[clean + i];
                }
            }
            char[] encryptIV = Conversions.encryptBinary(iv.toCharArray(), key.toCharArray());
            char[] res = Conversions.XOR(Conversions.convertToBinary(remainingChars), encryptIV);
            result += String.valueOf(res);
        }

        return result;
    }

    public static String decryptCFB(String encryption, String key, String iv) {
        // Initializing variables
        String result = "";
        String temp = "";
        int runsCount = encryption.length() / 35;
        char[] input = encryption.toCharArray();
        char[][] splitInput = new char[runsCount][35];

        // Split up the input into 35 bit blocks
        for(int i = 0; i < runsCount; i++) {
            for(int j = 0; j < 35; j++) {
                splitInput[i][j] = input[i * 35 + j];
            }
        }

        // Encrypt the iv with the key, then XOR with the input to get first decrypted part
        // Then use the previous input to encrypt with the key and to get the current decrypted part
        for(int i = 0; i < runsCount; i++) {
            char[] encryptedIV = Conversions.encryptBinary(iv.toCharArray(), key.toCharArray());
            char[] added = Conversions.XOR(splitInput[i], encryptedIV);
            temp = "";
            for(int j = 0; j < added.length; j++) {
                temp += added[j];
                if(temp.length() == 7) {
                    int decimal = Integer.parseInt(temp, 2);
                    result += (char) decimal;
                    temp = "";
                }
            }
            iv = String.valueOf(splitInput[i]);
        }
        return result;
    }
}