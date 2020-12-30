package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.Addition;
import ristvan.calculator.tokenizer.tokens.Token;

public class AdditionTokenGenerator implements TokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.length() > 0 && expression.charAt(0) == '+';
    }

    @Override
    public Token getToken() {
        return new Addition() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}

