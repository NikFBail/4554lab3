import java.util.Arrays;

class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hellojhdfhsdf";
        String key = "a5Z#\t";
        
        // Random iv generator
        int[] iv = Conversions.ivGenerator();

        // The iv to be used for CBC
        int[] cbcIV = {0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};

        // Prints out a randomly generated 35 bit iv string
        System.out.println("Random IV: " + Arrays.toString(iv));

        String[] plaintext = Conversions.rightShift(block); // Creating the plaintext
        String[] formalKey = Conversions.padding(key); // Creating the key

        /* Electronic Book Cipher
         * This block cipher encrypts the
         * key with the plaintext, no extra
         * steps
         */
        System.out.println(block); // Original message
        String[] encrypt = ECB.encrypted(plaintext, formalKey); // Creating the encryption in binary form
        String decrypted = ECB.decrypt(encrypt, formalKey); // Calling the decryption class to decrypt
        System.out.println(decrypted); // Decrypted message, should be the same as the original

        /* Cipher Block Chaining
         * This block cipher XORs
         * the plaintext and the IV,
         * and then encrypts the result
         * with the key
         */
        System.out.println(block); // Original message
        System.out.println("Plaintext: " + Arrays.toString(plaintext));
        System.out.println("iv: " + Arrays.toString(CBC.iv));
        System.out.println("Key: " + Arrays.toString(formalKey));
        String[] encryptedCBC = CBC.encryptCBC(plaintext, formalKey); // Encrypting CBCwithIV with the key
        System.out.println(Arrays.toString(encryptedCBC));
        String decryptedCBC = ECB.decrypt(encryptedCBC, formalKey);
        System.out.println(decryptedCBC);

    }

}
