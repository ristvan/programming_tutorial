package eistrej.calculator.evaluator;

import eistrej.calculator.semantic.ExpressionCreator;
import eistrej.calculator.semantic.items.IExpression;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.Tokenizer;

public class Evaluator {
    public int evaluate(String input) {
        ITokenizer tokenizer = new Tokenizer(input);
        ExpressionCreator expressionCreator = new ExpressionCreator(tokenizer);
        IExpression expression = expressionCreator.createExpression();
        return expression.evaluate();
    }
}
