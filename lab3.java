import java.util.Arrays;
import java.lang.Math;

class lab3 {

    public static void main(String args[]) {
        String block = "a5Z#\t";

        System.out.println(Arrays.toString(convertToASCII(block)));
        int[] ascii = convertToASCII(block);

        System.out.println(Arrays.toString(convertToBinary(ascii)));
        String[] binary = convertToBinary(ascii);

        System.out.println(Arrays.toString(padding(binary)));
        String[] padded = padding(binary);

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

}
