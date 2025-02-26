package com.avejorros.controller;

import com.avejorros.bean.AbstractList;

public class SinglyLinkedList<T> extends AbstractList<T> {
  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private Node<T> head;

  @Override
  public void add(T item) {
    Node<T> newNode = new Node<>(item);
    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    size++;
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index);
    }
    if (index == 0) {
      T data = head.data;
      head = head.next;
      size--;
      return data;
    }
    Node<T> current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.next;
    }
    T data = current.next.data;
    current.next = current.next.next;
    size--;
    return data;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index);
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }
}
