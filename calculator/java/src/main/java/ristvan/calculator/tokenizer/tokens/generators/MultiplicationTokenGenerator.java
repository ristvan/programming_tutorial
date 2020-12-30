package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.IMultiplication;
import ristvan.calculator.tokenizer.tokens.IToken;

public class MultiplicationTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.length() > 0 && expression.charAt(0) == '*';
    }

    @Override
    public IToken getToken() {
        return new IMultiplication() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}
