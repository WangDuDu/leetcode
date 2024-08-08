package binarysearch;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuyang on 2021-7-28.
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        List<Integer> list = new ArrayList<>();
        return start;
    }

    public boolean isBadVersion(int version) {
        return false;
    }
}
