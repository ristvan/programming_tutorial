package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.*;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.*;

import java.util.Stack;

public class ExpressionCreator {
    private final ITokenizer tokenizer;
    private Stack<IExpression> expressions = new Stack<>();
    private Stack<IOperator> operators = new Stack<>();

    public ExpressionCreator(ITokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public IExpression createExpression() {
        IToken token = tokenizer.getNextToken();
        while (token != null) {
            if (token instanceof INumber) {
                INumber number = (INumber) token;
                expressions.push(new NumberExpression(number.getValue()));
            } else if (operators.empty()) {
                operators.push(createOperatorWrapper(token));
            } else {
                IOperator operation = createOperatorWrapper(token);
                createOperationExpression(operation);
                expressions.push(operation);
            }
            token = tokenizer.getNextToken();
        }
        while (!operators.empty()) {
            IOperator operator = operators.pop();
            createOperationExpression(operator);
            expressions.push(operator);
        }
        return expressions.pop();
    }

    private void createOperationExpression(IOperator operator) {
        IExpression right = expressions.pop();
        IExpression left = expressions.pop();
        operator.setLeft(left);
        operator.setRight(right);
    }

    private IOperator createOperatorWrapper(IToken operation) {
        IOperator operator = null;
        if (operation instanceof IAddition) {
            operator = new AdditionExpression();
        } else if (operation instanceof IMinus) {
            operator = new SubtractionExpression();
        } else if (operation instanceof IMultiplication) {
            operator = new MultiplicationExpression();
        } else if (operation instanceof IDivision) {
            operator = new DivisionExpression();
        }
        return operator;
    }
}
