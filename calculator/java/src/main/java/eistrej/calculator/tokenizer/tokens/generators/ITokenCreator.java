package eistrej.calculator.tokenizer.tokens.generators;

import eistrej.calculator.tokenizer.tokens.IToken;

public interface ITokenCreator {
    boolean isMatching(String expression);
    IToken getToken();
    int getTokenLength();
}
