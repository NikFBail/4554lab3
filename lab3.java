import java.util.Arrays;
import java.lang.Math;

class lab3 {

    public static void main(String args[]) {
        String block = "Hello";
        String key = "a5Z#\t";

        // Creating the plaintext
        String[] shifted = rightShift(block);
        System.out.println("Plaintext: " + Arrays.toString(shifted));

        // Creating the key
        String[] keyPadded = padding(key);
        System.out.println("Key: " + Arrays.toString(keyPadded));

        String[] encrypt = encrypted(shifted, keyPadded);
        System.out.println("Encryption: " + Arrays.toString(encrypt));

        

    }

    /* Method for converting a string
     * into an ascii value
     * Stores the ascii values in
     * an int array
     */
    public static int[] convertToASCII(String input) {
        int[] result = new int[input.length()];
        int num = 0;
        for(int i = 0; i < input.length(); i++) {
            num = (int) (input.charAt(i));
            result[i] = num;
        }
        
        return result;
    }

    /* Method for converting an integer
     * array into binary values
     * Stores the binary values in
     * a string array
     */
    public static String[] convertToBinary(String input) {
        int[] ascii = convertToASCII(input);
        String[] result = new String[ascii.length];
        for(int i = 0; i < ascii.length; i++) {
            result[i] = Integer.toBinaryString(ascii[i]);
        }

        return result;
    }

    /* Method for padding binary values
     * adds 0s to the right hand side
     * of the binary if it is less than
     * 7 digits long
     */
    public static String[] padding(String input) {
        String[] binary = convertToBinary(input);
        String curr;
        for(int i = 0; i < binary.length; i++) {
            curr = binary[i];
            // As long as the length of curr is less than 7
            while(curr.length() < 7) {
                curr = 0 + curr;
            }
            binary[i] = curr;
        }

        return binary;
    }

    /* Method for shifting
     * right 3 bits
     * Moves the last three bits of
     * a 7-length binary string to
     * the beginning of the next
     * binary string
     * The last three of the last
     * binary string will go to the
     * beginning of the first binary
     * string
     */
    public static String[] rightShift(String input) {
        String[] padded = padding(input);
        // Gets the last three digits of the first binary string
        String lastThree = padded[0].substring(padded[0].length() - 3);
        String temp = "";
        for(int i = 1; i < padded.length; i++) {
            // Gets the last three digits of the current binary string
            temp = padded[i].substring(padded[i].length() - 3);
            // Adds three digits to the current binary string and cuts off the last three
            padded[i] = lastThree + padded[i].substring(0, padded[i].length() - 3);
            
            // Sets lastThree to temp
            lastThree = temp;
        }
        padded[0] = lastThree + padded[0].substring(0, padded[0].length() - 3);

        return padded;
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
        // Filling the array with empty strings to it isn't filled with null values
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
