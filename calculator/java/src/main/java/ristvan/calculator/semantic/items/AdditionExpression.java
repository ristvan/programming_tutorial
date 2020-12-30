package ristvan.calculator.semantic.items;

public class AdditionExpression implements OperatorExpression {
    private Expression augend;
    private Expression addend;

    @Override
    public int evaluate() {
        return augend.evaluate() + addend.evaluate();
    }

    @Override
    public void setLeft(Expression expression) {
        augend = expression;
    }

    @Override
    public void setRight(Expression expression) {
        addend = expression;
    }

    @Override
    public Expression getLeft() {
        return augend;
    }

    @Override
    public Expression getRight() {
        return addend;
    }
}
