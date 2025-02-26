package com.avejorros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.avejorros.controller.ArrayListStack;
import com.avejorros.interfaces.IStack;

public class StackTest {
  @Test
    public void testArrayListStack() {
        IStack<Integer> stack = new ArrayListStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
        assertTrue(stack.isEmpty());
    }
}
