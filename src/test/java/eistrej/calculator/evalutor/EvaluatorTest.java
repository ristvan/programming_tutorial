package eistrej.calculator.evalutor;

import static org.junit.Assert.assertEquals;

import eistrej.calculator.evaluator.Evaluator;
import org.junit.Test;


public class EvaluatorTest {
    @Test
    public void numberShouldBeEvaluatedCorrectly() {
        Evaluator evaluator = new Evaluator();
        assertEquals(28, evaluator.evaluate("28"));
        assertEquals(6, evaluator.evaluate("6"));
        assertEquals(1983, evaluator.evaluate("1983"));
    }
}
