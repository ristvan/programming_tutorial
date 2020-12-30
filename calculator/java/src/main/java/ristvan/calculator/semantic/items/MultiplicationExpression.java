package ristvan.calculator.semantic.items;

public class MultiplicationExpression implements OperatorExpression {
    private Expression multiplier;
    private Expression multicand;

    @Override
    public int evaluate() {
        return multiplier.evaluate() * multicand.evaluate();
    }

    @Override
    public Expression getLeft() {
        return multiplier;
    }

    @Override
    public void setLeft(Expression multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public Expression getRight() {
        return multicand;
    }

    @Override
    public void setRight(Expression multicand) {
        this.multicand = multicand;
    }
}
