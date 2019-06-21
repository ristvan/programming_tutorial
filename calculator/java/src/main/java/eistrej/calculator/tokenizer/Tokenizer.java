package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.*;

import java.util.LinkedList;
import java.util.List;

interface ITokenCreator {
    boolean isMatching(String expression);
    IToken getToken();
    int getTokenLength();
}

class NumberTokenCreator implements ITokenCreator {
    private String expression=null;
    private int index=0;

    @Override
    public boolean isMatching(String expression){
        this.expression=expression;
        return Character.isDigit(expression.charAt(0));
    }

    @Override
    public IToken getToken(){
        while(index<expression.length()&&Character.isDigit(expression.charAt(index))){
            index++;
        }
        String numberAsString=expression.substring(0,index);
        return (INumber) () -> Integer.parseInt(numberAsString);
    }

    @Override
    public int getTokenLength(){
        return 1;
    }
}

class AdditionTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.charAt(0) == '+';
    }

    @Override
    public IToken getToken() {
        return new IAddition() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}

class MinusTokenGenerator implements ITokenCreator {
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

class MultiplicationTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.charAt(0) == '*';
    }

    @Override
    public IToken getToken() {
        return new IMultiplication() {
        };
    }

    @Override
    public int getTokenLength() {
        return 1;
    }
}

class DivisionTokenGenerator implements ITokenCreator {
    @Override
    public boolean isMatching(String expression) {
        return expression.charAt(0) == '/';
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


public class Tokenizer implements ITokenizer {
    private String expression;

    public Tokenizer(String expression) {
        this.expression = expression.trim();
    }

    @Override
    public IToken getNextToken() {
        List<ITokenCreator> tokenCreators = new LinkedList<ITokenCreator>();
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
                expression = expression.substring(0, length);
            }
        }
        return token;
    }
}
