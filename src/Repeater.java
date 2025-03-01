import java.util.*;

public class Repeater {

    private final ArrayList<ArrayList<String>> expressionsRefill;

    private final double times;
    private double i = 1;
    private final String iteratorName;

    private Dictionary<String, Variable> originalVariables;
    private final Dictionary<String, Variable> localVariables = new Hashtable<>();

    public Repeater(double times, String iteratorName, ArrayList<ArrayList<String>> givenExpressions, Dictionary<String, Variable> variables) {
        this.expressionsRefill = givenExpressions;
        this.times = times;

        Enumeration<String> keys = variables.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            localVariables.put(key, variables.get(key));
        }

        originalVariables = variables;

        this.iteratorName = iteratorName;

        localVariables.put(iteratorName, new Variable<>(iteratorName, i, Variable.VariableType.num));
    }

    public void run() {
        while (i <= times) {
            ArrayList<ArrayList<String>> expressions = (ArrayList<ArrayList<String>>) expressionsRefill.clone();

            while (!expressions.isEmpty())
                Main.runExpression(expressions, expressions.remove(0), localVariables);

            i++;
            localVariables.put(iteratorName, new Variable<>(iteratorName, i, Variable.VariableType.num));
        }

        Enumeration<String> keys = localVariables.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (originalVariables.get(key) != null)
                originalVariables.put(key, localVariables.get(key));
        }
    }

}
