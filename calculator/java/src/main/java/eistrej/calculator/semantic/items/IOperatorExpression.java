package eistrej.calculator.semantic.items;

public interface IOperatorExpression extends IExpression {
    void setLeft(IExpression expression);
    void setRight(IExpression expression);
    IExpression getLeft();
    IExpression getRight();
}
