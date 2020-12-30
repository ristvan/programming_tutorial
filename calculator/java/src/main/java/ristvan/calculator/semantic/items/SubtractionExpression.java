package ristvan.calculator.semantic.items;

public class SubtractionExpression implements OperatorExpression {
    private Expression minuend;
    private Expression subtrahend;

    @Override
    public int evaluate() {
        return minuend.evaluate() - subtrahend.evaluate();
    }

    @Override
    public void setLeft(Expression minuend) {
        this.minuend = minuend;
    }

    @Override
    public void setRight(Expression subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public Expression getLeft() {
        return minuend;
    }

    @Override
    public Expression getRight() {
        return subtrahend;
    }
}
