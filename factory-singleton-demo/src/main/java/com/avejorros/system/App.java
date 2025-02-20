package com.avejorros.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.avejorros.controller.PostfixCalculator;
import com.avejorros.interfaces.IPostfixCalculator;

/**
 * 
 * Hello world!
 * @author mmogue - wichandro - gin
 * 
 */
public class App {
    public static void main(String[] args) {
        IPostfixCalculator calculator = new PostfixCalculator();

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int result = calculator.evaluate(line);
                System.out.println("Resultado: " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
