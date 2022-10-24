class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hello";
        String key = "11010101010100010101111010010010101";
        String iv = "10001011101011001000110011110000101";
        String iv19 = "1110101100100011001";
        System.out.println("The original message is: " + block);
        System.out.println("The key is: " + key);
        

        String encrypted = ECB.encryptECB(block, key); // Creating the encryption in binary form
        String decrypted = ECB.decryptECB(encrypted, key); // Calling the decryption class to decrypt
        // System.out.println("ECB Encryption: " + encrypted);
        // System.out.println("ECB Decryption: " + decrypted);

        String encryptedCBC = CBC.encryptCBC(block, key, iv);
        String decryptedCBC = CBC.decryptCBC(encryptedCBC, key, iv);
        // System.out.println("CBC Encryption: " + encryptedCBC);
        // System.out.println("CBC Decryption: " + decryptedCBC);

        String encryptedCFB = CFB.encryptCFB(block, key, iv);
        String  decryptedCFB = CFB.decryptCFB(encryptedCFB, key, iv);
        // System.out.println("CFB Encryption: " + encryptedCFB);
        // System.out.println("CFB Decryption: " + decryptedCFB);

        String encryptedOFB = OFB.encryptOFB(block, key, iv);
        String decryptedOFB = OFB.decryptOFB(encryptedOFB, key, iv);
        // System.out.println("OFB Encryption: " + encryptedOFB);
        // System.out.println("OFB Decryption: " + decryptedOFB);

        String encryptedCTR = CTR.encryptCTR(block, key, iv19);
        String decryptedCTR = CTR.decryptCTR(encryptedCTR, key, iv19);
        System.out.println("CTR Encryption: " + encryptedCTR);
        System.out.println("CTR Decryption: " + decryptedCTR);

    }

}