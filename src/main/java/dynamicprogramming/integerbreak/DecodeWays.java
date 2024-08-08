package dynamicprogramming.integerbreak;

/**
 * 解码方法
 */
public class DecodeWays {
    public int numDecodings(String s) {
        int length = s.length();
        int[] res = new int[length + 1];
        res[0] = 1;
        res[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= length; i++) {

            if (s.charAt(i - 1) != '0') {
                res[i] += res[i - 1];
            }

            if (s.charAt(i - 2) != '0' && (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26) {
                res[i] += res[i - 2];
            }

        }
        return res[length];
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("123003"));
    }
}
