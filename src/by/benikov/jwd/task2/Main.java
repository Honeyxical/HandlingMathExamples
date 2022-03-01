package by.benikov.jwd.task2;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        validationController("(100+12+2003)+(200)");
    }

    private static final List<Character> operandsList = Arrays.asList('+', '-', '/', '*', '(', ')');

    public static int solveExample(String str) {
        return 0;
    }


    // 1 chek on symbols +
    // 2 count stuples +
    // 3 check operands
    // 4 dev on 0
    // 5 chek on +-/-+ +
    // 6 check on start/end from operators
    // 1 + (1)
    // how to get number with 1+ digit
    // parse from + to +
    //

    private static boolean validationController(String str) {
        final List<Character> operatorsList = Arrays.asList('+', '-', '/', '*', '(', ')');
        char[] strSymb = str.toCharArray();
        symValidation(strSymb);
        parenthesesValidation(strSymb);
        plusMinusValidation(strSymb);
        startEndOperatorValidation(strSymb, operatorsList);
        return true;
    }

    //1+(2-3)

    private static boolean symValidation(char[] str) {
        for (char pointer : str) {
            System.out.println(pointer);
            if (!Character.isDigit(pointer)) {
                if (!operandsList.contains(pointer)) {
                    throw new RuntimeException("String validation error!");
                }
            }
        }
        return true;
    }

    private static boolean parenthesesValidation(char[] str) {
        int openParenthesesCounter = 0;
        int closeParenthesesCounter = 0;
        for (char pointer : str) {
            if (pointer == '(') {
                openParenthesesCounter++;
            } else if (pointer == ')') {
                closeParenthesesCounter++;
            }
        }
        if (openParenthesesCounter != closeParenthesesCounter) {
            throw new RuntimeException("Error number of parentheses.");
        }
        return true;
    }
    //1+2 2*(2+2)

    private static boolean plusMinusValidation(char[] str) {
        for (int i = 0; i < str.length - 1; i++) {
            if ((str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '/' || str[i] == '(') &&
                    (str[i + 1] == '+' || str[i + 1] == '-' || str[i + 1] == '*' || str[i + 1] == '/' || str[i + 1] == ')')) {
                throw new RuntimeException("String validation error!");
            }
        }
        return true;
    }

    private static boolean startEndOperatorValidation(char[] str, List<Character> operatorsList) {
        if ((operatorsList.contains(str[0]) && str[0] != '(') || (operatorsList.contains(str[str.length - 1]) && str[str.length - 1] != ')')) {
            throw new RuntimeException("String validation error!");
        }
        return true;
    }
}
