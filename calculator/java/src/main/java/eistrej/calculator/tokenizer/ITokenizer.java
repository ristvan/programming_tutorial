package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.IToken;

public interface ITokenizer {
    IToken getNextToken();
}
