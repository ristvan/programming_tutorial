package eistrej.calculator.tokenizer;

import eistrej.calculator.tokenizer.tokens.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenizerTest {
    @Test
    public void tokenizingNumbersShouldResultANumberTokenWithCorrectValue() {
        checkNumberTokenization("42", 42);
        checkNumberTokenization("83", 83);
        checkNumberTokenization("2019", 2019);
        checkNumberTokenization("1983 ",1983);
        checkNumberTokenization(" 1983",1983);
        checkNumberTokenization("\t 1983",1983);
        checkNumberTokenization(" 1983\t ",1983);
    }

    @Test
    public void tokenizingAdditionShouldResultAdditionToken() {
        checkTokenIsAddition("+");
        checkTokenIsAddition(" +");
        checkTokenIsAddition("+ ");
        checkTokenIsAddition(" + ");
        checkTokenIsAddition(" \t+ \t");
    }

    @Test
    public void tokenizingSubtractionShouldResultMinusToken() {
        checkTokenIsSubtraction("-");
        checkTokenIsSubtraction(" -");
        checkTokenIsSubtraction("- ");
        checkTokenIsSubtraction(" - ");
        checkTokenIsSubtraction(" \t- \t");
    }

    @Test
    public void tokenizingMultiplicationShouldResultMinusToken() {
        checkTokenIsMultiplication("*");
        checkTokenIsMultiplication(" *");
        checkTokenIsMultiplication("* ");
        checkTokenIsMultiplication(" * ");
        checkTokenIsMultiplication(" \t* \t");
    }

    @Test
    public void tokenizingDivisionShouldResultMinusToken() {
        checkTokenIsDivision("/");
        checkTokenIsDivision(" /");
        checkTokenIsDivision("/ ");
        checkTokenIsDivision(" / ");
        checkTokenIsDivision(" \t/ \t");
    }

    @Test
    public void tokenizingTwoTokensShouldBeResultedCorrectly() {
        String expression="28+";
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(28, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IAddition);

        nextToken = tokenizer.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingANumberAndAMultiplicationShouldBeResultedCorrectly() {
        String expression="28*";
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(28, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMultiplication);

        nextToken = tokenizer.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingLongExpressionShouldBeResultedCorrectly() {
        String expression="28+12*42-23+64/8";
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(28, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IAddition);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(12, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMultiplication);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(42, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(23, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IAddition);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(64, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IDivision);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(8, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingLongExpressionWithWhitespacesShouldBeResultedCorrectly() {
        String expression=" 28 + 12\t*\t42 \t-\t 23\t\t+  64 /\t8\t";
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(28, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IAddition);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(12, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMultiplication);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(42, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(23, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IAddition);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(64, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IDivision);

        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        assertEquals(8, ((INumber)nextToken).getValue());

        nextToken = tokenizer.getNextToken();
        assertNull(nextToken);
    }


    private void checkTokenIsDivision(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IDivision);
    }

    private void checkTokenIsMultiplication(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMultiplication);
    }

    private void checkTokenIsSubtraction(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);
    }

    private void checkNumberTokenization(String expression, int expected) {
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        INumber number = (INumber) nextToken;
        assertEquals(expected, number.getValue());
    }

    private void checkTokenIsAddition(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IAddition);
    }
}
