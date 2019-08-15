package eistrej.calculator.semantic.items;

public class MultiplicationExpression implements IOperator{
    private IExpression multiplier;
    private IExpression multicand;

    @Override
    public int evaluate() {
        return multiplier.evaluate() * multicand.evaluate();
    }

    @Override
    public IExpression getLeft() {
        return multiplier;
    }

    @Override
    public void setLeft(IExpression multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public IExpression getRight() {
        return multicand;
    }

    @Override
    public void setRight(IExpression multicand) {
        this.multicand = multicand;
    }
}
