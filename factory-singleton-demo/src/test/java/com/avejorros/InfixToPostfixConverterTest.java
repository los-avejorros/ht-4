package com.avejorros;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.avejorros.controller.InfixToPostfixConverter;

public class InfixToPostfixConverterTest {
  @Test
    public void testConvert() {
        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        assertEquals("10 20 9 * +", converter.convert("10 + 20 * 9"));
        assertEquals("1 2 + 9 *", converter.convert("( 1 + 2 ) * 9"));
        assertEquals("5 6 2 + * 12 4 / -", converter.convert("5 * ( 6 + 2 ) - 12 / 4 "));
        assertEquals("3 4 2 * 1 5 - / +", converter.convert("3 + 4 * 2 / ( 1 - 5 )"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCharacter() {
        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        converter.convert("10 + 20 * 9 &");
    }
}
