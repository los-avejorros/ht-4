package com.avejorros.util;

import com.avejorros.controller.ArrayListStack;
import com.avejorros.interfaces.IStack;

public class Calculator {
  private static Calculator instance;
  private IStack<Integer> stack;

  private Calculator() {
    // Constructor privado para evitar instanciación directa
  }

  public static Calculator getInstance() {
    if (instance == null) {
      instance = new Calculator();
    }
    return instance;
  }

  public void setStack(IStack<Integer> stack) {
    this.stack = stack;
  }

  public int evaluatePostfix(String expression) {
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

  public String infixToPostfix(String expression) {
    StringBuilder postfix = new StringBuilder(); // Para construir la expresión postfix
    IStack<Character> stack = new ArrayListStack<>(); // Usamos ArrayListStack como implementación de pila

    int i = 0;
    while (i < expression.length()) {
      char ch = expression.charAt(i);

      // Si es un dígito, leer el número completo
      if (Character.isDigit(ch)) {
        StringBuilder number = new StringBuilder();
        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
          number.append(expression.charAt(i));
          i++;
        }
        postfix.append(number).append(" "); // Agregar el número a la expresión postfix
        continue; // Continuar al siguiente carácter
      }

      // Si es un paréntesis de apertura
      if (ch == '(') {
        stack.push(ch);
      }
      // Si es un paréntesis de cierre
      else if (ch == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          postfix.append(stack.pop()).append(" "); // Sacar operadores de la pila
        }
        stack.pop(); // Eliminar '(' de la pila
      }
      // Si es un operador
      else if (isOperator(ch)) {
        while (!stack.isEmpty() && stack.peek() != '(' && precedence(ch) <= precedence(stack.peek())) {
          postfix.append(stack.pop()).append(" "); // Sacar operadores de mayor o igual precedencia
        }
        stack.push(ch); // Hacer push del operador actual
      }
      // Si es un espacio en blanco, ignorarlo
      else if (Character.isWhitespace(ch)) {
        // Ignorar espacios en blanco
      }
      // Si es un carácter no válido
      else {
        throw new IllegalArgumentException("Carácter no válido: " + ch);
      }

      i++; // Mover al siguiente carácter
    }

    // Vaciar la pila al final
    while (!stack.isEmpty()) {
      postfix.append(stack.pop()).append(" ");
    }

    return postfix.toString().trim(); // Devolver la expresión postfix sin espacios adicionales
  }

  public boolean isNumber(String token) {
    try {
      Integer.parseInt(token); // Intentar convertir el token a número
      return true;
    } catch (NumberFormatException e) {
      return false; // Si no es un número, devolver false
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
          throw new ArithmeticException("División por cero");
        }
        return operand1 / operand2;
      case "%":
        return operand1 % operand2;
      default:
        throw new IllegalArgumentException("Operador no válido: " + operator);
    }
  }

  public boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
  }

  public int precedence(char operator) {
    switch (operator) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
      case '%':
        return 2;
      default:
        throw new IllegalArgumentException("Operador no válido: " + operator);
    }
  }
}
