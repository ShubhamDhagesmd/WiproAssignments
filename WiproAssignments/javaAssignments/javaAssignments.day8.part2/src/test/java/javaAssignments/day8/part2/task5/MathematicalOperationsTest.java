package javaAssignments.day8.part2.task5;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MathematicalOperationsTest {

    private static MathematicalOperations mathOps;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Initialize resources once before all tests
        System.out.println("Setting up before class...");
        mathOps = new MathematicalOperations();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        // Clean up resources after all tests
        System.out.println("Tearing down after class...");
        mathOps = null;
    }

    @Before
    public void setUp() {
        // Initialize resources before each test
        System.out.println("Setting up before each test...");
        // You can add more setup code here if needed
    }

    @After
    public void tearDown() {
        // Clean up resources after each test
        System.out.println("Tearing down after each test...");
        // You can add more teardown code here if needed
    }

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
        assertEquals(-2.0, mathOps.divide(-10, 5), 0.0001); // delta specifies the maximum delta between expected and actual for which both numbers are still considered equal.

        // Test division by zero
        try {
            mathOps.divide(10, 0);
            fail("Expected IllegalArgumentException for division by zero");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}

