package ristvan.calculator.tokenizer;

import org.junit.Test;
import ristvan.calculator.tokenizer.tokens.*;
import ristvan.calculator.tokenizer.tokens.Number;

import static org.junit.Assert.*;

public class TokenizerImplTest {
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
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(28, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingANumberAndAMultiplicationShouldBeResultedCorrectly() {
        String expression="28*";
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(28, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Multiplication);

        nextToken = tokenizerImpl.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingLongExpressionShouldBeResultedCorrectly() {
        String expression="28+12*42-23+64/8";
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(28, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(12, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Multiplication);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(42, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Minus);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(23, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(64, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Division);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(8, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingLongExpressionWithWhitespacesShouldBeResultedCorrectly() {
        String expression=" 28 + 12\t*\t42 \t-\t 23\t\t+  64 /\t8\t";
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(28, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(12, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Multiplication);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(42, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Minus);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(23, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(64, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Division);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(8, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertNull(nextToken);
    }

    @Test
    public void tokenizingLongExpressionWithVariousLengthNumbersShouldBeResultedCorrectly() {
        String expression=" 281234+12*4289-2388+6/4568";
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(281234, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(12, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Multiplication);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(4289, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Minus);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(2388, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(6, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Division);

        nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        assertEquals(4568, ((Number)nextToken).getValue());

        nextToken = tokenizerImpl.getNextToken();
        assertNull(nextToken);
    }

    private void checkTokenIsDivision(String expression) {
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Division);
    }

    private void checkTokenIsMultiplication(String expression) {
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Multiplication);
    }

    private void checkTokenIsSubtraction(String expression) {
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Minus);
    }

    private void checkNumberTokenization(String expression, int expected) {
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Number);
        Number number = (Number) nextToken;
        assertEquals(expected, number.getValue());
    }

    private void checkTokenIsAddition(String expression) {
        TokenizerImpl tokenizerImpl = new TokenizerImpl(expression);
        Token nextToken = tokenizerImpl.getNextToken();
        assertTrue(nextToken instanceof Addition);
    }
}
