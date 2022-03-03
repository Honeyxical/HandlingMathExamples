package by.benikov.jwd.task2;

import java.util.Stack;

public class Calc {
    private static String current = "";
    private static String operand = "";
    private static Double a;
    private static Double b;
    private static final Stack<Character> CHARACTER_STACK = new Stack<>();
    private static final Stack<Double> DOUBLE_STACK = new Stack<>();

    public static Double calculate(String example){
        if(Validation.validationController(example)){
            return rpnToAnswer(expToRPN(example));
        }
        return 0.0;
    }

    private static String expToRPN(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            int priority = getPriority(expr.charAt(i));
            if (priority == 0) {
                current += expr.charAt(i);
            }
            if (priority == 1) {
                CHARACTER_STACK.push(expr.charAt(i));
            }
            if (priority > 1) {
                current += " ";
                while (!CHARACTER_STACK.empty()) {
                    if (getPriority(CHARACTER_STACK.peek()) >= priority) {
                        current += CHARACTER_STACK.pop();
                    } else break;
                }
                CHARACTER_STACK.push(expr.charAt(i));
            }

            if (priority == -1) {
                current += " ";
                while (getPriority(CHARACTER_STACK.peek()) != 1) {
                    current += CHARACTER_STACK.pop();
                }
                CHARACTER_STACK.pop();
            }
        }
        while (!CHARACTER_STACK.empty()) {
            current += CHARACTER_STACK.pop();
        }
        return current;
    }

    private static double rpnToAnswer(String rpn) {
        for (int i = 0; i < rpn.length(); i++) {

            if (rpn.charAt(i) == ' ') {
                continue;
            }

            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                }
                DOUBLE_STACK.push(Double.parseDouble(operand));
                operand = "";
            }

            if (getPriority(rpn.charAt(i)) > 1) {
                a = DOUBLE_STACK.pop();
                b = DOUBLE_STACK.pop();
                executeOperation(a,b,rpn.charAt(i));
            }
        }
        return DOUBLE_STACK.pop();
    }

    private static void executeOperation(double a, double b, char operator){
        switch (operator) {
            case '+':
                DOUBLE_STACK.push(b + a);
                break;
            case '-':
                DOUBLE_STACK.push(b - a);
                break;
            case '*':
                DOUBLE_STACK.push(b * a);
                break;
            case '/':
                if (Validation.isNull(b) && Validation.isNull(a)) {
                    DOUBLE_STACK.push(b / a);
                    break;
                }
                throw new RuntimeException("Division by zero");
        }
    }

    private static int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 3;
        } else if (token == '-' || token == '+') {
            return 2;
        } else if (token == '(') {
            return 1;
        } else if (token == ')') {
            return -1;
        }
        return 0;
    }
}
