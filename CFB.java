public class CFB {
    
    private static int[] cfbIV = {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1};
    private static String[] iv = Conversions.binaryToString(cfbIV);

    /* Method for encrypting using
     * Cipher Feedback
     * First encrypt the either the
     * iv (only for the frist input)
     * or the output of the encryption
     * of the previous input
     * Then XOR the encryption with
     * the input
     */
    public static String[] encryptCFB(String[] plaintext, String[] key) {
        int initVect =0; // Holder for one digit in the binary string of iv
        int k = 0; // Holder for one digit in the binary string of the key
        int p = 0; // Holder for one digit in the binary string of plaintext
        int encrypt = 0;
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                // If the initialization vector goes through the encryption
                if(i < iv.length) {
                    initVect = Integer.valueOf(iv[i].substring(j, j + 1));
                    sum = XOR(initVect, k);
                }
                // Otherwise the previous output goes through the encryption, ie XOR k with y2 for determining y3
                else {
                    initVect = Integer.valueOf(encrypted[i - 1].substring(j, j + 1));
                    sum = XOR(initVect, k);
                }

                encrypt = XOR(p, sum);
                encrypted[i] += Integer.toString(encrypt);
            }
        }

        return encrypted;
    }



    // XOR method
    public static int XOR(int x1, int x2) {
        int sum = 0;
        sum = (x1 + x2) % 2;
        return sum;
    }
}
