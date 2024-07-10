package javaAssignments.day8.part2.task6;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testIsEmpty() {
        assertTrue(StringUtil.isEmpty(null));
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty("  "));
        assertFalse(StringUtil.isEmpty("  a  "));
        assertFalse(StringUtil.isEmpty("abc"));
    }

    @Test
    public void testReverse() {
        assertEquals("", StringUtil.reverse(""));
        assertEquals("cba", StringUtil.reverse("abc"));
        assertEquals("54321", StringUtil.reverse("12345"));
    }

    @Test
    public void testToUpperCase() {
        assertEquals("", StringUtil.toUpperCase(""));
        assertEquals("HELLO", StringUtil.toUpperCase("hello"));
        assertEquals("123ABC", StringUtil.toUpperCase("123abc"));
    }
}

