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
        assertEquals(2019, evaluator.evaluate("1983\t+ 36"));
        assertEquals(2019, evaluator.evaluate("1983 + 36"));
    }

    @Test
    public void subtractionOfTwoNumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(4, evaluator.evaluate("6-2"));
        assertEquals(28, evaluator.evaluate("34-6"));
        assertEquals(1945, evaluator.evaluate("2019-74"));
        assertEquals(9900, evaluator.evaluate("9999\t - 99"));
    }

    @Test
    public void multiplicationOfTwoNumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(4, evaluator.evaluate("2*2"));
        assertEquals(4096, evaluator.evaluate("512*8"));
        assertEquals(42, evaluator.evaluate("6 * 7"));
        assertEquals(64, evaluator.evaluate("8\t * \t8\t"));
    }

    @Test
    public void divisionOfTwoNumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(2, evaluator.evaluate("4/2"));
        assertEquals(25, evaluator.evaluate("125/ 5"));
        assertEquals(100, evaluator.evaluate("10000 /100"));
        assertEquals(3, evaluator.evaluate(" 7\t/ 2\t"));
        assertEquals(123, evaluator.evaluate("246 / 2"));
    }

    @Test
    public void additionOfMoreThen2NumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(6, evaluator.evaluate("1+2+3"));
        assertEquals(10, evaluator.evaluate("1+2+3 + 4"));
        assertEquals(15, evaluator.evaluate("1+2+3 + 4\t+\t5" ));
        assertEquals(21, evaluator.evaluate("1+2+3 + 4\t+\t5 +\t6\t"));
    }

    @Test
    public void subtractionOfMoreThen2NumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(6, evaluator.evaluate("12-2-4"));
        assertEquals(10, evaluator.evaluate("122-22-90"));
        assertEquals(15, evaluator.evaluate("1024 - 512-256\t-128\t-\t64-32-17" ));
        assertEquals(1800, evaluator.evaluate("\t10000\t-\t8196 -\t4\t"));
    }

    @Test
    public void multiplicationOfMoreThen2NumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(6, evaluator.evaluate("1*2*3"));
        assertEquals(40, evaluator.evaluate("2*4*5"));
        assertEquals(128, evaluator.evaluate(" 2* 2 * 2\t* 2 *\t2* 2\t*\t2"));
        assertEquals(500, evaluator.evaluate("\t5 * 2 * 5 * 2* 5"));
    }

    @Test
    public void divisionOfMoreThen2NumbersShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(2, evaluator.evaluate("4/2/1"));
        assertEquals(5, evaluator.evaluate("125/ 5/5"));
        assertEquals(33, evaluator.evaluate("10000 /100 / 3"));
        assertEquals(3, evaluator.evaluate(" 154 / 7 / 3\t/ 2\t"));
        assertEquals(91, evaluator.evaluate("510510 / 17 / 2 / 5 / 11 / 3"));
    }
}
