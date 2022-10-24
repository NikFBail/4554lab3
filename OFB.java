public class OFB {
    // The initialization vector
    // Private as it shouldn't be referenced outside of this class
    private static int[] ofbIV = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1};

    /* Encryption method using
     * Output Feedback Mode
     * First encrypt either the
     * initialization vector, or the
     * result of the previous eBox
     * (not the whole encryption, just the eBox)
     * then XOR with the plaintext
     * to get the encrypted text
    */
    public static String encryptOFB(String plaintext, String key, String iv) {
        String stream = "";
        String encryptedIV = "";
        int runsCount = plaintext.length() / 5;
        int remaining = plaintext.length() % 5;
        char[] result = new char[plaintext.length()];

        // Checks if the text length is a multiple of 5
        // If not, need to do another run
        if(remaining > 0) {
            runsCount++;
        }

        // Create the encryption stream by encrypting the IV with the key character by character
        // Use the result of the previous encryption to encrypt the current one
        for(int i = 0; i < runsCount; i++) {
            encryptedIV = String.valueOf(Conversions.encryptBinary(iv.toCharArray(), key.toCharArray()));
            stream += encryptedIV;
            iv = encryptedIV;
        }

        // XOR the plaintext with the encrypted stream
        result = Conversions.XOR(stream.toCharArray(), Conversions.convertToBinary(plaintext.toCharArray()));
        return String.valueOf(result);
    }

    /* Decryption method for
     * Output Feedback Mode
     * The same as the encryption method
     * The only difference here is that we
     * are converting to a string that is
     * understood by the user
     * (ie not in binary)
     */
    public static String decryptOFB(String encryption, String key, String iv) {
        String stream = "";
        String res = "";
        String strRep = "";
        int runsCount = encryption.length() / 35;
        int remaining = encryption.length() % 35;

        // Checks if the encrypted text has length of a multiple of 35
        // If not, need to do another run
        if(remaining > 0) {
            runsCount++;
        }

        // Create the encryption stream by encrypting the IV with the key character by character
        // Use the result of the previous encryption to encrypt the current one
        for(int i= 0; i < runsCount; i++) {
            String encIV = String.valueOf(Conversions.encryptBinary(iv.toCharArray(), key.toCharArray()));
            stream += encIV;
            iv = encIV;
        }

        // XOR the encrypted text with the decrypted stream
        char[] result = new char[encryption.length()];
        result = Conversions.XOR(stream.toCharArray(), encryption.toCharArray());

        // Convert to string representation
        for(int i = 0; i < result.length; i++) {
            strRep = strRep + result[i];
            if(strRep.length() == 7) {
                int decimal = Integer.parseInt(strRep, 2);
                res += (char) decimal;
                strRep = "";
            }
        }
        return res;
    }
}
