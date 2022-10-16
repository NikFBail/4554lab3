import java.util.Arrays;

public class CTR {
    private static int[] ctrIV = {1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1};
    private static String[] iv = Conversions.binaryToString(ctrIV);
}
