import java.util.Arrays;

public class CBC {
    
    public static int[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};

    /* Method for
     * xor-ing the plaintext and the iv
     * Same as the encryption method in
     * the encryption class
     */
    public static String[] xorIV(String[] plaintext, String[] iv) {
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

                // Gets the integer value of one digit in the binary of key[i]
                k = Integer.valueOf(iv[i % iv.length].substring(j, j + 1));

                sum = (p + k) % 2; // XOR two binary digits

                // Converts sum to a string and adds it to encrypted[i]
                encrypted[i] += Integer.toString(sum);
            }
        }
        return encrypted;
    }

    /* Method for adding the
     * key and plaintext together
     * to encrypt the plaintext
     * Takes two binary string arrays
     * and adds them together
     */
    public static String[] encrypted(String[] plaintext, String[] key) {
        int k =0; // Holder for one digit in the binary string of key
        int p = 0; // Holder for one digit in the binary string of plaintext
        int sum = 0;
        String[] encrypted = new String[key.length];

        // Filling the array with empty strings so it isn't filled with null values
        Arrays.fill(encrypted, "");
        for(int i = 0; i < plaintext.length; i++) {
            for(int j = 0; j < plaintext[i].length(); j++) {

                // Gets the integer value of one digit in the binary of plaintext[i]
                p = Integer.valueOf(plaintext[i].substring(j, j + 1));

                // Gets the integer value of one digit in the binary of key[i]
                k = Integer.valueOf(key[i].substring(j, j + 1));

                sum = (p + k) % 2; // XOR two binary digits

                // Converts sum to a string and adds it to encrypted[i]
                encrypted[i] += Integer.toString(sum);
            }
        }
        return encrypted;
    }

}
