package com.avejorros.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.avejorros.controller.InfixToPostfixConverter;
import com.avejorros.interfaces.IList;
import com.avejorros.interfaces.IStack;
import com.avejorros.util.Calculator;
import com.avejorros.util.ListFactory;
import com.avejorros.util.StackFactory;

/**
 * Hello world!
 * 
 * @author mmogue - wichandro - gin
 */
public class App {
  public static void main(String[] args) {
    // Seleccionar implementación de lista y pila
    IList<Integer> list = ListFactory.createList("singlylinked");
    IStack<Integer> stack = StackFactory.createStack("arraylist", list);

    // Configurar la calculadora
    Calculator calculator = Calculator.getInstance();
    calculator.setStack(stack);

    // Leer expresión infix desde el archivo
    try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
      String line;
      while ((line = br.readLine()) != null) { // Leer cada línea del archivo
        if (line.trim().isEmpty()) { // Ignorar líneas vacías
          continue;
        }

        System.out.println("\nExpresión Infix: " + line);

        try {
          // Convertir infix a postfix
          String postfixExpression = InfixToPostfixConverter.convert(line);
          System.out.println("Expresión Postfix: " + postfixExpression);

          // Evaluar la expresión postfix
          int result = calculator.evaluatePostfix(postfixExpression);
          System.out.println("Resultado: " + result);
        } catch (IllegalArgumentException | ArithmeticException e) {
          System.out.println("Error: " + e.getMessage()); // Manejar errores
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
