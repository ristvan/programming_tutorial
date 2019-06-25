package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.AdditionExpression;
import eistrej.calculator.semantic.items.IExpression;
import eistrej.calculator.semantic.items.NumberExpression;
import eistrej.calculator.tokenizer.ITokenizer;
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
        AdditionExpression expression = new AdditionExpression();
        expression.addLeft(new NumberExpression(left.getValue()));
        expression.addRight(new NumberExpression(right.getValue()));
        return expression;
    }
}
