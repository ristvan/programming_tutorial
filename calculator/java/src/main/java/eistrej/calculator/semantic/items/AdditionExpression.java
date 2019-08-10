package eistrej.calculator.semantic.items;

public class AdditionExpression implements IExpression {
    private IExpression augend;
    private IExpression addend;
    @Override
    public int evaluate() {
        return augend.evaluate() + addend.evaluate();
    }

    public void setAugend(IExpression expression) {
        augend = expression;
    }

    public void setAddend(IExpression expression) {
        addend = expression;
    }

    public IExpression getAugend() {
        return augend;
    }

    public IExpression getAddend() {
        return addend;
    }
}
