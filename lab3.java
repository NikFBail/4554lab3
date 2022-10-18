import java.util.Arrays;

class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hellojhdf";
        String key = "a5Z#\t";
        System.out.println("The original message is: " + block);
        System.out.println("The key is: " + key);
        
        // Random iv generator
        int[] iv = Conversions.ivGenerator();

        // Prints out a randomly generated 35 bit iv string
        // Used for generating ivs when making our encryption methods
        // System.out.println("Random IV: " + Arrays.toString(iv));

        String[] plaintext = Conversions.rightShift(block); // Creating the plaintext
        String[] formalKey = Conversions.padding(key); // Creating the key

        /* Electronic Book Cipher
         * This block cipher encrypts the
         * key with the plaintext, no extra
         * steps
         */
        String[] encrypted = ECB.encrypted(plaintext, formalKey); // Creating the encryption in binary form
        String decrypted = ECB.decrypt(encrypted, formalKey); // Calling the decryption class to decrypt
        System.out.println("ECB Encryption: " + Arrays.toString(encrypted));
        System.out.println("ECB Decryption: " + decrypted);

        /* Cipher Block Chaining
         * This block cipher XORs
         * the plaintext and the IV,
         * and then encrypts the result
         * with the key
         */
        String[] encryptedCBC = CBC.encryptCBC(plaintext, formalKey); // Encrypting CBCwithIV with the key
        String decryptedCBC = CBC.decryptCBC(encryptedCBC, formalKey); // Decrypting the CBC encryption with the key
        System.out.println("CBC Encryption: " + Arrays.toString(encryptedCBC));
        System.out.println("CBC Decryption: " + decryptedCBC);

        /* Cipher Feedback
         * This block cipher encrypts
         * the iv with the key, and
         * then XORs the result with
         * the plaintext
         */
        String[] encryptedCFB = CFB.encryptCFB(plaintext, formalKey);
        String  decryptedCFB = CFB.decryptCFB(encryptedCFB, formalKey);
        System.out.println("CFB Encryption: " + Arrays.toString(encryptedCFB));
        System.out.println("CFB Decryption: " + decryptedCFB);

        /* Output Feedback
         * This block cipher encrypts
         * the iv with the key, once
         * the iv is done it encrypts
         * the previous eBox with the key
         * then it XORS the result with
         * the input
         */
        String[] encryptedOFB = OFB.encryptDecryptOFB(plaintext, formalKey);
        String decryptedOFB = OFB.decryptOFB(encryptedOFB, formalKey);
        System.out.println("OFB Encryption: " + Arrays.toString(encryptedOFB));
        System.out.println("OFB Decryption: " + decryptedOFB);

    }

}
