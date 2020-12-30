package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.IAddition;
import ristvan.calculator.tokenizer.tokens.IToken;

public class AdditionTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.length() > 0 && expression.charAt(0) == '+';
    }

    @Override
    public IToken getToken() {
        return new IAddition() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}

