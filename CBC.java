import java.util.Arrays;

public class CBC {
    
    private static int[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};
    public static String[] iv = Conversions.binaryToString(cbcIV);

    /* Method for
     * xor-ing the plaintext and the iv
     * Same as the encryption method in
     * the encryption class
     */
    public static String[] encryptCBC(String[] plaintext, String[] key) {
        int k =0; // Holder for one digit in the binary string of key
        int p = 0; // Holder for one digit in the binary string of plaintext
        int sum = 0;
        String[] encrypted = new String[plaintext.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");
        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {

                // Gets the integer value of one digit in the binary of plaintext[i]
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));

                if(i < iv.length) {
                    k = Integer.valueOf(CBC.iv[i % CBC.iv.length].substring(j, j + 1));
                }
                else {
                    // Gets the integer value of one digit in the binary of key[i]
                    k = Integer.valueOf(key[i % key.length].substring(j, j + 1));
                }

                sum = XOR(p, k); // XOR two binary digits

                // Converts sum to a string and adds it to encrypted[i]
                encrypted[i] += Integer.toString(sum);
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
