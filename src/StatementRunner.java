import java.util.ArrayList;
import java.util.Dictionary;

import static java.lang.Double.parseDouble;

public class StatementRunner {



    public static void declareVariable(Variable.VariableType variableType, ArrayList<String> tokens, Dictionary<String, Variable> variables) {
        String name = tokens.remove(0);

        // Declare Variable If There Are More Tokens
        if (tokens.size() > 0) {

            String equals = tokens.remove(0);

            if (!equals.equals("="))
                throw new Error("No = Declaration!");

            switch (variableType) {
                case num:
                    Double valueNum;

                    if (tokens.get(0).equals("("))
                        valueNum = Double.valueOf(Main.expressionToEvaluate(tokens, variables));
                    else
                        throw new Error("Number Variable Declaration Must Take In A Expression");

                    variables.put(name, new Variable<>(name, valueNum, Variable.VariableType.num));
                    break;

                case bool:
                    boolean valueBoolean;

                    if (tokens.get(0).equals("("))
                        valueBoolean = Main.expressionToEvaluate(tokens, variables).equals("TRUE") ? true : false;
                    else
                        throw new Error("Boolean Variable Declaration Must Take In A Expression");

                    variables.put(name, new Variable<>(name, valueBoolean, Variable.VariableType.bool));
                    break;

                case text:
                    String valueText;

                    if (tokens.get(0).equals("("))
                        valueText = Main.expressionToEvaluate(tokens, variables);
                    else
                        throw new Error("Text Variable Declaration Must Take In A Expression! Variable: " + name);

                    valueText = valueText.substring(1, valueText.length() - 1);
                    variables.put(name, new Variable<>(name, valueText, Variable.VariableType.text));
                    break;
            }
        }
        else {
            switch (variableType) {
                case num:
                    variables.put(name, new Variable<>(name, 0d, Variable.VariableType.num));
                    break;
                case bool:
                    variables.put(name, new Variable<>(name, false, Variable.VariableType.bool));
                    break;
                case text:
                    variables.put(name, new Variable<>(name, "", Variable.VariableType.text));
                    break;
            }
        }
    }



    public static void reassignVariable(String variableName, ArrayList<String> tokens, Dictionary<String, Variable> variables) {
        if (tokens.size() > 0) {
            String equals = tokens.remove(0);

            if (!equals.equals("="))
                throw new Error("No = Declaration!");

            if (variables.get(variableName).getVariableType().equals(Variable.VariableType.bool)) {
                boolean value = Main.expressionToEvaluate(tokens, variables).equals("TRUE");
                variables.put(variableName, new Variable<>(variableName, value, Variable.VariableType.bool));
            }
            else if (variables.get(variableName).getVariableType().equals(Variable.VariableType.num)) {
                double value = parseDouble(Main.expressionToEvaluate(tokens, variables));
                variables.put(variableName, new Variable<>(variableName, value, Variable.VariableType.num));
            }
            else if (variables.get(variableName).getVariableType().equals(Variable.VariableType.text)) {
                String value = Main.expressionToEvaluate(tokens, variables);
                value = value.substring(1, value.length() - 1);
                variables.put(variableName, new Variable<>(variableName, value, Variable.VariableType.text));
            }
            else {
                throw new Error("Invalid Variable Assignment!");
            }
        }
    }


    public static void print(ArrayList<String> tokens, Dictionary<String, Variable> variables) {
        String toPrint = Main.expressionToEvaluate(tokens, variables);

        if (Main.isNumber(toPrint))
            System.out.print(toPrint);
        else if (toPrint.equals("TRUE") || toPrint.equals("FALSE"))
            System.out.print(toPrint);
        else {
            if (toPrint.charAt(0) != '"' || toPrint.charAt(toPrint.length()-1) != '"')
                throw new Error("Text Not Correct!");

            System.out.print(toPrint.substring(1, toPrint.length()-1));
        }
    }

    public static void printLine(ArrayList<String> tokens, Dictionary<String, Variable> variables) {
        String toPrint = Main.expressionToEvaluate(tokens, variables);

        if (Main.isNumber(toPrint))
            System.out.println(toPrint);
        else if (toPrint.equals("TRUE") || toPrint.equals("FALSE"))
            System.out.println(toPrint);
        else {
            if (toPrint.charAt(0) != '"' || toPrint.charAt(toPrint.length()-1) != '"')
                throw new Error("Text Not Correct!");

            System.out.println(toPrint.substring(1, toPrint.length()-1));
        }
    }

}
