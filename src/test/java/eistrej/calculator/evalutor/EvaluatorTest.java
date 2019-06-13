package eistrej.calculator.evalutor;

import static org.junit.Assert.assertEquals;

import eistrej.calculator.evaluator.Evaluator;
import org.junit.Test;


public class EvaluatorTest {
    @Test
    public void positiveNumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(28, evaluator.evaluate("28"));
        assertEquals(6, evaluator.evaluate("6"));
        assertEquals(1983, evaluator.evaluate("1983"));
    }

    @Test
    public void positiveNumberWithLeadingWhitespacesShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(896, evaluator.evaluate(" 896"));
        assertEquals(896, evaluator.evaluate("      896"));
        assertEquals(896, evaluator.evaluate("\t 896"));
        assertEquals(896, evaluator.evaluate("\t \t896"));
    }

    @Test
    public void positiveNumberWithTrailingWhitespacesShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(896, evaluator.evaluate("896   "));
        assertEquals(896, evaluator.evaluate("896 "));
        assertEquals(896, evaluator.evaluate("896\t"));
        assertEquals(896, evaluator.evaluate("896\t \t"));
    }

    @Test
    public void additionOfTwoNumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(4, evaluator.evaluate("2+2"));
        assertEquals(34, evaluator.evaluate("6+28"));
        assertEquals(2019, evaluator.evaluate("1983+36"));
    }
}
