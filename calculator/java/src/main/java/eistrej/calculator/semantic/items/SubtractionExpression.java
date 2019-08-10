package eistrej.calculator.semantic.items;

public class SubtractionExpression implements IExpression {
    private IExpression minuend;
    private IExpression subtrahend;

    @Override
    public int evaluate() {
        return minuend.evaluate() - subtrahend.evaluate();
    }

    public void setMinuend(IExpression minuend) {
        this.minuend = minuend;
    }

    public void setSubtrahend(IExpression subtrahend) {
        this.subtrahend = subtrahend;
    }

    public IExpression getMinuend() {
        return minuend;
    }

    public IExpression getSubtrahend() {
        return subtrahend;
    }
}
