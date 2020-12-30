package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.IToken;

public interface ITokenCreator {
    boolean isMatching(String expression);
    IToken getToken();
    int getTokenLength();
}
