package eistrej.calculator.semantic.items;

public class AdditionExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    @Override
    public int evaluate() {
        return left.evaluate() + right.evaluate();
    }

    public void addLeft(IExpression expression) {
        left = expression;
    }

    public void addRight(IExpression expression) {
        right = expression;
    }
}
