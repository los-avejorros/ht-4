package com.avejorros.controller;

import com.avejorros.interfaces.IPostfixCalculator;
import com.avejorros.interfaces.IStack;

public class PostfixCalculator implements IPostfixCalculator {
  @Override
  public int evaluate(String expression) {
    IStack<Integer> stack = new ArrayListStack<>(); // Usamos ArrayListStack como implementación de pila
    String[] tokens = expression.split(" "); // Dividir la expresión en tokens (números y operadores)

    for (String token : tokens) {
      if (isNumber(token)) { // Si el token es un número
        stack.push(Integer.parseInt(token)); // Convertirlo a entero y hacer push en la pila
      } else { // Si el token es un operador
        int operand2 = stack.pop(); // Sacar el segundo operando
        int operand1 = stack.pop(); // Sacar el primer operando
        int result = applyOperation(token, operand1, operand2); // Aplicar la operación
        stack.push(result); // Hacer push del resultado en la pila
      }
    }

    return stack.pop(); // El resultado final está en la cima de la pila
  }

  private boolean isNumber(String token) {
    try {
      Integer.parseInt(token); // Intentar convertir el token a número
      return true;
    } catch (NumberFormatException e) {
      return false; // Si no es un número, devolver false
    }
  }

  private int applyOperation(String operator, int operand1, int operand2) {
    switch (operator) {
      case "+":
        return operand1 + operand2;
      case "-":
        return operand1 - operand2;
      case "*":
        return operand1 * operand2;
      case "/":
        if (operand2 == 0) {
          throw new ArithmeticException("División por cero");
        }
        return operand1 / operand2;
      case "%":
        return operand1 % operand2;
      default:
        throw new IllegalArgumentException("Operador no válido: " + operator);
    }
  }
}
