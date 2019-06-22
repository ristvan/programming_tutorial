package eistrej.calculator.tokenizer.tokens.generators;

import eistrej.calculator.tokenizer.tokens.IDivision;
import eistrej.calculator.tokenizer.tokens.IToken;

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
