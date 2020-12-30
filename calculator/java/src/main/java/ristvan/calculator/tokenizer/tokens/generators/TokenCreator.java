package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.Token;

public interface TokenCreator {
    boolean isMatching(String expression);
    Token getToken();
    int getTokenLength();
}
