package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.IDivision;
import ristvan.calculator.tokenizer.tokens.IToken;

public class DivisionTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.length() > 0 && expression.charAt(0) == '/';
    }

    @Override
    public IToken getToken() {
        return new IDivision() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}
