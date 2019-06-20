package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;

public class Tokenizer implements ITokenizer {
    private final String expression;

    public Tokenizer(String expression) {
        this.expression = expression;
    }
    @Override
    public IToken getNextToken() {
        return new INumber() {
            @Override
            public int getValue() {
                return Integer.parseInt(expression.trim());
            }
        };
    }
}
