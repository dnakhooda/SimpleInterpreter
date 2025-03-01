import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Tokenizer {
    public Tokenizer() {}

    private String currentToken = "";
    private boolean isString = false;
    private ArrayList<String> currentExpression = new ArrayList<>();

    public ArrayList<ArrayList<String>> tokenize(FileInputStream fileInputStream) throws IOException {

        ArrayList<ArrayList<String>> tokens = new ArrayList<>();
        tokens.add(new ArrayList<>());

        int character = fileInputStream.read();
        while (character != -1) {
            tokenize(character, tokens);
            character = fileInputStream.read();
        }

        return tokens;
    }

    private void tokenize(int character, ArrayList<ArrayList<String>> tokens) {
        if (character == 59) {
            if (!Objects.equals(currentToken, ""))
                currentExpression.add(currentToken);
            currentToken = "";
            tokens.add(currentExpression);
            currentExpression = new ArrayList<>();
            return;
        }

        if (character == 34) {
            currentToken = currentToken + (char) character;
            if (isString) {
                if (!Objects.equals(currentToken, ""))
                    currentExpression.add(currentToken);
                currentToken = "";
            }
            isString = !isString;
            return;
        }

        if ((character < 33 || character > 126) && !isString) {
            if (!Objects.equals(currentToken, ""))
                currentExpression.add(currentToken);
            currentToken = "";
            return;
        }

        currentToken = currentToken + (char) character;
    }
}
