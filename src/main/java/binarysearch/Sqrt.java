package binarysearch;

/**
 * Created by wangshuyang on 2021-7-26.
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int start = 1;
        int end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return sqrt;
            } else if (sqrt < mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}
