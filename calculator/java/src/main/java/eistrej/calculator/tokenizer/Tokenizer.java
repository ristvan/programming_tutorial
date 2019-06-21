package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.*;
import eistrej.calculator.tokenizer.tokens.generators.*;

import java.util.LinkedList;
import java.util.List;


public class Tokenizer implements ITokenizer {
    private String expression;

    public Tokenizer(String expression) {
        this.expression = expression.trim();
    }

    @Override
    public IToken getNextToken() {
        List<ITokenCreator> tokenCreators = new LinkedList<>();
        tokenCreators.add(new NumberTokenCreator());
        tokenCreators.add(new AdditionTokenGenerator());
        tokenCreators.add(new MinusTokenGenerator());
        tokenCreators.add(new MultiplicationTokenGenerator());
        tokenCreators.add(new DivisionTokenGenerator());

        IToken token = null;
        for (ITokenCreator tokenCreator : tokenCreators) {
            if (tokenCreator.isMatching(expression)) {
                token = tokenCreator.getToken();
                int length = tokenCreator.getTokenLength();
                if (length < expression.length()) {
                    expression = expression.substring(length + 1);
                } else {
                    expression = "";
                }
                break;
            }
        }
        return token;
    }
}
