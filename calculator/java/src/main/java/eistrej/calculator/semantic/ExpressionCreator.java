package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.*;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.IAddition;
import eistrej.calculator.tokenizer.tokens.IMultiplication;
import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;

public class ExpressionCreator {
    private final ITokenizer tokenizer;

    public ExpressionCreator(ITokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public IExpression createExpression() {
        INumber left = (INumber) tokenizer.getNextToken();
        IToken operation = tokenizer.getNextToken();
        INumber right = (INumber) tokenizer.getNextToken();
        if (operation == null) {
            return new NumberExpression(left.getValue());
        }
        if (operation instanceof IAddition) {
            AdditionExpression expression = new AdditionExpression();
            expression.setAugend(new NumberExpression(left.getValue()));
            expression.setAddend(new NumberExpression(right.getValue()));
            return expression;
        }
        if (operation instanceof IMultiplication) {
            MultiplicationExpression expression = new MultiplicationExpression();
            expression.setMultiplier(new NumberExpression(left.getValue()));
            expression.setMulticand(new NumberExpression(right.getValue()));
            return expression;
        }
        SubtractionExpression expression = new SubtractionExpression();
        expression.setMinuend(new NumberExpression(left.getValue()));
        expression.setSubtrahend(new NumberExpression(right.getValue()));
        return expression;
    }
}
