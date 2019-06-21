package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.IAddition;
import eistrej.calculator.tokenizer.tokens.IMinus;
import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;

public class Tokenizer implements ITokenizer {
    private final String expression;

    public Tokenizer(String expression) {
        this.expression = expression.trim();
    }
    @Override
    public IToken getNextToken() {
        if (expression.equals("+")) {
            return new IAddition() {
            };
        }
        if (expression.equals("-")) {
            return new IMinus() {
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