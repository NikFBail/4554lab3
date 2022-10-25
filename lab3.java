class lab3 {

    public static void main(String args[]) {
        // Block and key can only be 5 chars long
        String block = "Hello this should work";
        String key = "11010101010100010101111010010010101";
        String iv = "00111010110011011100011101100100011"; // Used for all modes except ECB and CTR
        String ctrIV = "1000011101111001001";// Only used for CTR
        System.out.println("The original message is: " + block);
        System.out.println("The key is: " + key);
        
        System.out.println("Electronic Code Book block cipher:\n");
        String encrypted = ECB.encryptECB(block, key);
        String decrypted = ECB.decryptECB(encrypted, key);
        // System.out.println("ECB Encryption: " + encrypted);
        // System.out.println("ECB Decryption: " + decrypted + "\n");

        System.out.println("Cipher Block Chaining block cipher:\n");
        String encryptedCBC = CBC.encryptCBC(block, key, iv);
        String decryptedCBC = CBC.decryptCBC(encryptedCBC, key, iv);
        // System.out.println("CBC Encryption: " + encryptedCBC);
        // System.out.println("CBC Decryption: " + decryptedCBC + "\n");

        System.out.println("Cipher Feedback block cipher:\n");
        String encryptedCFB = CFB.encryptCFB(block, key, iv);
        String  decryptedCFB = CFB.decryptCFB(encryptedCFB, key, iv);
        // System.out.println("CFB Encryption: " + encryptedCFB);
        // System.out.println("CFB Decryption: " + decryptedCFB + "\n");

        System.out.println("Output FeedBack block cipher:\n");
        String encryptedOFB = OFB.encryptOFB(block, key, iv);
        String decryptedOFB = OFB.decryptOFB(encryptedOFB, key, iv);
        // System.out.println("OFB Encryption: " + encryptedOFB);
        // System.out.println("OFB Decryption: " + decryptedOFB + "\n");

        System.out.println("Counter block cipher:\n");
        String encryptedCTR = CTR.encryptCTR(block, key, ctrIV);
        String decryptedCTR = CTR.decryptCTR(encryptedCTR, key, ctrIV);
        // System.out.println("CTR Encryption: " + encryptedCTR);
        // System.out.println("CTR Decryption: " + decryptedCTR + "\n");

        /* Introducing a 1 bit error into the test
         * Using the word inspector to test
        */

        // ECB Encryption:
        // 0110111100100110001011010111001100111001101101010110011000101110110101
        // 1-bit error on 12th bit
        String ECBerror = "0110111100110110001011010111001100111001101101010110011000101110110101";
        System.out.println("ECB 1-bit error results: \n" + ECB.decryptECB(ECBerror, key));

        // CBC Encryption:
        // 1101111001010011101111001110110100111110110011000010100011011101011000
        // 1-bit error on 11th bit
        String CBCerror = "1101111001110011101111001110110100111110110011000010100011011101011000";
        System.out.println("CBC 1-bit error results: \n" + CBC.decryptCBC(CBCerror, key, iv));

        // CFB Encryption:
        // 1011011110011111010100000000000000000000100011100011100101110110010101
        // 1-bit error on 11th bit
        String CFBerror = "1011011110111111010100000000000000000000100011100011100101110110010101";
        System.out.println("CFB 1-bit error results: \n" + CFB.decryptCFB(CFBerror, key, iv));

        // OFB Encryption:
        // 101101111001111101010000000000000001011111000000110101110000101
        // 1-bit error on 21st bit
        String OFBerror = "101101111001111101011000000000000001011111000000110101110000101";
        System.out.println("OFB 1-bit error results: \n" + OFB.decryptOFB(OFBerror, key, iv));

        // CTR Encryption:
        // 000110111000111010100101100111100000010111111100110010001011011
        // 1-bit error on 3rd bit
        String CTRerror = "001110111000111010100101100111100000010111111100110010001011011";
        System.out.println("CTR 1-bit error results: \n" + CTR.decryptCTR(CTRerror, key, ctrIV));
    }

}