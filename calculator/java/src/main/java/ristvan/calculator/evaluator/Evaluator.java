package ristvan.calculator.evaluator;

import ristvan.calculator.semantic.ExpressionCreator;
import ristvan.calculator.semantic.items.IExpression;
import ristvan.calculator.tokenizer.ITokenizer;
import ristvan.calculator.tokenizer.Tokenizer;

public class Evaluator {
    public int evaluate(String input) {
        ITokenizer tokenizer = new Tokenizer(input);
        ExpressionCreator expressionCreator = new ExpressionCreator(tokenizer);
        IExpression expression = expressionCreator.createExpression();
        return expression.evaluate();
    }
}
