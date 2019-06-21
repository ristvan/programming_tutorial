package eistrej.calculator.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import eistrej.calculator.tokenizer.tokens.IAddition;
import eistrej.calculator.tokenizer.tokens.IMinus;
import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;
import org.junit.Test;

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
        Tokenizer tokenizer = new Tokenizer("-");
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);

        tokenizer = new Tokenizer(" -");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);

        tokenizer = new Tokenizer("- ");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);

        tokenizer = new Tokenizer(" - ");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof IMinus);

        tokenizer = new Tokenizer(" \t- \t");
        nextToken = tokenizer.getNextToken();
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