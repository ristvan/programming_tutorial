package ristvan.calculator.semantic;

import ristvan.calculator.semantic.items.*;
import ristvan.calculator.tokenizer.Tokenizer;
import ristvan.calculator.tokenizer.tokens.*;
import ristvan.calculator.tokenizer.tokens.Number;

import java.util.Stack;

public class ExpressionCreator {
    private final Tokenizer tokenizer;
    private Stack<IExpression> expressions = new Stack<>();
    private Stack<IOperatorExpression> operators = new Stack<>();

    public ExpressionCreator(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public IExpression createExpression() {
        Token currentToken = tokenizer.getNextToken();
        while (currentToken != null) {
            handleToken(currentToken);
            currentToken = tokenizer.getNextToken();
        }
        moveRemainingOperatorsIntoExpressions();
        return expressions.pop();
    }

    private void handleToken(Token token) {
        if (token instanceof Number) {
            handleNumber((Number) token);
        } else {
            handleOperator(token);
        }
    }

    private void handleOperator(Token operatorToken) {
        IOperatorExpression newOperatorExpression = createOperatorExpression(operatorToken);
        if (!operators.empty()) {
            while (storedOperationHasHigherPrecedence(newOperatorExpression)) {
                IOperatorExpression lastStoredOperation = operators.pop();
                fillOperationExpression(lastStoredOperation);
                expressions.push(lastStoredOperation);
            }
        }
        operators.push(newOperatorExpression);
    }

    private boolean storedOperationHasHigherPrecedence(IOperatorExpression newOperatorExpression) {
        if (operators.empty()) {
            return false;
        }
        IOperatorExpression lastStoredOperation = operators.peek();
        return getPrecedence(lastStoredOperation) >= getPrecedence(newOperatorExpression);
    }

    private void handleNumber(Number numberToken) {
        expressions.push(new NumberExpression(numberToken.getValue()));
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

    private IOperatorExpression createOperatorExpression(Token operation) {
        IOperatorExpression operator = null;
        if (operation instanceof Addition) {
            operator = new AdditionExpression();
        } else if (operation instanceof Minus) {
            operator = new SubtractionExpression();
        } else if (operation instanceof Multiplication) {
            operator = new MultiplicationExpression();
        } else if (operation instanceof Division) {
            operator = new DivisionExpression();
        }
        return operator;
    }
}
