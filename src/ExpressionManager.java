import java.util.*;

import static java.lang.Double.parseDouble;

public class ExpressionManager {

    public static String evaluate(ArrayList<String> expressionTokens, Dictionary<String, Variable> variables) {
        Queue<String> items = new LinkedList<>();
        while (true) {
            String item = expressionTokens.remove(0);

            if (variables.get(item) != null) {
                if (variables.get(item).getVariableType().equals(Variable.VariableType.num))
                    item = "" + variables.get(item).getValue();
                else if (variables.get(item).getVariableType().equals(Variable.VariableType.text))
                    item = "\"" + variables.get(item).getValue() + "\"";
                else if (variables.get(item).getVariableType().equals(Variable.VariableType.bool))
                    item = variables.get(item).getValue().equals(true) ? "TRUE" : "FALSE";
            }

            if (item.equals("("))
                item = evaluate(expressionTokens, variables);

            if (item.equals(")"))
                break;

            items.add(item);
        }

        Stack<String> stack = new Stack<>();
        while (!items.isEmpty()) {
            String item = items.remove();
            if (isOperation(item)) {
                String toPush = operation(stack.pop(), item, items.remove());
                stack.push(toPush);
            }
            else if (item.equals("not")) {
                String toPush = items.remove();
                if (toPush.equals("TRUE"))
                    stack.push("FALSE");
                else if (toPush.equals("FALSE"))
                    stack.push("TRUE");
                else
                    throw new Error("Cannot Use Not On Non-Boolean Values");
            }
            else if (item.equals("length")) {
                String toPush = items.remove();
                stack.push("" + toPush.substring(1, toPush.length() - 1).length());
            }
            else
                stack.push(item);
        }

        return stack.pop();
    }

    private static String operation(String firstExpression, String operation, String secondExpression) {
        switch (operation) {
            case "+":
                return add(firstExpression, secondExpression);
            case "-":
                return subtract(firstExpression, secondExpression);
            case "*":
                return multiply(firstExpression, secondExpression);
            case "/":
                return divide(firstExpression, secondExpression);
            case "==":
                return equals(firstExpression, secondExpression);
            case ">":
                return greaterThan(firstExpression, secondExpression);
            case ">=":
                return greaterThanEqual(firstExpression, secondExpression);
            case "<":
                return lessThan(firstExpression, secondExpression);
            case "<=":
                return lessThanEqual(firstExpression, secondExpression);
            case "and":
                return and(firstExpression, secondExpression);
            case "or":
                return or(firstExpression, secondExpression);
            default:
                break;
        }
        throw new Error("Invalid Operation");
    }

    private static boolean isOperation(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/") || string.equals("==") || string.equals(">") || string.equals(">=") || string.equals("<") || string.equals("<=") || string.equals("and") || string.equals("or");
    }

    public static String add(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            Double first = parseDouble(firstExpression);
            Double second = parseDouble(secondExpression);

            return "" + (first + second);
        }
        else if (Main.isNumber(firstExpression)) {
            return "\"" + firstExpression + secondExpression.substring(1);
        }
        else if (Main.isNumber(secondExpression)) {
            return firstExpression.substring(0, firstExpression.length()-1) + secondExpression + "\"";
        }
        else {
            return (firstExpression.substring(0, firstExpression.length()-1) + secondExpression.substring(1));
        }
    }

    public static String subtract(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            Double first = parseDouble(firstExpression);
            Double second = parseDouble(secondExpression);

            return "" + (first - second);
        }
        else {
            throw new Error("Cannot Subtract Text!");
        }
    }

    public static String multiply(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            Double first = parseDouble(firstExpression);
            Double second = parseDouble(secondExpression);

            return "" + (first * second);
        }
        else {
            throw new Error("Cannot Multiply Text!");
        }
    }

    public static String divide(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            Double first = parseDouble(firstExpression);
            Double second = parseDouble(secondExpression);
            return "" + (first / second);
        }
        else {
            throw new Error("Cannot Multiply Text!");
        }
    }

    public static String equals(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            Double first = parseDouble(firstExpression);
            Double second = parseDouble(secondExpression);
            if (first.equals(second))
                return "TRUE";
            return "FALSE";
        }
        else if (firstExpression.equals(secondExpression))
            return "TRUE";
        return "FALSE";
    }

    public static String greaterThan(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            double first = parseDouble(firstExpression);
            double second = parseDouble(secondExpression);
            if (first > second)
                return "TRUE";
            return "FALSE";
        }
        else {
            throw new Error("Cannot Compare Non-Numbers!");
        }
    }

    public static String greaterThanEqual(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            double first = parseDouble(firstExpression);
            double second = parseDouble(secondExpression);
            if (first >= second)
                return "TRUE";
            return "FALSE";
        }
        else {
            throw new Error("Cannot Compare Non-Numbers!");
        }
    }

    public static String lessThan(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            double first = parseDouble(firstExpression);
            double second = parseDouble(secondExpression);
            if (first < second)
                return "TRUE";
            return "FALSE";
        }
        else {
            throw new Error("Cannot Compare Non-Numbers!");
        }
    }

    public static String lessThanEqual(String firstExpression, String secondExpression) {
        if (Main.isNumber(firstExpression) && Main.isNumber(secondExpression)) {
            double first = parseDouble(firstExpression);
            double second = parseDouble(secondExpression);
            if (first <= second)
                return "TRUE";
            return "FALSE";
        }
        else {
            throw new Error("Cannot Compare Non-Numbers!");
        }
    }

    public static String and(String firstExpression, String secondExpression) {
        if (firstExpression.equals("TRUE") && secondExpression.equals("TRUE"))
            return "TRUE";
        return "FALSE";
    }

    public static String or(String firstExpression, String secondExpression) {
        if (firstExpression.equals("TRUE") || secondExpression.equals("TRUE"))
            return "TRUE";
        return "FALSE";
    }
}
