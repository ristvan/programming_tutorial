package eistrej.calculator.semantic.items;

public class SubtractionExpression implements IOperator {
    private IExpression minuend;
    private IExpression subtrahend;

    @Override
    public int evaluate() {
        return minuend.evaluate() - subtrahend.evaluate();
    }

    @Override
    public void setLeft(IExpression minuend) {
        this.minuend = minuend;
    }

    @Override
    public void setRight(IExpression subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public IExpression getLeft() {
        return minuend;
    }

    @Override
    public IExpression getRight() {
        return subtrahend;
    }
}
