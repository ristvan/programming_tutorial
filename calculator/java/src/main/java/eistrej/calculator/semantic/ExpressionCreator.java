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
                operators.push(createOperatorExpression(token));
            } else {
                IOperator new_operation = createOperatorExpression(token);
                IOperator last_stored_operation = operators.peek();
                int new_precedence = getPrecedence(new_operation);
                int last_stored_precedence = getPrecedence(last_stored_operation);
                while (!operators.empty() && last_stored_precedence >= new_precedence) {
                    fillOperationExpression(last_stored_operation);
                    operators.pop();
                    expressions.push(last_stored_operation);
                    if (!operators.empty()) {
                        last_stored_operation = operators.peek();
                        last_stored_precedence = getPrecedence(last_stored_operation);
                    }
                }
                operators.push(new_operation);
            }
            token = tokenizer.getNextToken();
        }
        while (!operators.empty()) {
            IOperator operator = operators.pop();
            fillOperationExpression(operator);
            expressions.push(operator);
        }
        return expressions.pop();
    }

    private void fillOperationExpression(IOperator operator) {
        IExpression right = expressions.pop();
        IExpression left = expressions.pop();
        operator.setLeft(left);
        operator.setRight(right);
    }

    private int getPrecedence(IOperator operator) {
        int precedence = 1;
        if (operator instanceof AdditionExpression || operator instanceof SubtractionExpression) {
            return precedence;
        }
        precedence++;
        if (operator instanceof MultiplicationExpression || operator instanceof DivisionExpression) {
            return precedence;
        }
        return 0;
    }

    private IOperator createOperatorExpression(IToken operation) {
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
