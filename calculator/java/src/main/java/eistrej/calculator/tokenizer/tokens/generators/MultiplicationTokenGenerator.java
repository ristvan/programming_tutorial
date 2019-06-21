package eistrej.calculator.tokenizer.tokens.generators;

import eistrej.calculator.tokenizer.tokens.IMultiplication;
import eistrej.calculator.tokenizer.tokens.IToken;

public class MultiplicationTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.charAt(0) == '*';
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
