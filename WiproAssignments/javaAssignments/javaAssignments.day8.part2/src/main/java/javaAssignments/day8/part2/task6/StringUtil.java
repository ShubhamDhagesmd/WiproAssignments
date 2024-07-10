/* Task 3: Create test cases with assertEquals, assertTrue, and assertFalse to validate the correctness of a custom String utility class. */

package javaAssignments.day8.part2.task6;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }
}

