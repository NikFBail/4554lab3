import java.util.Arrays;

public class CBC {
    
    private static int[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};
    public static String[] iv = Conversions.binaryToString(cbcIV);

    /* Method for
     * encrypting using cipher block chaining
     * First xor the input with either 
     * the iv (only for the first input)
     * or the output of the the encryption
     * of the previous input
     * Then encrypt with the key
     * The output is then xor-ed with the
     * next input
     */
    public static String[] encryptCBC(String[] plaintext, String[] key) {
        int initVect =0; // Holder for one digit in the binary string of iv
        int k = 0;
        int p = 0; // Holder for one digit in the binary string of plaintext
        int encrypt = 0;
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");
        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {

                // Gets the integer value of one digit in the binary of plaintext[i]
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));

                // Gets the integer value of one digit in the binary of key[i]
                k = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                // If xor-ing with the iv, don't care about previous output
                if(i < iv.length) {
                    // Gets integer value of one digit in the binary of initialVect[i]
                    initVect = Integer.valueOf(CBC.iv[i % CBC.iv.length].substring(j, j + 1));
                }
                // If XOR-ing with the output of the previous encryption
                else {
                    // Gets integer value of one digit in the binary of encrypted[i - 1]
                    initVect = Integer.valueOf(encrypted[i - 1].substring(j, j + 1));
                }
                
                sum = XOR(p, initVect); // XORs two digits

                encrypt = XOR(sum, k); // XORs two digits
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
