import java.util.Arrays;

public class Decrypt {

    /* Main method for decryption
     * Calls upon the other methods
     * in this class to help decrypt
     */
    public static String decrypt(String[] encryption, String[] key) {
        String[] decrypted = decrypted(encryption, key);
        String[] removeShift = removeShift(decrypted);
        int[] ascii = binaryToASCII(removeShift);
        String word = asciiToChar(ascii);

        return word;
    }
    
    /* Converts an ASCII value to
     * the corresponding char value
     * based from the ASCII table
     */
    public static String asciiToChar(int[] input) {
        String result = "";
        for(int i = 0; i < input.length; i++) {
            result += (char) input[i];
        }

        return result;
    }

    /* Converts from binary to a decimal value */
    public static int[] binaryToASCII(String[] input) {
        int[] result = new int[input.length];
        for(int i = 0; i < input.length; i++) {

            // Converts to decimal, which is what ASCII is
            result[i] = Integer.parseInt(input[i], 2);
        }

        return result;
    }

    /* Undoes the right shift done to the plaintext
     * Takes the first three digits of each binary string
     * in a string[] array, and adds them to the end of the
     * binary string that comes before it in the array.
     * For the first three digits of the binary string in
     * string[0], add them to the end of the last binary
     * string in the array
     */
    public static String[] removeShift(String[] input) {
        // Gets the first three digits of the first binary string
        String firstThree = input[0].substring(0, 3);
        String temp = "";
        for(int i = input.length - 1; i >= 0; i--) {

            // Gets the first three digits of the current binary string
            temp = input[i].substring(0, 3);

            // Adds three digits to the current binary string and cuts off the first three
            input[i] = input[i].substring(3) + firstThree;
            
            // Sets firstThree to temp
            firstThree = temp;
        }

        return input;
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
