package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.*;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.*;

import java.util.Stack;

public class ExpressionCreator {
    private final ITokenizer tokenizer;
    private Stack<IExpression> expressions = new Stack<>();
    private Stack<IOperatorExpression> operators = new Stack<>();
    private IToken currentToken;

    public ExpressionCreator(ITokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public IExpression createExpression() {
        currentToken = tokenizer.getNextToken();
        while (currentToken != null) {
            handleToken();
            currentToken = tokenizer.getNextToken();
        }
        moveRemainingOperatorsIntoExpressions();
        return expressions.pop();
    }

    private void handleToken() {
        if (currentToken instanceof INumber) {
            handleNumber();
        } else {
            handleOperator();
        }
    }

    private void handleOperator() {
        IOperatorExpression newOperatorExpression = createOperatorExpression(currentToken);
        if (!operators.empty()) {
            IOperatorExpression lastStoredOperation = operators.peek();
            int newPrecedence = getPrecedence(newOperatorExpression);
            int lastStoredPrecedence = getPrecedence(lastStoredOperation);
            while (!operators.empty() && lastStoredPrecedence >= newPrecedence) {
                fillOperationExpression(lastStoredOperation);
                operators.pop();
                expressions.push(lastStoredOperation);
                if (!operators.empty()) {
                    lastStoredOperation = operators.peek();
                    lastStoredPrecedence = getPrecedence(lastStoredOperation);
                }
            }
        }
        operators.push(newOperatorExpression);
    }

    private void handleNumber() {
        INumber number = (INumber) currentToken;
        expressions.push(new NumberExpression(number.getValue()));
    }

    private void moveRemainingOperatorsIntoExpressions() {
        while (!operators.empty()) {
            IOperatorExpression operator = operators.pop();
            fillOperationExpression(operator);
            expressions.push(operator);
        }
    }

    private void fillOperationExpression(IOperatorExpression operator) {
        IExpression right = expressions.pop();
        IExpression left = expressions.pop();
        operator.setLeft(left);
        operator.setRight(right);
    }

    private int getPrecedence(IOperatorExpression operator) {
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

    private IOperatorExpression createOperatorExpression(IToken operation) {
        IOperatorExpression operator = null;
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
