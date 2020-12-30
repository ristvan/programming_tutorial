package ristvan.calculator.tokenizer;

import ristvan.calculator.tokenizer.tokens.Token;

public interface Tokenizer {
    Token getNextToken();
}
