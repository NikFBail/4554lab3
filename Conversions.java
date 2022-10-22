public class Conversions {

    /* Method for converting the iv to binary
     * We need a binary that is at least 16
     * bits long, so if it is shorter we will pad
     * with 0's on the right side so it will
     * maintain it's original value
     */
    public static String binaryIV(int input) {
        String binary = Integer.toBinaryString(input);
        while (binary.length() < 16) {
            binary = "0" + binary;
        }

        return binary;
    }

    /* Method for converting a char array
     * into a binary, stored in a char array
     * If any of the binary strings are less than
     * 7 in length, we pad with 0's on the right
     * side so the binary will maintain
     * it's original value
     */
    public static char[] convertToBinary(char[] input) {
        String temp = "";
        String binaryInput = "";
        for(int i = 0; i < input.length; i++) {
            binaryInput = Integer.toBinaryString((int) input[i]);
            while(binaryInput.length() < 7) {
                binaryInput = "0" + binaryInput;
            }
            temp += binaryInput;
        }

        char[] result = temp.toCharArray();
        return result;
    }

    /* Performs a right shift on a char array
     * Shifts the first three chars of a character value
     * to the next character value in the char array
     * The chars in char[char.length] get moved to char[0]
     * Does this by shifting the first char in each
     * character value in the array first,
     * then the second char of each character value,
     * and then the third char of each character value
     */
    public static char[] rightShift(char[] input) {
        int len = input.length;

        for(int i = 0; i < 3; i++) {
            char storage = input[0];
            for(int j = 0; j < len; j++) {
                char temp = input[(j + 1) % len];
                input[(j + 1) % len] = storage;
                storage = temp;
            }
        }

        return input;
    }

    /* Performs a left shift on a char array
     * Shifts the first three chars of a character value
     * to the previous character value in the char array
     * The chars in char[0] get moved to char[char.length]
     * Does this by shifting the first char in each
     * character value in the array first,
     * then the second char of each character value,
     * and then the third char of each character value
     */
    public static char[] leftShift(char[] input) {
        int len = input.length;

        for(int i = 0; i < 3; i++) {
            char storage = input[len - 1];
            for(int j = input.length - 1; j >= 0; j--) {
                char temp = input[(j - 1 + len) % len];
                input[(j - 1 + len) % len] = storage;
                storage = temp;
            }
        }

        return input;
    }

    // XOR Method
    // Adds two integer values and mods 2
    public static char[] XOR(char[] input, char[] key) {
        int num = 0;
        int keyVal = 0;
        char[] result = new char[key.length];
        for(int i = 0; i < result.length; i++) {
            num = Character.getNumericValue(input[i]);
            keyVal = Character.getNumericValue(key[i]);
            result[i] = (char) (((num + keyVal) % 2) + '0');
        }

        return result;
    }

    /* Encryption method
     * Look more into how this is different from the eBox
     */
    public static char[] encryptBinary(char[] input, char[] key) {
        char[] plain = rightShift(input); // Shift binary representation to the right (circular)
        char[] encrypt = new char[plain.length];
        encrypt = XOR(plain, key);
        return (encrypt);
    }

    // Encryption box operations
    // What happens in the Ebox stays in the Ebox
    public static char[] eBox(char[] input, char[] key) {
        char[] charArray = convertToBinary(input);
        char[] plain = rightShift(charArray);
        char[] encrypt = new char[plain.length];
        encrypt = XOR(plain, key);

        return (encrypt);
    }

    // eBox with another XOR after the normal encryption operations
    public static char[] encryptPlusXOR(char[] input, char[] key, String vector) {
        char[] vectArr = vector.toCharArray();
        char[] charArr = convertToBinary(input); // Convert to binary (Result is a char array of 1's and 0's)
        charArr = XOR(charArr, vectArr);
        char[] plain = rightShift(charArr); // Shift binary representation to the right (circular)
        char[] encrypt = new char[plain.length];
        encrypt = XOR(plain, key);

        return (encrypt);
    }

    /* Does the inverse of the eBox
     * XOR's, then removes the
     * right shift
     */
    public static String deBox(char[] text, char[] key) {
        String temp = "";
        String result = "";
        char[] unKey= new char[key.length];
        unKey = XOR(text, key);
        unKey = leftShift(unKey);
        
        for(int i = 0; i < unKey.length; i++) {
            temp += unKey[i];
            if(temp.length() == 7) {
                int decimal = Integer.parseInt(temp, 2);
                result += (char) decimal;
                temp = "";
            }
        }

        return result;
    }

    /* Look more into the decryption method */
    public static String decryptNoChars(char[] encrypted, char[] key) {
        String result = "";
        char[] unKey = new char[key.length];
        unKey = XOR(encrypted, key);
        unKey = leftShift(unKey);
        result = String.valueOf(unKey);

        return result;
    }
}