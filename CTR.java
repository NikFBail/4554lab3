public class CTR {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static String ctrIV = "1000011101111001001";

    /* Method of encryption using
     * Counter Mode
     * Takes an iv with a counter and
     * encrypts with the key
     * (ie the first encryption would be with an iv
     * with 0s at the end, second iv encrypted would
     * have 00...01, third would have 00...10, fourth
     * would have 00...11, and so on)
     * Then XOR the result with the input to get the
     * encrypted text
     */
    public static String encryptCTR(String plaintext, String key, String iv) {
        // Initializing variables
        int remaining = plaintext.length() % 5;
        int runsCount = plaintext.length() / 5;
        String stream = "";
        char[] result = new char[plaintext.length()];

        // Checks if the plaintext has length of a multiple of 5
        // If not, increase runs by 1
        if(remaining > 0) {
            runsCount++;
        }

        // Takes the 16-bit iv, and runsCount, and implements
        // a 35-bit iv with a counter at the end for each round of encryption
        for(int index = 0; index < runsCount; index++) {
            char[] tempChar = (String.valueOf(iv) + Conversions.binaryIV(index)).toCharArray();
            stream += String.valueOf(Conversions.encryptBinary(tempChar, key.toCharArray()));
        }

        result = Conversions.XOR(stream.toCharArray(), Conversions.convertToBinary(plaintext.toCharArray()));
        return String.valueOf(result);
    }

    /* Method of decryption using
     * Counter Method
     * Same as encryption method,
     * only difference is that at the end
     * we convert back to a string readable
     * by the user
     * (ie not in binary)
     */
    public static String decryptCTR(String encryption, String key, String iv) {
        // Initializing variables
        int remaining = encryption.length() % 35;
        int runs = encryption.length() / 35;
        String stream = "";
        String res = "";
        String temp = "";
        char[] result = new char[encryption.length()];

        // Checks if the plaintext has length of a multiple of 5
        // If not, increase runs by 1
        if(remaining > 0) {
            runs++;
        }

        // Takes the 16-bit iv, and runsCount, and implements
        // a 35-bit iv with a counter at the end for each round of encryption
        for(int index = 0; index < runs; index++) {
            char[] tempChar = (String.valueOf(iv) + Conversions.binaryIV(index)).toCharArray();
            stream += String.valueOf(Conversions.encryptBinary(tempChar, key.toCharArray()));
        }
 
        result = Conversions.XOR(stream.toCharArray(), encryption.toCharArray());
        
        // Converting from a char array to a string
        for(int i = 0; i < result.length; i++) {
            temp += result[i];
            if(temp.length() == 7) {
                int decimal = Integer.parseInt(temp, 2);
                res += (char) decimal;
                temp = "";
            }
        }
        return res;
    }
}