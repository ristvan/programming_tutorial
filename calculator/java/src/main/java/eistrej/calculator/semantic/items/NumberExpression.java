package eistrej.calculator.semantic.items;

public class NumberExpression implements IExpression {
    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}
