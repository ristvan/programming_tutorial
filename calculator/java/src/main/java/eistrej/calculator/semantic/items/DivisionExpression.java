package eistrej.calculator.semantic.items;

public class DivisionExpression implements IExpression {
    private IExpression dividend;
    private IExpression divisor;

    @Override
    public int evaluate() {
        return dividend.evaluate() / divisor.evaluate();
    }

    public IExpression getDividend() {
        return dividend;
    }

    public IExpression getDivisor() {
        return divisor;
    }

    public void setDividend(IExpression dividend) {
        this.dividend = dividend;
    }

    public void setDivisor(IExpression divisor) {
        this.divisor = divisor;
    }
}
