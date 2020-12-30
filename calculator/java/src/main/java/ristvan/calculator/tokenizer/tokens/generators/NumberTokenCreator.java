package ristvan.calculator.tokenizer.tokens.generators;

import ristvan.calculator.tokenizer.tokens.Number;
import ristvan.calculator.tokenizer.tokens.Token;

public class NumberTokenCreator implements TokenCreator {
    private String expression = null;
    private int index = 0;

    @Override
    public boolean isMatching(String expression){
        this.expression = expression;
        index = 0;
        return expression.length() > 0 && Character.isDigit(expression.charAt(0));
    }

    @Override
    public Token getToken(){
        while (index<expression.length() && Character.isDigit(expression.charAt(index))){
            index++;
        }
        String numberAsString = expression.substring(0, index);
        return (Number) () -> Integer.parseInt(numberAsString);
    }

    @Override
    public int getTokenLength(){
        return index;
    }
}
