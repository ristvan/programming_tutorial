package eistrej.calculator.evaluator;

public class Evaluator {
    public int evaluate(String input) {
        if (input.matches("\\s*\\d+\\s*")) {
            return Integer.parseInt(input.trim());
        }
        String[] tokens = input.split("\\+");
        return Integer.parseInt(tokens[0]) + Integer.parseInt(tokens[1]);
    }
}
