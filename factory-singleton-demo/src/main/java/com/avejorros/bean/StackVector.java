package com.avejorros.bean;

import java.util.Vector;

import com.avejorros.interfaces.IStack;

public class StackVector<T> implements IStack<T> {
  private Vector<T> stack;

  public StackVector() {
    stack = new Vector<>();
  }

  @Override
  public void push(T item) {
    stack.add(item);
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.remove(stack.size() - 1);
  }

  @Override
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.get(stack.size() - 1);
  }

  @Override
  public boolean isEmpty() {
    return stack.isEmpty();
  }

  @Override
  public int size() {
    return stack.size();
  }
}
