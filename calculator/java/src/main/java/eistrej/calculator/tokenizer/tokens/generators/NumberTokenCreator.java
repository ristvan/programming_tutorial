package eistrej.calculator.tokenizer.tokens.generators;

import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;

public class NumberTokenCreator implements ITokenCreator {
    private String expression=null;
    private int index=0;

    @Override
    public boolean isMatching(String expression){
        this.expression=expression;
        return expression.length() > 0 && Character.isDigit(expression.charAt(0));
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
        return index;
    }
}
