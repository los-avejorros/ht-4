package com.avejorros.controller;

import java.util.ArrayList;

import com.avejorros.bean.AbstractStack;

public class ArrayListStack<T> extends AbstractStack<T> {
  private ArrayList<T> stack;

  public ArrayListStack() {
    stack = new ArrayList<>();
  }

  @Override
  public void push(T item) {
    stack.add(item);
    size++;
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    size--;
    return stack.remove(stack.size() - 1);
  }

  @Override
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.get(stack.size() - 1);
  }
}
