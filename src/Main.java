import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static java.lang.Double.parseDouble;

public class Main {
    public static FileInputStream fileInputStream;

    public static ArrayList<ArrayList<String>> globalExpressions;
    public static Dictionary<String, Variable> globalVariables = new Hashtable<>();

    public static void main(String[] args){
        // Get File To Run
        System.out.print("\nInput the file to interpret: ");
        Scanner scanner = new Scanner(System.in);
        final String fileName = scanner.nextLine();

        // Tokenize Text
        Tokenizer tokenizer = new Tokenizer();
        try {
            if (fileName.endsWith(".simple")) {
                fileInputStream = new FileInputStream(fileName);
            }
            else {
                fileInputStream = new FileInputStream(fileName + ".simple");
            }

            globalExpressions = tokenizer.tokenize(fileInputStream);

            // Run All Global Expressions
            while (!globalExpressions.isEmpty())
                runExpression(globalExpressions, globalExpressions.remove(0), globalVariables);
        }
        catch (IOException exception) {
            throw new Error("Error Getting File: " + fileName);
        }
    }

    public static void runExpression(ArrayList<ArrayList<String>> expressions, ArrayList<String> tokens, Dictionary<String, Variable> variables) {

        ArrayList<String> statementTokens = (ArrayList<String>) tokens.clone();

        while (!statementTokens.isEmpty()) {
            String token = statementTokens.remove(0);

            // Variable Declarations
            if (token.equals("num")) {
                StatementRunner.declareVariable(Variable.VariableType.num, statementTokens, variables);
            }
            else if (token.equals("bool")) {
                StatementRunner.declareVariable(Variable.VariableType.bool, statementTokens, variables);
            }
            else if (token.equals("text")) {
                StatementRunner.declareVariable(Variable.VariableType.text, statementTokens, variables);
            }

            // Variable Assignment
            else if (variables.get(token) != null) {
                StatementRunner.reassignVariable(token, statementTokens, variables);
            }

            // In Built Commands
            else if (token.equals("print")) {
                StatementRunner.print(statementTokens, variables);
            }

            else if (token.equals("printLine")) {
                StatementRunner.printLine(statementTokens, variables);
            }

            else if (token.equals("input")) {

                String variable = statementTokens.remove(0);
                Scanner scanner = new Scanner(System.in);

                if (!variables.get(variable).getVariableType().equals(Variable.VariableType.text))
                    throw new Error("Input Variable Must Be Of Type Text");

                variables.get(variable).setValue(scanner.nextLine());
            }

            else if (token.equals("exit")) {
                System.exit(0);
                return;
            }

            else if (token.equals("repeat")) {
                double times = parseDouble(expressionToEvaluate(statementTokens, variables));

                String variableName = statementTokens.remove(0);

                if (variables.get(variableName) != null)
                    throw new Error("Iterator Variable Is Already Defined! Variable Name: " + variableName);

                if (!expressions.remove(0).get(0).equals("then"))
                    throw new Error("No Then After Repeat!");

                Repeater repeater = new Repeater(times, variableName, getInsideExpression(expressions), variables);
                repeater.run();
            }

            else if (token.equals("characterAt")) {
                if (statementTokens.get(0) == null)
                    throw new Error("No Variable Given To characterAt!");

                String variable = statementTokens.remove(0);

                int index;
                try {
                    index = (int) Double.parseDouble(expressionToEvaluate(statementTokens, variables));
                }
                catch (NumberFormatException exception) {
                    throw new Error("Invalid Index For Character At. Must be an integer (whole number)! ");
                }

                String expression = expressionToEvaluate(statementTokens, variables);

                if (index < 0)
                    throw new Error("Character At Index Cannot Be Less Than 0!");
                else if (index > expression.length() - 2)
                    throw new Error("Character At Index Cannot Exceed Text Length!");

                if (variables.get(variable) == null)
                    throw new Error("Given Variable For characterAt Is Invalid! Variable Name: " + variable);

                variables.get(variable).setValue(expression.charAt(index));
            }

            else if (token.equals("if")) {

                String conditionString = expressionToEvaluate(statementTokens, variables);
                boolean condition;
                if (conditionString.equals("TRUE"))
                    condition = true;
                else if (conditionString.equals("FALSE"))
                    condition = false;
                else
                    throw new Error("Condition In If Statement Invalid!");

                if (!expressions.remove(0).get(0).equals("then"))
                    throw new Error("No Then After If!");

                ArrayList<ArrayList<String>> firstCondition = getInsideExpression(expressions);

                if (!expressions.isEmpty() && expressions.get(0).get(0).equals("else")) {

                    expressions.remove(0);

                    if (!expressions.remove(0).get(0).equals("then"))
                        throw new Error("No Then After Else!");

                    ArrayList<ArrayList<String>> secondCondition = getInsideExpression(expressions);
                    Ifer ifStatement = new Ifer(firstCondition, secondCondition, condition, variables);
                    ifStatement.run();
                }
                else {
                    Ifer ifStatement = new Ifer(firstCondition, new ArrayList<>(), condition, variables);
                    ifStatement.run();
                }
            }
            else {
                throw new Error("Token Invalid: " + token);
            }
        }

    }



    public static ArrayList<ArrayList<String>> getInsideExpression(ArrayList<ArrayList<String>> expressions) {
        ArrayList<ArrayList<String>> expressionsToGive = new ArrayList<>();
        int brackets = 0;

        while (true) {
            if (expressions.get(0) == null)
                throw new Error("There Is No Closing 'end' For 'then' Statement!");

            ArrayList<String> item = expressions.remove(0);

            if (item.get(0).equals("then"))
                brackets++;


            if (item.get(0).equals("end")) {
                if (brackets < 1)
                    break;
                brackets--;
            }

            expressionsToGive.add(item);
        }

        return expressionsToGive;
    }



    public static String expressionToEvaluate(ArrayList<String> statementTokens, Dictionary<String, Variable> variables) {
        ArrayList<String> toGive = new ArrayList<>();

        String firstParentheses = statementTokens.remove(0);

        if (!Objects.equals(firstParentheses, "("))
            throw new Error("Expression To Evaluate Is Invalid");

        int parentheses = 1;
        while (parentheses != 0) {
            if (statementTokens.get(0) == null)
                throw new Error("Opening Parentheses Does Not Contain Ending Parentheses!");

            String nextToken = statementTokens.remove(0);
            if (nextToken.equals("("))
                parentheses++;
            else if (nextToken.equals(")"))
                parentheses--;

            toGive.add(nextToken);
        }

        return ExpressionManager.evaluate(toGive, variables);
    }



    public static boolean isNumber(String x) {
        try {
            parseDouble(x);
            return true;
        }
        catch (NumberFormatException error) {
            return false;
        }
    }
}