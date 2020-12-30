package ristvan.calculator.tokenizer;

import ristvan.calculator.tokenizer.tokens.IToken;

public interface ITokenizer {
    IToken getNextToken();
}
