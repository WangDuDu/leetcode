package partition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuyang on 2021-8-2.
 */
public class DifferentWaysToAddParentheses {


    public List<Integer> diffWaysToCompute(String expression) {
        // 存储expression可能得到的结果
        List<Integer> ways = new ArrayList<>();
        // 遍历给定的字符串，每次遇到计算符号就分治该符号两边
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, expression.length()));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        // 如果ways没有加入过任务元素，说明expression没有任何计算符号，是一个数字直接将其转为Integer
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(expression));
        }
        return ways;
    }
}
