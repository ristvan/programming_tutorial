package eistrej.calculator.evaluator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Evaluator {

    private Map<String, IntBinaryOperator> operations;

    public Evaluator() {
        operations = new LinkedHashMap<>();
        operations.put("\\+", (left, right) -> left + right);
        operations.put("-", (left, right) -> left - right);
        operations.put("\\*", (left, right) -> left * right);
        operations.put("/", (left, right) -> left / right);
    }

    public int evaluate(String input) {
        if (input.matches("\\s*\\d+\\s*")) {
            return Integer.parseInt(input.trim());
        }
        String[] tokens = null;
        IntBinaryOperator operator = null;
        String delimiter = "";
        for (String regularExpression : operations.keySet()) {
            tokens = input.split(regularExpression);
            if (tokens.length > 1) {
                operator = operations.get(regularExpression);
                delimiter = regularExpression.substring(regularExpression.length() - 1);
                break;
            }
        }
        if (operator == null) {
            return 0;
        }
        String left = String.join(delimiter, Arrays.copyOfRange(tokens, 0,tokens.length - 1));
        String right = tokens[tokens.length - 1];
        return operator.applyAsInt(evaluate(left), evaluate(right));
    }
}
