package com.avejorros.controller;

import java.util.HashMap;
import java.util.Map;

import com.avejorros.interfaces.IStack;

public class InfixToPostfixConverter {

  private static final Map<Character, Integer> PRECEDENCE = new HashMap<>();

  static {
    PRECEDENCE.put('+', 1);
    PRECEDENCE.put('-', 1);
    PRECEDENCE.put('*', 2);
    PRECEDENCE.put('/', 2);
    PRECEDENCE.put('%', 2);
  }

  public static String convert(String infix) {
    StringBuilder postfix = new StringBuilder();
    IStack<Character> stack = new ArrayListStack<>();

    int i = 0;
    while (i < infix.length()) {
      char ch = infix.charAt(i);

      // Si es un dígito, leer el número completo
      if (Character.isDigit(ch)) {
        StringBuilder number = new StringBuilder();
        while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
          number.append(infix.charAt(i));
          i++;
        }
        postfix.append(number).append(" ");
        continue; // Continuar al siguiente carácter
      }

      // Si es un paréntesis de apertura
      if (ch == '(') {
        stack.push(ch);
      }
      // Si es un paréntesis de cierre
      else if (ch == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          postfix.append(stack.pop()).append(" ");
        }
        stack.pop(); // Eliminar '(' de la pila
      }
      // Si es un operador
      else if (PRECEDENCE.containsKey(ch)) { // Verificar si el operador está en el mapa
        while (!stack.isEmpty() && stack.peek() != '(' && PRECEDENCE.get(ch) <= PRECEDENCE.get(stack.peek())) {
          postfix.append(stack.pop()).append(" ");
        }
        stack.push(ch);
      }
      // Si es un espacio en blanco, ignorarlo
      else if (Character.isWhitespace(ch)) {
        // Ignorar espacios en blanco
      }
      // Si es un carácter no válido
      else {
        throw new IllegalArgumentException("Operador no válido: " + ch);
      }

      i++; // Mover al siguiente carácter
    }

    // Vaciar la pila al final
    while (!stack.isEmpty()) {
      postfix.append(stack.pop()).append(" ");
    }

    return postfix.toString().trim();
  }

}
