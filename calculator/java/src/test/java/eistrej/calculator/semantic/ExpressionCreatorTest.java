package eistrej.calculator.semantic;

import eistrej.calculator.semantic.items.AdditionExpression;
import eistrej.calculator.semantic.items.IExpression;
import eistrej.calculator.semantic.items.NumberExpression;
import eistrej.calculator.tokenizer.ITokenizer;
import eistrej.calculator.tokenizer.tokens.IAddition;
import eistrej.calculator.tokenizer.tokens.INumber;
import eistrej.calculator.tokenizer.tokens.IToken;
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
    public void firstTest() {
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
        testTokens.add(new INumber() {
            @Override
            public int getValue() {
                return 42;
            }
        });

        checkNumber(testTokens, 42);

        testTokens.remove(0);
        testTokens.add(new INumber() {
            @Override
            public int getValue() {
                return 28;
            }
        });
        checkNumber(testTokens, 28);

        testTokens.remove(0);
        testTokens.add(new INumber() {
            @Override
            public int getValue() {
                return 1983;
            }
        });
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
        testTokens.add(new INumber() {
            @Override
            public int getValue() {
                return 42;
            }
        });
        testTokens.add(new IAddition() {});
        testTokens.add(new INumber() {
            @Override
            public int getValue() {
                return 28;
            }
        });
        ITokenizer tokenizer = new TokenizerStub(testTokens);
        ExpressionCreator ec = new ExpressionCreator(tokenizer);
        IExpression expression = ec.createExpression();
        assertTrue("Expression is NOT an Addition", expression instanceof AdditionExpression);
        assertEquals(70, expression.evaluate());
    }
}
