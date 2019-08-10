package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.*;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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
        NumberExpression leftNumber = (NumberExpression) addExpression.getAugend();
        assertEquals(42, leftNumber.evaluate());
        NumberExpression rightNumber = (NumberExpression) addExpression.getAddend();
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
        SubtractionExpression addExpression = (SubtractionExpression) expression;
        NumberExpression leftNumber = (NumberExpression) addExpression.getMinuend();
        assertEquals(42, leftNumber.evaluate());
        NumberExpression rightNumber = (NumberExpression) addExpression.getSubtrahend();
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
        assertTrue("Expression is NOT an Multiplication", expression instanceof MultiplicationExpression);
        MultiplicationExpression addExpression = (MultiplicationExpression) expression;
        NumberExpression multiplier = (NumberExpression) addExpression.getMultiplier();
        assertEquals(6, multiplier.evaluate());
        NumberExpression multicand = (NumberExpression) addExpression.getMulticand();
        assertEquals(7, multicand.evaluate());
        assertEquals(42, expression.evaluate());
    }
}
