import java.util.Arrays;

public class Conversions {

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

    // Converts from binary to a decimal value
    public static int[] binaryToASCII(String[] input) {
        int[] result = new int[input.length];
        for(int i = 0; i < input.length; i++) {

            // Converts to decimal, which is what ASCII is
            result[i] = Integer.parseInt(input[i], 2);
        }

        return result;
    }

    /* Method for converting an integer
     * array into binary values
     * Stores the binary values in
     * a string array
     */
    public static String[] convertToBinary(int[] input) {
        String[] result = new String[input.length];
        for(int i = 0; i < input.length; i++) {
            // Converts value of ascii[i] into a binary string
            result[i] = Integer.toBinaryString(input[i]);
        }

        return result;
    }

    /* Method for padding binary values
     * adds 0s to the right hand side
     * of the binary if it is less than
     * 7 digits long
     */
    public static String[] padding(String[] input) {
        String[] result = new String[input.length];
        String curr;
        for(int i = 0; i < result.length; i++) {
            curr = input[i];

            // As long as the length of curr is less than 7
            while(curr.length() < 7) {
                curr = 0 + curr;
            }
            result[i] = curr;
        }

        return result;
    }

    /* Method for shifting right 3 bits
     * Moves the last three bits of a 7-length binary string to
     * the beginning of the next binary string
     * The last three of the last binary string will go to the
     * beginning of the first binary string
     */
    public static String[] rightShift(String[] input) {
        String[] result = new String[input.length];

        // Gets the last three digits of the first binary string
        String lastThree = input[0].substring(input[0].length() - 3);
        String temp = "";
        for(int i = 1; i < result.length; i++) {
            // Gets the last three digits of the current binary string
            temp = input[i].substring(input[i].length() - 3);

            // Adds three digits to the current binary string and cuts off the last three
            result[i] = lastThree + input[i].substring(0, input[i].length() - 3);
            
            // Sets lastThree to temp
            lastThree = temp;
        }
        result[0] = lastThree + input[0].substring(0, input[0].length() - 3);

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

    /* Converts 35 binary digits to a String array 
     * holding 5, 7 character strings for each
     * 7 bit binary value
     */
    public static String[] binaryToString(int[] arr) {
        String[] result = new String[arr.length / 7];

        Arrays.fill(result, "");
        for(int i = 0; i < arr.length; i++) {
            while(i % 7 != 0) {
                result[i/7] += Integer.toString(arr[i]);
                i++;
            }
        }

        for (int j = 0; j<arr.length; j+=7) {
            result[j/7] = Integer.toString(arr[j]) + result[j/7];
        }

        return result;
    }

    // Initialization Vector generator
    // Creates an IV of length 35
    public static int[] ivGenerator() {
        int[] iv = new int[35];
        for(int i = 0; i < iv.length; i++) {
            iv[i] = (int) Math.round(Math.random());
        }

        return iv;
    }

    // XOR method
    public static int XOR(int x1, int x2) {
        int sum = 0;
        sum = (x1 + x2) % 2; // The actual XOR process
        return sum;
    }

    // Encryption box operations
    // What happens in the Ebox stays in the Ebox
    public static String[] eBox(String[] text, String[] key) {
        // Initializing variables
        int keyVal = 0;
        int textVal = 0;
        int sum = 0;
        String[] result = new String[text.length];

        // Doing the right shift
        text = Conversions.rightShift(text);

        Arrays.fill(result, "");

        for(int i = 0; i < text.length; i++) {
            for(int j = 0; j < text[i].length(); j++) {
                // Getting integer values of strings
                textVal = Integer.valueOf(text[i].substring(j, j + 1));
                keyVal = Integer.valueOf(key[i % key.length].substring(j, j + 1));

                sum = XOR(textVal, keyVal);
                result[i] += Integer.toString(sum);
            }
        }
        return result;
    }

    /* Does the inverse of the eBox
     * XOR's, then removes the
     * right shift
     */
    public static String[] deBox(String[] text, String[] key) {
        // Initializing variables
        int keyVal = 0;
        int textVal = 0;
        int sum = 0;
        String[] result = new String[text.length];

        Arrays.fill(result, "");

        for(int i = 0; i < text.length; i++) {
            for(int j = 0; j < text[i].length(); j++) {
                // Getting integer values of strings
                textVal = Integer.valueOf(text[i].substring(j, j + 1));
                keyVal = Integer.valueOf(key[i % key.length].substring(j, j + 1));
                
                sum = XOR(textVal, keyVal);
                result[i] += Integer.toString(sum);
            }
        }
        result = Conversions.removeShift(result);
        return result;
    }
    
}
