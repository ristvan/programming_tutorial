package ristvan.calculator.evaluator;

import ristvan.calculator.semantic.ExpressionCreator;
import ristvan.calculator.semantic.items.IExpression;
import ristvan.calculator.tokenizer.Tokenizer;
import ristvan.calculator.tokenizer.TokenizerImpl;

public class Evaluator {
    public int evaluate(String input) {
        Tokenizer tokenizer = new TokenizerImpl(input);
        ExpressionCreator expressionCreator = new ExpressionCreator(tokenizer);
        IExpression expression = expressionCreator.createExpression();
        return expression.evaluate();
    }
}
