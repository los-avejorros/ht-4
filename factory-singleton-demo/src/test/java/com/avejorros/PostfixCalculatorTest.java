package com.avejorros;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.avejorros.controller.PostfixCalculator;
import com.avejorros.interfaces.IPostfixCalculator;

public class PostfixCalculatorTest {
  @Test
  public void testEvaluate() {
    IPostfixCalculator calculator = new PostfixCalculator();
    assertEquals(15, calculator.evaluate("1 2 + 4 * 3 +"));
    assertEquals(30, calculator.evaluate("6 2 3 + *"));
  }

  @Test(expected = ArithmeticException.class)
  public void testDivisionByZero() {
    IPostfixCalculator calculator = new PostfixCalculator();
    calculator.evaluate("1 0 /");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOperator() {
    IPostfixCalculator calculator = new PostfixCalculator();
    calculator.evaluate("1 2 &");
  }
}
