import java.util.Arrays;

public class CBC {
    
    public final static int[] initialVector = ivGenerator();

    public static int[] ivGenerator() {
        int[] iv = new int[35];
        for(int i = 0; i < iv.length; i++) {
            iv[i] = (int) Math.round(Math.random());
        }

        return iv;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(initialVector));
        System.out.println(Arrays.toString(initialVector));
        System.out.println(Arrays.toString(initialVector));
    }
    
}
