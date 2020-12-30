package ristvan.calculator.semantic.items;

public interface OperatorExpression extends Expression {
    void setLeft(Expression expression);
    void setRight(Expression expression);
    Expression getLeft();
    Expression getRight();
}
