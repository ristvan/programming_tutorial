package eistrej.calculator.semantic.items;

public class MultiplicationExpression implements IExpression{
    private IExpression multiplier;
    private IExpression multicand;

    @Override
    public int evaluate() {
        return multiplier.evaluate() * multicand.evaluate();
    }

    public IExpression getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(IExpression multiplier) {
        this.multiplier = multiplier;
    }

    public IExpression getMulticand() {
        return multicand;
    }

    public void setMulticand(IExpression multicand) {
        this.multicand = multicand;
    }
}
