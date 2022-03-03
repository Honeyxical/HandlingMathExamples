package by.benikov.jwd.task2;

import java.util.Arrays;
import java.util.List;

public class Validation {
    public static boolean validationController(String str) {
        final List<Character> operatorsList = Arrays.asList('+', '-', '/', '*', '(', ')');
        char[] strSymb = str.toCharArray();
        symValidation(strSymb, operatorsList);
        parenthesesValidation(strSymb);
        plusMinusValidation(strSymb);
        startEndOperatorValidation(strSymb, operatorsList);
        return true;
    }


    private static void symValidation(char[] str, List<Character> operatorsList) {
        for (char pointer : str) {
            if (!Character.isDigit(pointer)) {
                if (!operatorsList.contains(pointer)) {
                    throw new RuntimeException("The expression contains an unknown character!");
                }
            }
        }
    }

    private static void parenthesesValidation(char[] str) {
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
    }

    private static void plusMinusValidation(char[] str) {
        for (int i = 0; i < str.length - 1; i++) {
            if ((str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '/' || str[i] == '(') &&
                    (str[i + 1] == '+' || str[i + 1] == '-' || str[i + 1] == '*' || str[i + 1] == '/' || str[i + 1] == ')')) {
                throw new RuntimeException("Incorrect placement of operators!");
            }
        }
    }

    private static void startEndOperatorValidation(char[] str, List<Character> operatorsList) {
        if ((operatorsList.contains(str[0]) && str[0] != '(') || (operatorsList.contains(str[str.length - 1]) && str[str.length - 1] != ')')) {
            throw new RuntimeException("Incorrect placement of parentheses!");
        }
    }

    public static boolean isNull(double num){
        return num == 0;
    }
}
