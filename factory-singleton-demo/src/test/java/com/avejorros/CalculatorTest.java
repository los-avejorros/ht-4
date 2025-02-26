package com.avejorros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.avejorros.controller.ArrayListStack;
import com.avejorros.util.Calculator;

public class CalculatorTest {
  @Test
  public void testEvaluatePostfix() {
    Calculator calculator = Calculator.getInstance();
    calculator.setStack(new ArrayListStack<>());

    // Caso 1: Expresión postfix válida
    assertEquals(15, calculator.evaluatePostfix("1 2 + 4 * 3 +"));

    // Caso 2: Otra expresión postfix válida
    assertEquals(30, calculator.evaluatePostfix("6 2 3 + *"));

    // Caso 3: Expresión con división
    assertEquals(2, calculator.evaluatePostfix("10 5 /"));

    // Caso 4: Expresión con módulo
    assertEquals(1, calculator.evaluatePostfix("5 2 %"));
  }

  @Test(expected = ArithmeticException.class)
  public void testEvaluatePostfixDivisionByZero() {
    Calculator calculator = Calculator.getInstance();
    calculator.setStack(new ArrayListStack<>());

    // Caso: División por cero
    calculator.evaluatePostfix("10 0 /");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvaluatePostfixInvalidOperator() {
    Calculator calculator = Calculator.getInstance();
    calculator.setStack(new ArrayListStack<>());

    // Caso: Operador no válido
    calculator.evaluatePostfix("10 5 &");
  }

  @Test
  public void testInfixToPostfix() {
    Calculator calculator = Calculator.getInstance();

    // Caso 1: Expresión infix válida
    assertEquals("10 20 9 * +", calculator.infixToPostfix("10+20*9"));

    // Caso 2: Expresión con paréntesis
    assertEquals("1 2 + 9 *", calculator.infixToPostfix("(1+2)*9"));

    // Caso 3: Expresión con múltiples operadores
    assertEquals("5 6 2 + * 12 4 / -", calculator.infixToPostfix("5*(6+2)-12/4"));

    // Caso 4: Expresión con división y paréntesis
    assertEquals("3 4 2 * 1 5 - / +", calculator.infixToPostfix("3+4*2/(1-5)"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInfixToPostfixInvalidCharacter() {
    Calculator calculator = Calculator.getInstance();

    // Caso: Carácter no válido
    calculator.infixToPostfix("10 + 20 * 9 &");
  }

  @Test
  public void testIsNumber() {
    Calculator calculator = Calculator.getInstance();

    // Caso 1: Token es un número
    assertTrue(calculator.isNumber("123"));

    // Caso 2: Token es un número negativo
    assertTrue(calculator.isNumber("-456"));

    // Caso 3: Token no es un número
    assertFalse(calculator.isNumber("+"));
  }

  @Test
  public void testApplyOperation() {
    Calculator calculator = Calculator.getInstance();

    // Caso 1: Suma
    assertEquals(15, calculator.applyOperation("+", 10, 5));

    // Caso 2: Resta
    assertEquals(5, calculator.applyOperation("-", 10, 5));

    // Caso 3: Multiplicación
    assertEquals(50, calculator.applyOperation("*", 10, 5));

    // Caso 4: División
    assertEquals(2, calculator.applyOperation("/", 10, 5));

    // Caso 5: Módulo
    assertEquals(0, calculator.applyOperation("%", 10, 5));
  }

  @Test(expected = ArithmeticException.class)
  public void testApplyOperationDivisionByZero() {
    Calculator calculator = Calculator.getInstance();

    // Caso: División por cero
    calculator.applyOperation("/", 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyOperationInvalidOperator() {
    Calculator calculator = Calculator.getInstance();

    // Caso: Operador no válido
    calculator.applyOperation("&", 10, 5);
  }

  @Test
  public void testIsOperator() {
    Calculator calculator = Calculator.getInstance();

    // Caso 1: Operador válido
    assertTrue(calculator.isOperator('+'));

    // Caso 2: Operador válido
    assertTrue(calculator.isOperator('-'));

    // Caso 3: Operador válido
    assertTrue(calculator.isOperator('*'));

    // Caso 4: Operador válido
    assertTrue(calculator.isOperator('/'));

    // Caso 5: Operador válido
    assertTrue(calculator.isOperator('%'));

    // Caso 6: Carácter no es un operador
    assertFalse(calculator.isOperator('&'));
  }

  @Test
  public void testPrecedence() {
    Calculator calculator = Calculator.getInstance();

    // Caso 1: Precedencia de suma
    assertEquals(1, calculator.precedence('+'));

    // Caso 2: Precedencia de resta
    assertEquals(1, calculator.precedence('-'));

    // Caso 3: Precedencia de multiplicación
    assertEquals(2, calculator.precedence('*'));

    // Caso 4: Precedencia de división
    assertEquals(2, calculator.precedence('/'));

    // Caso 5: Precedencia de módulo
    assertEquals(2, calculator.precedence('%'));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPrecedenceInvalidOperator() {
    Calculator calculator = Calculator.getInstance();

    // Caso: Operador no válido
    calculator.precedence('&');
  }
}
