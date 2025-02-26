package com.avejorros.util;

import com.avejorros.controller.DoublyLinkedList;
import com.avejorros.controller.SinglyLinkedList;
import com.avejorros.interfaces.IList;

public class ListFactory {
  public static <T> IList<T> createList(String type) {
    switch (type.toLowerCase()) {
      case "singlylinked":
        return new SinglyLinkedList<>();
      case "doublylinked":
        return new DoublyLinkedList<>();
      default:
        throw new IllegalArgumentException("Invalid list type: " + type);
    }
  }
}
