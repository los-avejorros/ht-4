package com.avejorros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

  @Test
  public void testDivisionByZeroWithMessage() {
    IPostfixCalculator calculator = new PostfixCalculator();
    try {
      calculator.evaluate("1 0 /");
      fail("Se esperaba una ArithmeticException");
    } catch (ArithmeticException e) {
      assertEquals("Division by zero", e.getMessage());
    }
  }

  @Test
  public void testInvalidOperatorWithMessage() {
    IPostfixCalculator calculator = new PostfixCalculator();
    try {
      calculator.evaluate("1 2 &");
      fail("Se esperaba una IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid operator: &", e.getMessage());
    }
  }
}
