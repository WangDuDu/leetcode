package greddy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuyang on 2021-7-25.
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // 1、首先将字符串中所有字母在字符串中的最后一个位置找出来
        int[] charLastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charLastIndex[char2Index(s.charAt(i))] = i;
        }

        // 2、贪心发求解
        List<Integer> partitions = new ArrayList<>();
        int startIndex = 0;
        while (startIndex < s.length()) {
            int endIndex = startIndex;
            for (int i = startIndex; i < s.length() && i <= endIndex; i++) {
                int currentCharLastIndex = charLastIndex[char2Index(s.charAt(i))];
                if (currentCharLastIndex > endIndex) {
                    endIndex = currentCharLastIndex;
                }
            }
            partitions.add(endIndex - startIndex + 1);
            startIndex = endIndex + 1;
        }
        return partitions;
    }
    public int char2Index(char c) {
        return c - 'a';
    }
}
