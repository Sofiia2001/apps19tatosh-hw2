package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue;

    @Before
    public void setUp() {
        queue = new Queue();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPeekEmptyList() {
        queue.peek();
    }

    @Test
    public void testPeekOrdinaryQueue() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        Object expected = 0;
        Object got = queue.peek();
        assertEquals(expected, got);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDequeueEmptyList() {
        queue.dequeue();
    }

    @Test
    public void testDequeueOrdinaryQueue() {
        Object[] arr = {'a', 'b', 'c', 'd'};
        for (int i = 0; i < arr.length; i++) {
            queue.enqueue(arr[i]);
        }
        Object expected = 'a';
        Object got = queue.dequeue();
        assertEquals(expected, got);
    }
}
