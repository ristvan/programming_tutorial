package eistrej.calculator.evaluator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Evaluator {
    public int evaluate(String input) {
        if (input.matches("\\s*\\d+\\s*")) {
            return Integer.parseInt(input.trim());
        }
        Map<String, IntBinaryOperator> operations = new LinkedHashMap<>();
        operations.put("\\+", (left, right) -> left + right);
        operations.put("-", (left, right) -> left - right);
        operations.put("\\*", (left, right) -> left * right);
        operations.put("/", (left, right) -> left / right);
        String[] tokens = null;
        IntBinaryOperator operator = null;
        for (String regularExpression : operations.keySet()) {
            tokens = input.split(regularExpression);
            if (tokens.length > 1) {
                operator = operations.get(regularExpression);
                break;
            }
        }
        if (operator == null) {
            return 0;
        }
//        String left = String.join("+", Arrays.copyOfRange(tokens, 1, tokens.length));
        return operator.applyAsInt(evaluate(tokens[0]), evaluate(tokens[1]));
    }
}
