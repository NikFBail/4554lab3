import java.util.Arrays;

class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hello";
        String key = "a5Z#\t";
        System.out.println("The original message is: " + block);
        System.out.println("The key is: " + key);
        
        // Random iv generator
        // int[] iv = Conversions.ivGenerator();

        // Prints out a randomly generated 35 bit iv string
        // Used for generating ivs when making our encryption methods
        // System.out.println("Random IV: " + Arrays.toString(iv));

        /* Calls to the Conversions class
         * Makes it easier to understand what exactly
         * the Conversions class does, and how the
         * plaintext and key are formatted
         * More conversions on the plaintext are done
         * inside each block cipher class
         */
        int[] blockArray = Conversions.convertToASCII(block); // Converts the plaintext to ASCII
        String[] blockBinary = Conversions.convertToBinary(blockArray); // Converts the plaintext to binary
        int[] keyArray = Conversions.convertToASCII(key); // Converts the key to ASCII
        String[] keyBinary = Conversions.convertToBinary(keyArray); // Converts the plaintext to binary
        String[] formalKey = Conversions.padding(keyBinary); // Pads the key, final step
        

        /* Electronic Book Cipher
         * This block cipher encrypts the
         * key with the plaintext, no extra
         * steps
         */
        String[] encrypted = ECB.encryptedECB(blockBinary, formalKey); // Creating the encryption in binary form
        String decrypted = ECB.decryptedECB(encrypted, formalKey); // Calling the decryption class to decrypt
        // System.out.println("ECB Encryption: " + Arrays.toString(encrypted));
        // System.out.println("ECB Decryption: " + decrypted);

        /* Cipher Block Chaining
         * This block cipher XORs
         * the plaintext and the IV,
         * and then encrypts the result
         * with the key
         */
        String[] encryptedCBC = CBC.encryptCBC(blockBinary, formalKey); // Encrypting CBCwithIV with the key
        String decryptedCBC = CBC.decryptedCBC(encryptedCBC, formalKey); // Decrypting the CBC encryption with the key
        System.out.println("CBC Encryption: " + Arrays.toString(encryptedCBC));
        System.out.println("CBC Decryption: " + decryptedCBC);

        /* Cipher Feedback
         * This block cipher encrypts
         * the iv with the key, and
         * then XORs the result with
         * the plaintext
         */
        String[] encryptedCFB = CFB.encryptCFB(blockBinary, formalKey);
        String  decryptedCFB = CFB.decryptCFB(encryptedCFB, formalKey);
        // System.out.println("CFB Encryption: " + Arrays.toString(encryptedCFB));
        // System.out.println("CFB Decryption: " + decryptedCFB);

        /* Output Feedback
         * This block cipher encrypts
         * the iv with the key, once
         * the iv is done it encrypts
         * the previous eBox with the key
         * then it XORS the result with
         * the input
         */
        String[] encryptedOFB = OFB.encryptDecryptOFB(blockBinary, formalKey);
        String decryptedOFB = OFB.decryptOFB(encryptedOFB, formalKey);
        // System.out.println("OFB Encryption: " + Arrays.toString(encryptedOFB));
        // System.out.println("OFB Decryption: " + decryptedOFB);

        /* Counter Mode
         * This block cipher encrypts
         * an iv that has a counter
         * on it, with the key
         * The result is then XOR-ed with
         * the plaintext to get the output,
         * the encrypted text
         */
        // String[] encryptedCTR = CTR.encryptDecryptCTR(blockBinary, formalKey);
        // String decryptedCTR = CTR.decryptCTR(encryptedCTR, formalKey);
        // System.out.println("CTR Encryption: " + Arrays.toString(encryptedCTR));
        // System.out.println("CTR Decryption: " + decryptedCTR);

    }

}
