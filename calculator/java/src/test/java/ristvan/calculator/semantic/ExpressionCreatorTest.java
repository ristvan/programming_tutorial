package ristvan.calculator.semantic;

import ristvan.calculator.semantic.items.*;
import ristvan.calculator.tokenizer.Tokenizer;
import ristvan.calculator.tokenizer.tokens.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import ristvan.calculator.tokenizer.tokens.Number;

import static org.junit.Assert.*;

class TokenizerStub implements Tokenizer {
    private List<Token> tokens;
    private int index = 0;
    public TokenizerStub(List<Token> userTokens) {
        tokens = userTokens;
    }
    @Override
    public Token getNextToken() {
        Token token = null;
        if (index < tokens.size()) {
            token = tokens.get(index);
            index++;
        }
        return token;
    }
}

public class ExpressionCreatorTest {

    public static final String FIRST_ADDEND_IS_NOT_A_NUMBER = "First addend is NOT a Number";
    public static final String SECOND_ADDEND_IS_NOT_A_NUMBER = "Second addend is NOT a Number";
    public static final String THIRD_AUGEND_IS_NOT_A_NUMBER = "Third augend is NOT a Number";
    public static final String THIRD_ADDEND_IS_NOT_A_NUMBER = "Third addend is NOT a Number";

    @Test
    public void verifyThatTokenizerStubIsWorkingCorrectlyTest() {
        List<Token> testTokens = new LinkedList<>();
        testTokens.add(new Addition() {});
        testTokens.add(null);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        Token token = tokenizer.getNextToken();
        assertTrue(token instanceof Addition);
        token = tokenizer.getNextToken();
        assertNull(token);
        token = tokenizer.getNextToken();
        assertNull(token);
    }

    @Test
    public void whenOnlyOneNumberIsGivenAsTokenItShouldResultThatNumber() {
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 42);

        checkNumber(testTokens, 42);

        testTokens.remove(0);
        testTokens.add((Number) () -> 28);
        checkNumber(testTokens, 28);

