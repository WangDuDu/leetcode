package leetcode.editor.cn;

//给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。 
//
// 注意: 
//
// 
// 十六进制中所有字母(a-f)都必须是小写。 
// 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
// 给定的数确保在32位有符号整数范围内。 
// 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。 
// 
//
// 示例 1： 
//
// 
//输入:
//26
//
//输出:
//"1a"
// 
//
// 示例 2： 
//
// 
//输入:
//-1
//
//输出:
//"ffffffff"
// 
// Related Topics 位运算 数学 👍 245 👎 0


public class ConvertANumberToHexadecimal {
    public static void main(String[] args) {
        Solution solution = new ConvertANumberToHexadecimal().new Solution();
        System.out.println(solution.toHex1(26));
    }

    class Solution {
        /**
         * 这里的int是32位的，实际上就是32位的有符号二进制转换为有符号16进制，
         * 1、16进制需要用4位来表示，这里总共32位，所以可以分成8组
         * 2、负数转化为32位的二级制数本身就是用补码来进行表示的，所以处理负数直接用补码进行二级制运算即可不用进行额外的操作。
         * 3、需要注意用无符号右移进行除法操作，这样不会影响符号位
         * 4、负数在进行转换的时候，符号位在最高位始终是1，最高四位正数是0XXX，负数是1XXX。最高位的四位数进行转换的时候，转换成16进制0~7表示正数、8~f表示负数
         *
         * @param num
         * @return
         */
        public String toHex(int num) {
            if (num == 0) {
                return "0";
            }
            char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

            StringBuilder stringBuilder = new StringBuilder();
            while (num != 0) {
                // 这里可以用num & 0b1111 或 num & 15来进行二进制的取余操作
                stringBuilder.append(chars[num & 0b1111]);
                num = num >>> 4;
            }
            return stringBuilder.reverse().toString();
        }

        /**
         * 1、直接采用求商取余的方法
         * 2、对于负数我们的处理方式是在原有的基础上加上2的32次的偏移量，这是一种采用大整数表示负数的方式，对于32位的int类型变量
         * 最高位的数值代表符号(1为负数，0为正数)，负数用补码表示，-1的32位二级制有符号数表示为32个1。无符号数的「2的32次 -1」
         * 的二级制表示同样是32个1，这样有符号数的-1和无符号数的「2的32次 -1」相差2的32次，所以在有符号负数的基础上加上2的32次
         * 的偏移量可以转化为二级制相同的无符号正数。这里的无符号正数我们可以用比int表示范围大的有符号类型long来表示。就可以用大
         * 正数来表示负数了
         *
         * @param num
         * @return
         */
        public String toHex1(int num) {
            if (num == 0) {
                return "0";
            }

            long longNum = num;
            if (longNum < 0) {
                longNum = (long)(Math.pow(2, 32) + num);
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (longNum != 0) {
                long bitNum = longNum % 16;
                char c = (char) bitNum;
                if (bitNum >= 10) {
                    c = (char) ('a' + (bitNum - 10));
                } else {
                    c = (char) ('0' + bitNum);
                }
                stringBuilder.append(c);
                longNum = longNum / 16;
            }
            return stringBuilder.reverse().toString();
        }
    }
}