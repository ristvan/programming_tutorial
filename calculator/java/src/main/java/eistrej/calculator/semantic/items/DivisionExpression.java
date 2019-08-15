package eistrej.calculator.semantic.items;

public class DivisionExpression implements IOperator {
    private IExpression dividend;
    private IExpression divisor;

    @Override
    public int evaluate() {
        return dividend.evaluate() / divisor.evaluate();
    }

    @Override
    public IExpression getLeft() {
        return dividend;
    }

    @Override
    public IExpression getRight() {
        return divisor;
    }

    @Override
    public void setLeft(IExpression dividend) {
        this.dividend = dividend;
    }

    @Override
    public void setRight(IExpression divisor) {
        this.divisor = divisor;
    }
}
