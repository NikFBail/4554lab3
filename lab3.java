class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hello";
        String key = "11010101010100010101111010010010101";
        String iv = "10001011101011001000110011110000101"; // USed for all modes except ECB and CTR
        String ctrIV = "1110101100100011001"; // Only used for CTR
        System.out.println("The original message is: " + block);
        System.out.println("The key is: " + key);
        
        System.out.println("Electronic Code Book block cipher:");
        String encrypted = ECB.encryptECB(block, key);
        String decrypted = ECB.decryptECB(encrypted, key);
        System.out.println("ECB Encryption: " + encrypted);
        System.out.println("ECB Decryption: " + decrypted);

        System.out.println("Cipher Block Chaining block cipher:");
        String encryptedCBC = CBC.encryptCBC(block, key, iv);
        String decryptedCBC = CBC.decryptCBC(encryptedCBC, key, iv);
        System.out.println("CBC Encryption: " + encryptedCBC);
        System.out.println("CBC Decryption: " + decryptedCBC);

        System.out.println("Cipher Feedback block cipher:");
        String encryptedCFB = CFB.encryptCFB(block, key, iv);
        String  decryptedCFB = CFB.decryptCFB(encryptedCFB, key, iv);
        System.out.println("CFB Encryption: " + encryptedCFB);
        System.out.println("CFB Decryption: " + decryptedCFB);

        System.out.println("Output FeedBack block cipher:");
        String encryptedOFB = OFB.encryptOFB(block, key, iv);
        String decryptedOFB = OFB.decryptOFB(encryptedOFB, key, iv);
        System.out.println("OFB Encryption: " + encryptedOFB);
        System.out.println("OFB Decryption: " + decryptedOFB);

        System.out.println("Counter block cipher:");
        String encryptedCTR = CTR.encryptCTR(block, key, ctrIV);
        String decryptedCTR = CTR.decryptCTR(encryptedCTR, key, ctrIV);
        System.out.println("CTR Encryption: " + encryptedCTR);
        System.out.println("CTR Decryption: " + decryptedCTR);

        /* Introducing a 1 bit error into the test
         * Using the word deteriorate to test
        */
    }

}