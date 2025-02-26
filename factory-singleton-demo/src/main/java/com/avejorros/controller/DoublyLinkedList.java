package com.avejorros.controller;

import com.avejorros.bean.AbstractList;

public class DoublyLinkedList<T> extends AbstractList<T> {
  private static class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.prev = null;
      this.next = null;
    }
  }

  private Node<T> head;
  private Node<T> tail;

  @Override
  public void add(T item) {
    Node<T> newNode = new Node<>(item);
    if (head == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index);
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    if (current.prev != null) {
      current.prev.next = current.next;
    } else {
      head = current.next;
    }
    if (current.next != null) {
      current.next.prev = current.prev;
    } else {
      tail = current.prev;
    }
    size--;
    return current.data;
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
