package leetcode.editor.cn;

//给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。 
//
// 例如： 
//
// 
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28 
//...
// 
//
// 
//
// 示例 1： 
//
// 
//输入：columnNumber = 1
//输出："A"
// 
//
// 示例 2： 
//
// 
//输入：columnNumber = 28
//输出："AB"
// 
//
// 示例 3： 
//
// 
//输入：columnNumber = 701
//输出："ZY"
// 
//
// 示例 4： 
//
// 
//输入：columnNumber = 2147483647
//输出："FXSHRXW"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= columnNumber <= 2³¹ - 1 
// 
// Related Topics 数学 字符串 👍 533 👎 0

/**
 * 本题的数值范围是大于等于1的，是从1开始的而不是从0开始的，这样后边的求商取余都查了一位。所以在每次求商取余的时候应该把被除数减一。
 * 如果采用map (0, 'A'),(1, 'B')......(25, 'Z')，26的余数是0，商是1，这样26计算得到的是BA，而不是Z，这种方式同样不可取
 *
 * 尝试的其它做法
 * (1)如果采用map (1, 'A'),(2, 'B')......(26, 'Z')
 *    1、1~26数字分别对应数组map的1~26。26的余数是0，商是1，这样0是没有可以对应的数的，所以这种方式不行
 *
 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        Solution solution = new ExcelSheetColumnTitle().new Solution();
    }

    class Solution {
        public String convertToTitle(int columnNumber) {
            char[] map = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

            StringBuilder stringBuilder = new StringBuilder();
            while (columnNumber != 0) {
                columnNumber = columnNumber - 1;
                stringBuilder.append(map[columnNumber % 26]);
                columnNumber = columnNumber / 26;
            }
            return stringBuilder.reverse().toString();

        }
    }

}