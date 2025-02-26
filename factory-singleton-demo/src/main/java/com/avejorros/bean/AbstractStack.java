package com.avejorros.bean;

import com.avejorros.interfaces.IStack;

public abstract class AbstractStack<T> implements IStack<T> {
  protected int size;

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }
}
