package eistrej.calculator.evaluator;

import eistrej.calculator.semantic.ExpressionCreator;
import eistrej.calculator.semantic.items.IExpression;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.Tokenizer;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Evaluator {
    public int evaluate(String input) {
        ITokenizer tokenizer = new Tokenizer(input);
        ExpressionCreator expressionCreator = new ExpressionCreator(tokenizer);
        IExpression expression = expressionCreator.createExpression();
        return expression.evaluate();
    }
}
