public class Variable<T> {
    public enum VariableType {
        num,
        bool,
        text,
    }

    private T value;
    private String name;
    private VariableType variableType;

    public Variable(String name, T value, VariableType variableType) {
        this.value = value;
        this.name = name;
        this.variableType = variableType;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }
}
