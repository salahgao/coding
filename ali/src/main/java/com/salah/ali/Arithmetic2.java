package com.salah.ali;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author salahgao
 * @date 2020/1/20
 */
public class Arithmetic2 {

    public static void main(String[] args) {

        String exp = "1+(2+3)*4+5*6"; //51
        //exp = "1+2";
        //exp = "1+(2+3)*4";
        String[] array = exp.split("");
        System.out.println(calc(array));
    }

    public static String calc(String[] args) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < args.length) {
            String s = args[i];
            switch (s) {
                // 遇到括弧递归调用运算处理方法
                case "(":
                    int start = i + 1;
                    int count = 1;
                    while (count > 0) {
                        i = i + 1;
                        String tmp = args[i];
                        if (tmp.equals("(")) {
                            count++;
                        } else if (tmp.equals(")")) {
                            count--;
                        }
                    }
                    int end = i;
                    String[] array = Arrays.copyOfRange(args, start, end);
                    String value = calc(array);
                    stack.push(value);
                    break;

                // 遇到+ -将栈顶数据计算后后入栈，并将新符号入栈
                case "+":
                case "-":
                    if (stack.contains("*") || stack.contains("/") || stack.contains("+") || stack.contains("-")) {
                        stack.push(popAndCalc(stack));
                    }
                    stack.push(s);
                    break;
                //遇到* / 将栈顶是* /的计算入栈，并将新符号入栈
                case "*":
                case "/":
                    if (stack.contains("*") || stack.contains("/")) {
                        stack.push(popAndCalc(stack));
                    }
                    stack.push(s);
                    break;
                default:
                    stack.push(s);
            }
            i = i + 1;
        }

        while (stack.size() > 1) {

            stack.push(popAndCalc(stack));
        }
        return stack.pop();

    }

    private static String popAndCalc(Stack<String> stack) {
        int b = Integer.valueOf(stack.pop());
        String oper = stack.pop();
        int a = Integer.valueOf(stack.pop());
        int c = basicCalc(a, b, oper);
        return String.valueOf(c);
    }

    private static int basicCalc(int a, int b, String oper) {
        int result = 0;
        switch (oper) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;
    }

}
