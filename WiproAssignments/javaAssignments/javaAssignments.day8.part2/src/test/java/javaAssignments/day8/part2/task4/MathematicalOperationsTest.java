package javaAssignments.day8.part2.task4;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathematicalOperationsTest {

    private MathematicalOperations mathOps = new MathematicalOperations();

    @Test
    public void testAdd() {
        assertEquals(5, mathOps.add(2, 3));
        assertEquals(-1, mathOps.add(2, -3));
        assertEquals(0, mathOps.add(0, 0));
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, mathOps.subtract(2, 3));
        assertEquals(5, mathOps.subtract(8, 3));
        assertEquals(0, mathOps.subtract(5, 5));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, mathOps.multiply(2, 3));
        assertEquals(-6, mathOps.multiply(2, -3));
        assertEquals(0, mathOps.multiply(0, 5));
        assertEquals(0, mathOps.multiply(5, 0));
    }

    @Test
    public void testDivide() {
        assertEquals(2.5, mathOps.divide(5, 2), 0.0001); // delta specifies the maximum delta between expected and actual for which both numbers are still considered equal.
        assertEquals(-2.0, mathOps.divide(-10, 5), 0.0001); 
    }
}// delta specifies the maximum delta between expected and actual for which both numbers are still considered equal.

