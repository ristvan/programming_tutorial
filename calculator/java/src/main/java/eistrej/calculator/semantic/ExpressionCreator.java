package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.*;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.*;

import java.util.Stack;

public class ExpressionCreator {
    private final ITokenizer tokenizer;
    private Stack<IExpression> expressions = new Stack<>();
    private Stack<IOperatorExpression> operators = new Stack<>();

    public ExpressionCreator(ITokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public IExpression createExpression() {
        IToken currentToken = tokenizer.getNextToken();
        while (currentToken != null) {
            handleToken(currentToken);
            currentToken = tokenizer.getNextToken();
        }
        moveRemainingOperatorsIntoExpressions();
        return expressions.pop();
    }

    private void handleToken(IToken token) {
        if (token instanceof INumber) {
            handleNumber((INumber) token);
        } else {
            handleOperator(token);
        }
    }

    private void handleOperator(IToken operatorToken) {
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

    private void handleNumber(INumber numberToken) {
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
