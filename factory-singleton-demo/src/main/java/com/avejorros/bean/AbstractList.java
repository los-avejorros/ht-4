package com.avejorros.bean;

import com.avejorros.interfaces.IList;

public abstract class AbstractList<T> implements IList<T> {
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
