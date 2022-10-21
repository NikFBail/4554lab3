class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hello";
        String key = "11010101010100010101111010010010101";
        System.out.println("The original message is: " + block);
        System.out.println("The key is: " + key);
        
        // Random iv generator
        // int[] iv = Conversions.ivGenerator();

        // Prints out a randomly generated 35 bit iv string
        // Used for generating ivs when making our encryption methods
        // System.out.println("Random IV: " + Arrays.toString(iv));
        

        /* Electronic Book Cipher
         * This block cipher encrypts the
         * key with the plaintext, no extra
         * steps
         */
        String encrypted = ECB.encryptECB(block, key); // Creating the encryption in binary form
        String decrypted = ECB.decryptECB(encrypted, key); // Calling the decryption class to decrypt
        System.out.println("ECB Encryption: " + encrypted);
        System.out.println("ECB Decryption: " + decrypted);

        /* Cipher Block Chaining
         * This block cipher XORs
         * the plaintext and the IV,
         * and then encrypts the result
         * with the key
         */
        // String[] encryptedCBC = CBC.encryptCBC(block, key);
        // String decryptedCBC = CBC.decryptedCBC(encryptedCBC, key);
        // System.out.println("CBC Encryption: " + Arrays.toString(encryptedCBC));
        // System.out.println("CBC Decryption: " + decryptedCBC);

        /* Cipher Feedback
         * This block cipher encrypts
         * the iv with the key, and
         * then XORs the result with
         * the plaintext
         */
        // String[] encryptedCFB = CFB.encryptCFB(block, key);
        // String  decryptedCFB = CFB.decryptCFB(encryptedCFB, key);
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
        // String[] encryptedOFB = OFB.encryptDecryptOFB(block, key);
        // String decryptedOFB = OFB.decryptOFB(encryptedOFB, key);
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
