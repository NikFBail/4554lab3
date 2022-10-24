public class CFB {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static char[] cfbIV = {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1};

    public static String encryptCFB(String plaintext, String key, String iv) {
        String result = "";
        String temp = "";
        int runsCount = plaintext.length() / 5;
        int remaining = plaintext.length() % 5;
        char[] fullInput = plaintext.toCharArray();
        char[][] splitInput = new char[runsCount][5];
        

        //Split the data up into an array of arrays of 5 character blocks
        for (int j = 0; j < runsCount; j++) {
            for (int i = 0; i < 5; i++) {
                splitInput[j][i] = fullInput[j * 5 + i];
            }
        }

        //Encrypt the IV and then XOR it with the first block to receive the first encrypted block, which is appended to
        //the result and then encrypted itself to be XOR'd with the next block and so on.
        for (int i = 0; i < runsCount; i++) {
            char[] encryptIV = Conversions.encryptBinary(iv.toCharArray(), key.toCharArray());
            char[] res = Conversions.XOR(Conversions.convertToBinary(splitInput[i]), encryptIV);
            temp = String.valueOf(res);
            result += temp;
            iv = temp;
        }

        //Pad the last block with 0s if there is a remainder, and then finish off encrypting the result
        if (remaining > 0) {
            char[] remainingChars = new char[5];
            for (int i = 0; i < 5; i++) {
                if (i >= remaining) {
                    remainingChars[i] = (char) 0;
                } else {
                    int clean = (runsCount * 5);
                    remainingChars[i] = fullInput[clean + i];
                }
            }
            char[] encryptIV = Conversions.encryptBinary(iv.toCharArray(), key.toCharArray());
            char[] res = Conversions.XOR(Conversions.convertToBinary(remainingChars), encryptIV);
            result += String.valueOf(res);
        }
        return result;
    }

    public static String decryptCFB(String encryption, String key, String iv) {
        String result = "";
        String temp = "";
        int runs = encryption.length() / 35;
        char[] fullInput = encryption.toCharArray();
        char[][] splitInput = new char[runs][35];

        //Split up the input into an array of arrays of 35 bit blocks
        for (int j = 0; j < runs; j++) {
            for (int i = 0; i < 35; i++) {
                splitInput[j][i] = fullInput[j * 35 + i];
            }
        }

        //Encrypt the IV with the key and XOR it with the first block to receive the first decrypted block. Encrypt the
        //result with the first decrypted block and XOR it with the next to get the next block and so on, converting to
        //its string representation with each block along the way
        for (int i = 0; i < runs; i++) {
            char[] encryptedIV = Conversions.encryptBinary(iv.toCharArray(), key.toCharArray());
            char[] added = Conversions.XOR(splitInput[i], encryptedIV);
            temp = "";
            for (int j = 0; j < added.length; j++) {
                temp += added[j];
                if (temp.length() == 7) {
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