        testTokens.remove(0);
        testTokens.add((Number) () -> 1983);
        checkNumber(testTokens, 1983);
    }

    private void checkNumber(List<Token> testTokens, int expectedValue) {
        Tokenizer tokenizer;
        ExpressionCreator ec;
        Expression expression;
        tokenizer = new TokenizerStub(testTokens);
        ec = new ExpressionCreator(tokenizer);
        expression = ec.createExpression();
        assertTrue("Expression is NOT a number", expression instanceof NumberExpression);
        assertEquals(expectedValue, expression.evaluate());
    }

    @Test
    public void whenTwoNumberIsGivenWithAnAdditionThatShouldBeResultedAsAHierarchy() {
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 42);
        testTokens.add(new Addition() {});
        testTokens.add((Number) () -> 28);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        Expression expression = ec.createExpression();
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
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 42);
        testTokens.add(new Minus() {});
        testTokens.add((Number) () -> 28);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        Expression expression = ec.createExpression();
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
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 6);
        testTokens.add(new Multiplication() {});
        testTokens.add((Number) () -> 7);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        Expression expression = ec.createExpression();
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
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 42);
        testTokens.add(new Division() {});
        testTokens.add((Number) () -> 7);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        Expression expression = ec.createExpression();
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
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 1);
        testTokens.add(new Addition() {});
        testTokens.add((Number) () -> 2);
        testTokens.add(new Addition() {});
        testTokens.add((Number) () -> 3);
        testTokens.add(new Addition() {});
        testTokens.add((Number) () -> 4);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        Expression expression = ec.createExpression();
        assertTrue("Expression is NOT an Addition", expression instanceof AdditionExpression);

        AdditionExpression addExpression1 = (AdditionExpression) expression;
        Expression augend1 = addExpression1.getLeft();
        Expression addend1 = addExpression1.getRight();
        assertTrue("First augend is NOT an Addition", augend1 instanceof AdditionExpression);
        assertTrue(FIRST_ADDEND_IS_NOT_A_NUMBER, addend1 instanceof NumberExpression);
        assertEquals(6, augend1.evaluate());
        assertEquals(4, addend1.evaluate());

        AdditionExpression addExpression2 = (AdditionExpression) augend1;
        Expression augend2 = addExpression2.getLeft();
        Expression addend2 = addExpression2.getRight();
        assertTrue("Second augend is NOT an Addition", augend2 instanceof AdditionExpression);
        assertTrue(SECOND_ADDEND_IS_NOT_A_NUMBER, addend2 instanceof NumberExpression);
        assertEquals(3, augend2.evaluate());
        assertEquals(3, addend2.evaluate());

        AdditionExpression addExpression3 = (AdditionExpression) augend2;
        Expression augend3 = addExpression3.getLeft();
        Expression addend3 = addExpression3.getRight();
        assertTrue(THIRD_AUGEND_IS_NOT_A_NUMBER, augend3 instanceof NumberExpression);
        assertTrue(THIRD_ADDEND_IS_NOT_A_NUMBER, addend3 instanceof NumberExpression);
        assertEquals(1, augend3.evaluate());
        assertEquals(2, addend3.evaluate());

        assertEquals(10, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithSubtractionOperatorsThatShouldBeResultedAsAHierarchy() {
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 100);
        testTokens.add(new Minus() {});
        testTokens.add((Number) () -> 50);
        testTokens.add(new Minus() {});
        testTokens.add((Number) () -> 30);
        testTokens.add(new Minus() {});
        testTokens.add((Number) () -> 10);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        Expression expression = ec.createExpression();
        assertTrue("Expression is NOT an Subtraction", expression instanceof SubtractionExpression);

        SubtractionExpression subtractionExpression1 = (SubtractionExpression) expression;
        Expression minuend1 = subtractionExpression1.getLeft();
        Expression subtrahend1 = subtractionExpression1.getRight();
        assertTrue("First augend is NOT an Subtraction", minuend1 instanceof SubtractionExpression);
        assertTrue(FIRST_ADDEND_IS_NOT_A_NUMBER, subtrahend1 instanceof NumberExpression);
        assertEquals(20, minuend1.evaluate());
        assertEquals(10, subtrahend1.evaluate());

        SubtractionExpression subtractionExpression2 = (SubtractionExpression) minuend1;
        Expression minuend2 = subtractionExpression2.getLeft();
        Expression subtrahend2 = subtractionExpression2.getRight();
        assertTrue("Second augend is NOT an Subtraction", minuend2 instanceof SubtractionExpression);
        assertTrue(SECOND_ADDEND_IS_NOT_A_NUMBER, subtrahend2 instanceof NumberExpression);
        assertEquals(50, minuend2.evaluate());
        assertEquals(30, subtrahend2.evaluate());

        SubtractionExpression subtractionExpression3 = (SubtractionExpression) minuend2;
        Expression minuend3 = subtractionExpression3.getLeft();
        Expression subtrahend3 = subtractionExpression3.getRight();
        assertTrue(THIRD_AUGEND_IS_NOT_A_NUMBER, minuend3 instanceof NumberExpression);
        assertTrue(THIRD_ADDEND_IS_NOT_A_NUMBER, subtrahend3 instanceof NumberExpression);
        assertEquals(100, minuend3.evaluate());
        assertEquals(50, subtrahend3.evaluate());

        assertEquals(10, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithMultiplicationOperatorsThatShouldBeResultedAsAHierarchy() {
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 1);
        testTokens.add(new Multiplication() {});
        testTokens.add((Number) () -> 2);
        testTokens.add(new Multiplication() {});
        testTokens.add((Number) () -> 3);
        testTokens.add(new Multiplication() {});
        testTokens.add((Number) () -> 4);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        Expression expression = ec.createExpression();
        assertTrue("Expression is NOT an Multiplication", expression instanceof MultiplicationExpression);

        MultiplicationExpression multiplicationExpression1 = (MultiplicationExpression) expression;
        Expression multiplier1 = multiplicationExpression1.getLeft();
        Expression multicand1 = multiplicationExpression1.getRight();
        assertTrue("First augend is NOT an Multiplication", multiplier1 instanceof MultiplicationExpression);
        assertTrue(FIRST_ADDEND_IS_NOT_A_NUMBER, multicand1 instanceof NumberExpression);
        assertEquals(6, multiplier1.evaluate());
        assertEquals(4, multicand1.evaluate());

        MultiplicationExpression multiplicationExpression2 = (MultiplicationExpression) multiplier1;
        Expression multiplier2 = multiplicationExpression2.getLeft();
        Expression multicand2 = multiplicationExpression2.getRight();
        assertTrue("Second augend is NOT an Multiplication", multiplier2 instanceof MultiplicationExpression);
        assertTrue(SECOND_ADDEND_IS_NOT_A_NUMBER, multicand2 instanceof NumberExpression);
        assertEquals(2, multiplier2.evaluate());
        assertEquals(3, multicand2.evaluate());

        MultiplicationExpression multiplicationExpression3 = (MultiplicationExpression) multiplier2;
        Expression multiplier3 = multiplicationExpression3.getLeft();
        Expression multicand3 = multiplicationExpression3.getRight();
        assertTrue(THIRD_AUGEND_IS_NOT_A_NUMBER, multiplier3 instanceof NumberExpression);
        assertTrue(THIRD_ADDEND_IS_NOT_A_NUMBER, multicand3 instanceof NumberExpression);
        assertEquals(1, multiplier3.evaluate());
        assertEquals(2, multicand3.evaluate());

        assertEquals(24, expression.evaluate());
    }

    @Test
    public void whenMoreNumbersAreGivenWithDivisionOperatorsThatShouldBeResultedAsAHierarchy() {
        List<Token> testTokens = new LinkedList<>();
        testTokens.add((Number) () -> 72);
        testTokens.add(new Division() {});
        testTokens.add((Number) () -> 4);
        testTokens.add(new Division() {});
        testTokens.add((Number) () -> 3);
        testTokens.add(new Division() {});
        testTokens.add((Number) () -> 2);
        Tokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);

        Expression expression = ec.createExpression();
        assertTrue("Expression is NOT an Division", expression instanceof DivisionExpression);

        DivisionExpression divisionExpression1 = (DivisionExpression) expression;
        Expression dividend1 = divisionExpression1.getLeft();
        Expression divisor1 = divisionExpression1.getRight();
        assertTrue("First dividend is NOT an Division", dividend1 instanceof DivisionExpression);
        assertTrue("First divisor is NOT a Number", divisor1 instanceof NumberExpression);
        assertEquals(6, dividend1.evaluate());
        assertEquals(2, divisor1.evaluate());

        DivisionExpression subtractionExpression2 = (DivisionExpression) dividend1;
        Expression dividend2 = subtractionExpression2.getLeft();
        Expression divisor2 = subtractionExpression2.getRight();
        assertTrue("Second dividend is NOT an Division", dividend2 instanceof DivisionExpression);
        assertTrue("Second divisor is NOT a Number", divisor2 instanceof NumberExpression);
        assertEquals(18, dividend2.evaluate());
        assertEquals(3, divisor2.evaluate());

        DivisionExpression subtractionExpression3 = (DivisionExpression) dividend2;
        Expression dividend3 = subtractionExpression3.getLeft();
        Expression divisor3 = subtractionExpression3.getRight();
        assertTrue("Third dividend is NOT a Number", dividend3 instanceof NumberExpression);
        assertTrue("Third divisor is NOT a Number", divisor3 instanceof NumberExpression);
        assertEquals(72, dividend3.evaluate());
        assertEquals(4, divisor3.evaluate());

        assertEquals(3, expression.evaluate());
    }
}
