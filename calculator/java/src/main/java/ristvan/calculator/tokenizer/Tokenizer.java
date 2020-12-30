package ristvan.calculator.tokenizer;

import ristvan.calculator.tokenizer.tokens.IToken;
import ristvan.calculator.tokenizer.tokens.generators.*;

import java.util.LinkedList;
import java.util.List;


public class Tokenizer implements ITokenizer {
    private String expression;
    private final List<ITokenCreator> tokenCreators;

    public Tokenizer(String expression) {
        this.expression = expression.trim();
        tokenCreators = new LinkedList<>();
        tokenCreators.add(new NumberTokenCreator());
        tokenCreators.add(new AdditionTokenGenerator());
        tokenCreators.add(new MinusTokenGenerator());
        tokenCreators.add(new MultiplicationTokenGenerator());
        tokenCreators.add(new DivisionTokenGenerator());
    }

    @Override
    public IToken getNextToken() {
        IToken token = null;
        for (ITokenCreator tokenCreator : tokenCreators) {
            if (tokenCreator.isMatching(expression)) {
                token = tokenCreator.getToken();
                int length = tokenCreator.getTokenLength();
                if (length < expression.length()) {
                    expression = expression.substring(length).trim();
                } else {
                    expression = "";
                }
                break;
            }
        }
        return token;
    }
}
