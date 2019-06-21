package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.*;

public class Tokenizer implements ITokenizer {
    private final String expression;

    public Tokenizer(String expression) {
        this.expression = expression.trim();
    }
    @Override
    public IToken getNextToken() {
        if (expression.startsWith("+")) {
            return new IAddition() {
            };
        }
        if (expression.startsWith("-")) {
            return new IMinus() {
            };
        }
        if (expression.startsWith("*")) {
            return new IMultiplication() {
            };
        }
        if (expression.startsWith("/")) {
            return new IDivision() {
            };
        }

        return new INumber() {
            @Override
            public int getValue() {
                return Integer.parseInt(expression.trim());
            }
        };
    }
}
