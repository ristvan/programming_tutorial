package ristvan.calculator.semantic;

import ristvan.calculator.semantic.items.*;
import ristvan.calculator.tokenizer.ITokenizer;
import ristvan.calculator.tokenizer.tokens.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

class TokenizerStub implements ITokenizer {
    private List<IToken> tokens;
    private int index = 0;
    public TokenizerStub(List<IToken> userTokens) {
        tokens = userTokens;
    }
    @Override
    public IToken getNextToken() {
        IToken token = null;
        if (index < tokens.size()) {
            token = tokens.get(index);
            index++;
        }
        return token;
    }
}

public class ExpressionCreatorTest {
    @Test
    public void verifyThatTokenizerStubIsWorkingCorrectlyTest() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add(new IAddition() {});
        testTokens.add(null);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        IToken token = tokenizer.getNextToken();
        assertTrue(token instanceof IAddition);
        token = tokenizer.getNextToken();
        assertNull(token);
        token = tokenizer.getNextToken();
        assertNull(token);
    }

    @Test
    public void whenOnlyOneNumberIsGivenAsTokenItShouldResultThatNumber() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 42);

        checkNumber(testTokens, 42);

        testTokens.remove(0);
        testTokens.add((INumber) () -> 28);
        checkNumber(testTokens, 28);

        testTokens.remove(0);
        testTokens.add((INumber) () -> 1983);
        checkNumber(testTokens, 1983);
    }

    private void checkNumber(List<IToken> testTokens, int expectedValue) {
        ITokenizer tokenizer;
        ExpressionCreator ec;
        IExpression expression;
        tokenizer = new TokenizerStub(testTokens);
        ec = new ExpressionCreator(tokenizer);
        expression = ec.createExpression();
        assertTrue("Expression is NOT a number", expression instanceof NumberExpression);
        assertEquals(expectedValue, expression.evaluate());
    }

    @Test
    public void whenTwoNumberIsGivenWithAnAdditionThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 42);
        testTokens.add(new IAddition() {});
        testTokens.add((INumber) () -> 28);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Addition", expression instanceof AdditionExpression);
        AdditionExpression addExpression = (AdditionExpression) expression;
        NumberExpression leftNumber = (NumberExpression) addExpression.getLeft();
        assertEquals(42, leftNumber.evaluate());
        NumberExpression rightNumber = (NumberExpression) addExpression.getRight();
        assertEquals(28, rightNumber.evaluate());
        assertEquals(70, expression.evaluate());
    }

    @Test
    public void whenTwoNumberIsGivenWithASubtractionThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 42);
        testTokens.add(new IMinus() {});
        testTokens.add((INumber) () -> 28);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Subtraction", expression instanceof SubtractionExpression);
        SubtractionExpression subtractionExpression = (SubtractionExpression) expression;
        NumberExpression leftNumber = (NumberExpression) subtractionExpression.getLeft();
        assertEquals(42, leftNumber.evaluate());
        NumberExpression rightNumber = (NumberExpression) subtractionExpression.getRight();
        assertEquals(28, rightNumber.evaluate());
        assertEquals(14, expression.evaluate());
    }

    @Test
    public void whenTwoNumberIsGivenWithAMultiplicationThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 6);
        testTokens.add(new IMultiplication() {});
        testTokens.add((INumber) () -> 7);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT a Multiplication", expression instanceof MultiplicationExpression);
        MultiplicationExpression multiplicationExpression = (MultiplicationExpression) expression;
        NumberExpression multiplier = (NumberExpression) multiplicationExpression.getLeft();
        assertEquals(6, multiplier.evaluate());
        NumberExpression multicand = (NumberExpression) multiplicationExpression.getRight();
        assertEquals(7, multicand.evaluate());
        assertEquals(42, expression.evaluate());
    }

    @Test
    public void whenTwoNumberIsGivenWithADivisionThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 42);
        testTokens.add(new IDivision() {});
        testTokens.add((INumber) () -> 7);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT a Division", expression instanceof DivisionExpression);
        DivisionExpression divisionExpression = (DivisionExpression) expression;
        NumberExpression dividend = (NumberExpression) divisionExpression.getLeft();
        assertEquals(42, dividend.evaluate());
        NumberExpression divisor = (NumberExpression) divisionExpression.getRight();
        assertEquals(7, divisor.evaluate());
        assertEquals(6, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithAdditionOperatorsThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 1);
        testTokens.add(new IAddition() {});
        testTokens.add((INumber) () -> 2);
        testTokens.add(new IAddition() {});
        testTokens.add((INumber) () -> 3);
        testTokens.add(new IAddition() {});
        testTokens.add((INumber) () -> 4);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Addition", expression instanceof AdditionExpression);

        AdditionExpression addExpression1 = (AdditionExpression) expression;
        IExpression augend1 = addExpression1.getLeft();
        IExpression addend1 = addExpression1.getRight();
        assertTrue("First augend is NOT an Addition", augend1 instanceof AdditionExpression);
        assertTrue("First addend is NOT a Number", addend1 instanceof NumberExpression);
        assertEquals(6, augend1.evaluate());
        assertEquals(4, addend1.evaluate());

        AdditionExpression addExpression2 = (AdditionExpression) augend1;
        IExpression augend2 = addExpression2.getLeft();
        IExpression addend2 = addExpression2.getRight();
        assertTrue("Second augend is NOT an Addition", augend2 instanceof AdditionExpression);
        assertTrue("Second addend is NOT a Number", addend2 instanceof NumberExpression);
        assertEquals(3, augend2.evaluate());
        assertEquals(3, addend2.evaluate());

        AdditionExpression addExpression3 = (AdditionExpression) augend2;
        IExpression augend3 = addExpression3.getLeft();
        IExpression addend3 = addExpression3.getRight();
        assertTrue("Third augend is NOT a Number", augend3 instanceof NumberExpression);
        assertTrue("Third addend is NOT a Number", addend3 instanceof NumberExpression);
        assertEquals(1, augend3.evaluate());
        assertEquals(2, addend3.evaluate());

        assertEquals(10, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithSubtractionOperatorsThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 100);
        testTokens.add(new IMinus() {});
        testTokens.add((INumber) () -> 50);
        testTokens.add(new IMinus() {});
        testTokens.add((INumber) () -> 30);
        testTokens.add(new IMinus() {});
        testTokens.add((INumber) () -> 10);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Subtraction", expression instanceof SubtractionExpression);

        SubtractionExpression subtractionExpression1 = (SubtractionExpression) expression;
        IExpression minuend1 = subtractionExpression1.getLeft();
        IExpression subtrahend1 = subtractionExpression1.getRight();
        assertTrue("First augend is NOT an Subtraction", minuend1 instanceof SubtractionExpression);
        assertTrue("First addend is NOT a Number", subtrahend1 instanceof NumberExpression);
        assertEquals(20, minuend1.evaluate());
        assertEquals(10, subtrahend1.evaluate());

        SubtractionExpression subtractionExpression2 = (SubtractionExpression) minuend1;
        IExpression minuend2 = subtractionExpression2.getLeft();
        IExpression subtrahend2 = subtractionExpression2.getRight();
        assertTrue("Second augend is NOT an Subtraction", minuend2 instanceof SubtractionExpression);
        assertTrue("Second addend is NOT a Number", subtrahend2 instanceof NumberExpression);
        assertEquals(50, minuend2.evaluate());
        assertEquals(30, subtrahend2.evaluate());

        SubtractionExpression subtractionExpression3 = (SubtractionExpression) minuend2;
        IExpression minuend3 = subtractionExpression3.getLeft();
        IExpression subtrahend3 = subtractionExpression3.getRight();
        assertTrue("Third augend is NOT a Number", minuend3 instanceof NumberExpression);
        assertTrue("Third addend is NOT a Number", subtrahend3 instanceof NumberExpression);
        assertEquals(100, minuend3.evaluate());
        assertEquals(50, subtrahend3.evaluate());

        assertEquals(10, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithMultiplicationOperatorsThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 1);
        testTokens.add(new IMultiplication() {});
        testTokens.add((INumber) () -> 2);
        testTokens.add(new IMultiplication() {});
        testTokens.add((INumber) () -> 3);
        testTokens.add(new IMultiplication() {});
        testTokens.add((INumber) () -> 4);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Multiplication", expression instanceof MultiplicationExpression);

        MultiplicationExpression multiplicationExpression1 = (MultiplicationExpression) expression;
        IExpression multiplier1 = multiplicationExpression1.getLeft();
        IExpression multicand1 = multiplicationExpression1.getRight();
        assertTrue("First augend is NOT an Multiplication", multiplier1 instanceof MultiplicationExpression);
        assertTrue("First addend is NOT a Number", multicand1 instanceof NumberExpression);
        assertEquals(6, multiplier1.evaluate());
        assertEquals(4, multicand1.evaluate());

        MultiplicationExpression multiplicationExpression2 = (MultiplicationExpression) multiplier1;
        IExpression multiplier2 = multiplicationExpression2.getLeft();
        IExpression multicand2 = multiplicationExpression2.getRight();
        assertTrue("Second augend is NOT an Multiplication", multiplier2 instanceof MultiplicationExpression);
        assertTrue("Second addend is NOT a Number", multicand2 instanceof NumberExpression);
        assertEquals(2, multiplier2.evaluate());
        assertEquals(3, multicand2.evaluate());

        MultiplicationExpression multiplicationExpression3 = (MultiplicationExpression) multiplier2;
        IExpression multiplier3 = multiplicationExpression3.getLeft();
        IExpression multicand3 = multiplicationExpression3.getRight();
        assertTrue("Third augend is NOT a Number", multiplier3 instanceof NumberExpression);
        assertTrue("Third addend is NOT a Number", multicand3 instanceof NumberExpression);
        assertEquals(1, multiplier3.evaluate());
        assertEquals(2, multicand3.evaluate());

        assertEquals(24, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithDivisionOperatorsThatShouldBeResultedAsAHierarchy() {
        List<IToken> testTokens = new LinkedList<>();
        testTokens.add((INumber) () -> 72);
        testTokens.add(new IDivision() {});
        testTokens.add((INumber) () -> 4);
        testTokens.add(new IDivision() {});
        testTokens.add((INumber) () -> 3);
        testTokens.add(new IDivision() {});
        testTokens.add((INumber) () -> 2);
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Division", expression instanceof DivisionExpression);

        DivisionExpression divisionExpression1 = (DivisionExpression) expression;
        IExpression dividend1 = divisionExpression1.getLeft();
        IExpression divisor1 = divisionExpression1.getRight();
        assertTrue("First dividend is NOT an Division", dividend1 instanceof DivisionExpression);
        assertTrue("First divisor is NOT a Number", divisor1 instanceof NumberExpression);
        assertEquals(6, dividend1.evaluate());
        assertEquals(2, divisor1.evaluate());

        DivisionExpression subtractionExpression2 = (DivisionExpression) dividend1;
        IExpression dividend2 = subtractionExpression2.getLeft();
        IExpression divisor2 = subtractionExpression2.getRight();
        assertTrue("Second dividend is NOT an Division", dividend2 instanceof DivisionExpression);
        assertTrue("Second divisor is NOT a Number", divisor2 instanceof NumberExpression);
        assertEquals(18, dividend2.evaluate());
        assertEquals(3, divisor2.evaluate());

        DivisionExpression subtractionExpression3 = (DivisionExpression) dividend2;
        IExpression dividend3 = subtractionExpression3.getLeft();
        IExpression divisor3 = subtractionExpression3.getRight();
        assertTrue("Third dividend is NOT a Number", dividend3 instanceof NumberExpression);
        assertTrue("Third divisor is NOT a Number", divisor3 instanceof NumberExpression);
        assertEquals(72, dividend3.evaluate());
        assertEquals(4, divisor3.evaluate());

        assertEquals(3, expression.evaluate());
    }
}
