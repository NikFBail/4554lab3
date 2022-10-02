import java.util.Arrays;

public class Decrypt {

    public static int[] convertToASCII(String input) {
        int[] result = new int[input.length()];
        int num = 0;
        for(int i = 0; i < input.length(); i++) {
            num = (int) (input.charAt(i));
            result[i] = num;
        }
        
        return result;
    }

    public static String[] convertToBinary(String input) {
        int[] ascii = convertToASCII(input);
        String[] result = new String[ascii.length];
        for(int i = 0; i < ascii.length; i++) {
            result[i] = Integer.toBinaryString(ascii[i]);
        }

        return result;
    }

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
