public class Conversions {

    /* Method for converting an integer
     * array into binary values
     * Stores the binary values in
     * a string array
     */
    public static char[] convertToBinary(char[] input) {
        String temp = "";
        for(int i = 0; i < input.length; i++) {
            String augInput = Integer.toBinaryString((int) input[i]);
            while(augInput.length() < 7) {
                augInput = "0" + augInput;
            }
            temp = temp + augInput;
        }

        char[] res = temp.toCharArray();
        return res;
    }

    public static char[] rightShift(char[] input) {
        int len = input.length;
        for(int i = 0; i < 3; i++) {
            char store = input[0];
            for(int j = 0; j < len; j++) {
                char temp = input[(j + 1) % len];
                input[(j + 1) % len] = store;
                store = temp;
            }
        }
        return input;
    }

    public static char[] leftShift(char[] input) {
        int len = input.length;
        for(int i = 0; i < 3; i++) {
            char store = input[len - 1];
            for(int j = input.length - 1; j >= 0; j--) {
                char temp = input[(j - 1 + len) % len];
                input[(j - 1 + len) % len] = store;
                store = temp;
            }
        }
        return input;
    }

    public static char[] XOR(char[] input, char[] key) {
        int num = 0;
        int keyVal = 0;
        char[] resultant = new char[key.length];
        for(int i = 0; i < resultant.length; i++) {
            num = Character.getNumericValue(input[i]);
            keyVal = Character.getNumericValue(key[i]);
            // This turns into something like: (char)(((1 + 0)mod 2) + '0')
            resultant[i] = (char) (((num + keyVal) % 2) + '0');
        }
        return resultant;
    }

    // Encryption box operations
    // What happens in the Ebox stays in the Ebox
    public static char[] eBox(char[] input, char[] key) {
        char[] charArr = convertToBinary(input);
        char[] plain = rightShift(charArr);
        char[] enc = new char[plain.length];
        enc = XOR(plain, key);
        return (enc);
    }

    /* Does the inverse of the eBox
     * XOR's, then removes the
     * right shift
     */
    public static String deBox(char[] text, char[] key) {
        char[] deKeyd = new char[key.length];
        deKeyd = XOR(text, key);
        deKeyd = leftShift(deKeyd);
        String strRep = "";
        String res = "";
        for (int i = 0; i < deKeyd.length; i++) {
            strRep = strRep + deKeyd[i];
            if (strRep.length() == 7) {
                int decimal = Integer.parseInt(strRep, 2);
                res = res + (char) decimal;
                strRep = "";
            }
        }
        return res;
    } 
}
