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

    /* Converts from binary to a decimal value */
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
    public static String[] convertToBinary(String input) {
        // Calls the method to convert the input into an ASCII value
        int[] ascii = convertToASCII(input);
        String[] result = new String[ascii.length];
        for(int i = 0; i < ascii.length; i++) {
            // Converts value of ascii[i] into a binary string
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
        // Calls the method to convert the input into a binary string
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

    /* Method for shifting right 3 bits
     * Moves the last three bits of a 7-length binary string to
     * the beginning of the next binary string
     * The last three of the last binary string will go to the
     * beginning of the first binary string
     */
    public static String[] rightShift(String input) {
        // Calls the method to convert the input into a padded binary string
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

    public static String[] binaryToString(int[] arr) {
        String[] result = new String[arr.length / 7];
        for(int i = 0; i < arr.length; i++) {
            while(i % 7 != 0) {
                result[i/7] += Integer.toString(arr[i]);
                i++;
            }
        }

        for (int j = 0; j<arr.length; j+=7) {
            if ()
        }
        return result;
    }
    
}
