import java.util.Arrays;
import java.lang.Math;

class lab3 {

    public static void main(String args[]) {
        String block = "This is a test";

        System.out.println(Arrays.toString(convertToASCII(block)));
        int[] ascii = convertToASCII(block);

        System.out.println(Arrays.toString(convertToBinary(ascii)));
        String[] binary = convertToBinary(ascii);

    }

    public static int[] convertToASCII(String input) {
        int[] result = new int[input.length()];
        int num = 0;
        for(int i = 0; i < input.length(); i++) {
            num = (int) (input.charAt(i));
            result[i] = num;
        }
        
        return result;
    }

    public static String[] convertToBinary(int[] arr) {
        String[] result = new String[arr.length];
        for(int i = 0; i < arr.length; i++) {
            result[i] = Integer.toBinaryString(arr[i]);
        }

        return result;
    }

}
