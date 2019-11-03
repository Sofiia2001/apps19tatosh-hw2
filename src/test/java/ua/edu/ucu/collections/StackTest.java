package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPeekEmptyList() {
        stack.peek();
    }

    @Test
    public void testPeekOrdinaryStack() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Object expected = 9;
        Object got = stack.peek();
        assertEquals(expected, got);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPopEmptyList() {
        stack.pop();
    }

    @Test
    public void testPopOrdinaryStack() {
        Object[] arr = {'a', 'b', 'c', 'd'};
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }
        Object expected = 'd';
        Object got = stack.pop();
        assertEquals(expected, got);
    }

}
