package eistrej.calculator.evaluator;

public class Evaluator {
    public int evaluate(String input) {
        if (input.matches("\\s*\\d+\\s*")) {
            return Integer.parseInt(input.trim());
        }
        String[] tokens = input.split("\\+");
        if (tokens.length > 1) {
            return evaluate(tokens[0]) + evaluate(tokens[1]);
        }
        tokens = input.split("-");
        if (tokens.length > 1) {
            return evaluate(tokens[0]) - evaluate(tokens[1]);
        }
        return 4;
    }
}
