import java.util.Arrays;

public class Decrypt {

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    public static String decrypt(String[] encryption, String[] key) {
        String[] decrypted = decrypted(encryption, key);
        String[] removeShift = Conversions.removeShift(decrypted);
        int[] ascii = Conversions.binaryToASCII(removeShift);
        String word = Conversions.asciiToChar(ascii);

        return word;
    }

    /* Method that XORs the key from
     * the encrypted plaintext
     */
    public static String[] decrypted(String[] encryption, String[] key) {
        int k =0; // Holder for one digit in the binary string of key
        int e = 0; // Holder for one digit in the binary string of encryption
        int sum = 0;
        String[] decrypted = new String[key.length];

        // Filling the array with empty strings to it isn't filled with null values
        Arrays.fill(decrypted, "");
        for(int i = 0; i < encryption.length; i++) {
            for(int j = 0; j < encryption[i].length(); j++) {

                // Gets the integer value of one digit in the binary of encryption[i]
                e = Integer.valueOf(encryption[i].substring(j, j + 1));

                // Gets the integer value of one digit in the binary of key[i]
                k = Integer.valueOf(key[i].substring(j, j + 1));

                sum = (e + k) % 2; // XOR two binary digits

                // Converts sum to a string and adds it to decrypted[i]
                decrypted[i] += Integer.toString(sum);
            }
        }

        return decrypted;
    }
}
