import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Ifer {

    private final ArrayList<ArrayList<String>> firstExpression;
    private final ArrayList<ArrayList<String>> secondExpressions;

    private final boolean condition;

    private final Dictionary<String, Variable> originalVariables;
    private final Dictionary<String, Variable> localVariables = new Hashtable<>();

    public Ifer(ArrayList<ArrayList<String>> firstExpression, ArrayList<ArrayList<String>> secondExpression, boolean condition, Dictionary<String, Variable> variables) {
        this.firstExpression = firstExpression;
        this.secondExpressions = secondExpression;

        this.condition = condition;

        Enumeration<String> keys = variables.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            localVariables.put(key, variables.get(key));
        }

        this.originalVariables = variables;
    }

    public void run() {
        if (condition)
            while (!firstExpression.isEmpty())
                Main.runExpression(firstExpression, firstExpression.remove(0), localVariables);
        else
            while (!secondExpressions.isEmpty())
                Main.runExpression(secondExpressions, secondExpressions.remove(0), localVariables);

        Enumeration<String> keys = localVariables.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (originalVariables.get(key) != null)
                originalVariables.put(key, localVariables.get(key));
        }
    }

}
