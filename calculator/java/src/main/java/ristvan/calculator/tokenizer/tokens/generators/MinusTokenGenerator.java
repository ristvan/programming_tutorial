package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.Minus;
import ristvan.calculator.tokenizer.tokens.Token;

public class MinusTokenGenerator implements TokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.length() > 0 && expression.charAt(0) == '-';
    }

    @Override
    public Token getToken() {
        return new Minus() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}
