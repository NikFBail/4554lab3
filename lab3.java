import java.util.Arrays;

class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hello";
        String key = "a5Z#\t";
        int[] iv = Conversions.ivGenerator();
        int[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};

        // Prints out a randomly generated 35 bit iv string
        System.out.println("Random IV: " + Arrays.toString(iv));

        // Creating the plaintext
        String[] shifted = Conversions.rightShift(block);
        System.out.println("Plaintext: " + Arrays.toString(shifted));

        // Creating the key
        String[] keyPadded = Conversions.padding(key);
        System.out.println("Key: " + Arrays.toString(keyPadded));

        // Creating the encryption in binary form
        String[] encrypt = Encrypt.encrypted(shifted, keyPadded);
        System.out.println("Encryption: " + Arrays.toString(encrypt));

        // Converting the encryption from binary to ASCII
        int[] encryptASCII = Conversions.binaryToASCII(encrypt);

        // Converting encryption from ASCII to char
        String encryption = Conversions.asciiToChar(encryptASCII);

        // Calling the decryption class to decrypt
        String decrypted = Decrypt.decrypt(encrypt, keyPadded);
        System.out.println(decrypted);

        String[] IV = Conversions.binaryToString(cbcIV);
        System.out.println(Arrays.toString(Conversions.binaryToString(cbcIV)));

        String[] CBCwithIV = Encrypt.encrypted(shifted, IV);
        System.out.println(Arrays.toString(CBCwithIV));

    }

}
