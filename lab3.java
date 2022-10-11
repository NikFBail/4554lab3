import java.util.Arrays;

class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hellojhdf";
        String key = "a5Z#\t";
        
        // Random iv generator
        int[] iv = Conversions.ivGenerator();

        // Prints out a randomly generated 35 bit iv string
        System.out.println("Random IV: " + Arrays.toString(iv));

        String[] plaintext = Conversions.rightShift(block); // Creating the plaintext
        String[] formalKey = Conversions.padding(key); // Creating the key

        /* Electronic Book Cipher
         * This block cipher encrypts the
         * key with the plaintext, no extra
         * steps
         */
        String[] encrypt = ECB.encrypted(plaintext, formalKey); // Creating the encryption in binary form
        String decrypted = ECB.decrypt(encrypt, formalKey); // Calling the decryption class to decrypt

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
        String decryptedCBC = CBC.decrypt(encryptedCBC, formalKey);
        System.out.println(Arrays.toString(CBC.decrypted(encryptedCBC, formalKey)));
        System.out.println(decryptedCBC);

    }

}
