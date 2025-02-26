package com.avejorros.util;

import com.avejorros.controller.ArrayListStack;
import com.avejorros.controller.ListStack;
import com.avejorros.controller.VectorStack;
import com.avejorros.interfaces.IList;
import com.avejorros.interfaces.IStack;

public class StackFactory {
  public static <T> IStack<T> createStack(String type, IList<T> list) {
    switch (type.toLowerCase()) {
      case "arraylist":
        return new ArrayListStack<>();
      case "vector":
        return new VectorStack<>();
      case "list":
        return new ListStack<>(list);
      default:
        throw new IllegalArgumentException("Invalid stack type: " + type);
    }
  }
}
