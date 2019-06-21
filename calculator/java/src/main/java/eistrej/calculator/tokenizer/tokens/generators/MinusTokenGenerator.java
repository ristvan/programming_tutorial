package eistrej.calculator.tokenizer.tokens.generators;

import eistrej.calculator.tokenizer.tokens.IMinus;
import eistrej.calculator.tokenizer.tokens.IToken;

public class MinusTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.charAt(0) == '-';
    }

    @Override
    public IToken getToken() {
        return new IMinus() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}
