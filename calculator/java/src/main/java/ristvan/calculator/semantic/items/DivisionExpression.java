package ristvan.calculator.semantic.items;

public class DivisionExpression implements OperatorExpression {
    private Expression dividend;
    private Expression divisor;

    @Override
    public int evaluate() {
        return dividend.evaluate() / divisor.evaluate();
    }

    @Override
    public Expression getLeft() {
        return dividend;
    }

    @Override
    public Expression getRight() {
        return divisor;
    }

    @Override
    public void setLeft(Expression dividend) {
        this.dividend = dividend;
    }

    @Override
    public void setRight(Expression divisor) {
        this.divisor = divisor;
    }
}
