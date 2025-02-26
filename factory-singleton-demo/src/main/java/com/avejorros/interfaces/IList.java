package com.avejorros.interfaces;

public interface IList<T> {
  void add(T item);
  T remove(int index);
  T get(int index);
  boolean isEmpty();
  int size();
}
