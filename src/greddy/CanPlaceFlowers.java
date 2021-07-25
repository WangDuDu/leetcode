package greddy;

/**
 * Created by wangshuyang on 2021-7-25.
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        if (0 == length || n > (length / 2 + 1)) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = (i == 0) ? 0 : i - 1;
            int post = (i == length - 1) ? i : i + 1;
            if (flowerbed[pre] != 1 && flowerbed[post] != 1) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }
}
