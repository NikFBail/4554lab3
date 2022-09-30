import java.util.Arrays;
import java.lang.Math;

class lab3 {

    public static void main(String args[]) {
        String block = "Hello";
        String key = "a5Z#\t";

        System.out.println(Arrays.toString(convertToASCII(block)));
        int[] ascii = convertToASCII(block);

        System.out.println(Arrays.toString(convertToBinary(ascii)));
        String[] binary = convertToBinary(ascii);

        System.out.println(Arrays.toString(padding(binary)));
        String[] padded = padding(binary);

        System.out.println(Arrays.toString(rightShift(padded)));
        String[] shifted = rightShift(padded);
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
    public static String[] convertToBinary(int[] arr) {
        String[] result = new String[arr.length];
        for(int i = 0; i < arr.length; i++) {
            result[i] = Integer.toBinaryString(arr[i]);
        }

        return result;
    }

    /* Method for padding binary values
     * adds 0s to the right hand side
     * of the binary if it is less than
     * 7 digits long
     */
    public static String[] padding(String[] input) {
        String curr;
        for(int i = 0; i < input.length; i++) {
            curr = input[i];
            // As long as the length of curr is less than 7
            while(curr.length() < 7) {
                curr = 0 + curr;
            }
            input[i] = curr;
        }

        return input;
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
    public static String[] rightShift(String[] input) {
        // Gets the last three digits of the first binary string
        String lastThree = input[0].substring(input[0].length() - 3);
        for(int i = 1; i < input.length; i++) {
            
            // Gets the last three digits of the current binary string
            lastThree = input[i].substring(input[i].length() - 3);
            // Adds three digits to the current binary string and cuts off the last three
            input[i] = lastThree + input[i].substring(0, input[i].length() - 3);
        }
        input[0] = lastThree + input[0].substring(0, input[0].length() - 3);

        return input;
    }

}
