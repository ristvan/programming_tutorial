package eistrej.calculator.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;
import org.junit.Test;

public class TokenizerTest {
    @Test
    public void testNumbers() {
        Tokenizer tokenizer = new Tokenizer("42");
        IToken nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        INumber number = (INumber) nextToken;
        assertEquals(42, number.getValue());

        tokenizer = new Tokenizer("83");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        number = (INumber) nextToken;
        assertEquals(83, number.getValue());

        tokenizer = new Tokenizer("2019");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        number = (INumber) nextToken;
        assertEquals(2019, number.getValue());

        tokenizer = new Tokenizer("1983 ");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        number = (INumber) nextToken;
        assertEquals(1983, number.getValue());

        tokenizer = new Tokenizer(" 1983");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        number = (INumber) nextToken;
        assertEquals(1983, number.getValue());

        tokenizer = new Tokenizer("\t 1983");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        number = (INumber) nextToken;
        assertEquals(1983, number.getValue());

        tokenizer = new Tokenizer(" 1983\t ");
        nextToken = tokenizer.getNextToken();
        assertTrue(nextToken instanceof INumber);
        number = (INumber) nextToken;
        assertEquals(1983, number.getValue());
    }
}
