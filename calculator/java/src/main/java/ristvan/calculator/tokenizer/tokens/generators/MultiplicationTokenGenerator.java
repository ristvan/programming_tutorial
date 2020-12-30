package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.Multiplication;
import ristvan.calculator.tokenizer.tokens.Token;

public class MultiplicationTokenGenerator implements TokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.length() > 0 && expression.charAt(0) == '*';
    }

    @Override
    public Token getToken() {
        return new Multiplication() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}
