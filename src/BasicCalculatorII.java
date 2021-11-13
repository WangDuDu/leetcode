import java.util.Stack;

public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int pre, post;
            char tempRes;
            switch (s.charAt(i)) {
                case ' ':
                    break;
                case '+':
                    stack.push('+');
                    break;
                case '-':
                    stack.push('-');
                    break;
                case '*':
                    pre = stack.pop() - '0';
                    while (s.charAt(++i) == ' ') {
                    }
                    post = s.charAt(i) - '0';
                    tempRes = (char) (pre * post);
                    stack.push(tempRes);
                    break;
                case '/':
                    pre = stack.pop() - '0';
                    while (s.charAt(++i) == ' ') {
                    }
                    post = s.charAt(i) - '0';
                    int temp = pre / post;
                    tempRes = (char) temp;
                    stack.push(tempRes);
                    break;
                default:
                    stack.push(s.charAt(i));
            }
        }

        Stack<Character> stackTemp = new Stack<>();
        while (!stack.isEmpty()) {
            stackTemp.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!stackTemp.isEmpty()) {
            sb.append(stackTemp.pop());
        }
        return 1;
    }

    public static void main(String[] args) {
        new BasicCalculatorII().calculate("3+5 / 2");
    }
}
