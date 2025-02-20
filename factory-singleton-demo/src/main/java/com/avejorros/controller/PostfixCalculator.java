package com.avejorros.controller;

import com.avejorros.interfaces.IStack;
import com.avejorros.bean.StackVector;
import com.avejorros.interfaces.IPostfixCalculator;

public class PostfixCalculator implements IPostfixCalculator {
  @Override
  public int evaluate(String expression) {
    IStack<Integer> stack = new StackVector<>();
    String[] tokens = expression.split(" ");

    for (String token : tokens) {
      if (isNumber(token)) {
        stack.push(Integer.parseInt(token));
      } else {
        int operand2 = stack.pop();
        int operand1 = stack.pop();
        int result = applyOperation(token, operand1, operand2);
        stack.push(result);
      }
    }

    return stack.pop();
  }

  public boolean isNumber(String token) {
    try {
      Integer.parseInt(token);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public int applyOperation(String operator, int operand1, int operand2) {
    switch (operator) {
      case "+":
        return operand1 + operand2;
      case "-":
        return operand1 - operand2;
      case "*":
        return operand1 * operand2;
      case "/":
        if (operand2 == 0) {
          throw new ArithmeticException("Division by zero");
        }
        return operand1 / operand2;
      case "%":
        return operand1 % operand2;
      default:
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }
  }
}
