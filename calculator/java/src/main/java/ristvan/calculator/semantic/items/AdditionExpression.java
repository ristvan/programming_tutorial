package ristvan.calculator.semantic.items;

public class AdditionExpression implements IOperatorExpression {
    private IExpression augend;
    private IExpression addend;

    @Override
    public int evaluate() {
        return augend.evaluate() + addend.evaluate();
    }

    @Override
    public void setLeft(IExpression expression) {
        augend = expression;
    }

    @Override
    public void setRight(IExpression expression) {
        addend = expression;
    }

    @Override
    public IExpression getLeft() {
        return augend;
    }

    @Override
    public IExpression getRight() {
        return addend;
    }
}
