public class OFB {
    
    private static int[] ofbIV = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1};
    private static String[] iv = Conversions.binaryToString(ofbIV);
}
