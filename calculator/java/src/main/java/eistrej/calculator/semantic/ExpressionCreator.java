package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.*;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.*;

import java.util.Stack;

interface IExpressionWrapper {
    void setLeft(IExpression expression);
    void setRight(IExpression expression);
    IExpression getExpression();
}

public class ExpressionCreator {
    private final ITokenizer tokenizer;
    private Stack<IExpression> expressions = new Stack<>();
    private Stack<IExpressionWrapper> operators = new Stack<>();

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
                IExpressionWrapper expressionWrapper = createOperatorWrapper(token);
                createOperationExpression(expressionWrapper);
            }
            token = tokenizer.getNextToken();
        }
        while (!operators.empty()) {
            IExpressionWrapper expressionWrapper = operators.pop();
            createOperationExpression(expressionWrapper);
        }
        return expressions.pop();
    }

    private void createOperationExpression(IExpressionWrapper expressionWrapper) {
        IExpression right = expressions.pop();
        IExpression left = expressions.pop();
        expressionWrapper.setLeft(left);
        expressionWrapper.setRight(right);
        expressions.push(expressionWrapper.getExpression());
    }

    private IExpressionWrapper createOperatorWrapper(IToken operation) {
        IExpressionWrapper operatorWrapper = null;
        if (operation instanceof IAddition) {
            operatorWrapper = new IExpressionWrapper() {
                AdditionExpression addition = new AdditionExpression();
                @Override
                public void setLeft(IExpression expression) {
                    addition.setAugend(expression);
                }

                @Override
                public void setRight(IExpression expression) {
                    addition.setAddend(expression);
                }

                @Override
                public IExpression getExpression() {
                    return addition;
                }
            };
        } else if (operation instanceof IMinus) {
            operatorWrapper = new IExpressionWrapper() {
                SubtractionExpression subtraction = new SubtractionExpression();
                @Override
                public void setLeft(IExpression expression) {
                    subtraction.setMinuend(expression);
                }

                @Override
                public void setRight(IExpression expression) {
                    subtraction.setSubtrahend(expression);
                }

                @Override
                public IExpression getExpression() {
                    return subtraction;
                }
            };
        } else if (operation instanceof IMultiplication) {
            operatorWrapper = new IExpressionWrapper() {
                MultiplicationExpression multiplication = new MultiplicationExpression();
                @Override
                public void setLeft(IExpression expression) {
                    multiplication.setMultiplier(expression);
                }

                @Override
                public void setRight(IExpression expression) {
                    multiplication.setMulticand(expression);
                }

                @Override
                public IExpression getExpression() {
                    return multiplication;
                }
            };
        } else if (operation instanceof IDivision) {
            operatorWrapper = new IExpressionWrapper() {
                DivisionExpression division = new DivisionExpression();
                @Override
                public void setLeft(IExpression expression) {
                    division.setDividend(expression);
                }

                @Override
                public void setRight(IExpression expression) {
                    division.setDivisor(expression);
                }

                @Override
                public IExpression getExpression() {
                    return division;
                }
            };
        }
        return operatorWrapper;
    }
}
