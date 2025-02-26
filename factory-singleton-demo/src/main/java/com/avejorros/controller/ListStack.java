package com.avejorros.controller;

import com.avejorros.bean.AbstractStack;
import com.avejorros.interfaces.IList;

public class ListStack<T> extends AbstractStack<T> {
  private IList<T> stack;

  public ListStack(IList<T> list) {
    this.stack = list;
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
