package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    private ImmutableLinkedList EmptyArr;
    private ImmutableList StandartArr;
    private ImmutableOptionalLinkedList OrdinaryArr;

    @Before
    public void setUp() {
        EmptyArr = new ImmutableLinkedList();

        StandartArr = EmptyArr;
        for (int i = 1; i < 6; i++) {
            StandartArr = StandartArr.add(i);
        }

        OrdinaryArr = EmptyArr;
        for (int i = 1; i < 6; i++) {
            OrdinaryArr = OrdinaryArr.addLast(i);
        }
    }

    @Test
    public void testAddToEmptyList() {
        String got = EmptyArr.add('a').toString();
        String expected = Arrays.toString(new char[]{'a'});

        assertEquals(expected, got);
    }

    @Test
    public void testAddToList() {
        String got = StandartArr.add(100).toString();
        String expected = Arrays.toString(new int[]{1, 2, 3, 4, 5, 100});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddByIndexToEmptyList() {
        EmptyArr.add(0, 'a');
    }

    @Test
    public void testAddByIndexToList() {
        String got = StandartArr.add(0, 100).toString();
        String expected = Arrays.toString(new int[]{100, 1, 2, 3, 4, 5});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddByOutOfBoundIndexToList() {
        StandartArr.add(10, 100);
    }

    @Test
    public void testAddAllToEmptyList() {
        String got = EmptyArr.addAll(new Object[]{1, 2, 3, 4, 5}).toString();
        String expected = Arrays.toString(new int[]{1, 2, 3, 4, 5});

        assertEquals(expected, got);
    }

    @Test
    public void testAddAllToList() {
        String got = StandartArr.addAll(new Object[]{1, 2, 3, 4, 5}).toString();
        String expected = Arrays.toString(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllByIndexToEmptyList() {
        EmptyArr.addAll(0, new Object[]{1, 2});
    }

    @Test
    public void testAddAllByFirstIndexToList() {
        String got = StandartArr.addAll(0, new Object[]{12, 13}).toString();
        String expected = Arrays.toString(new int[]{12, 13, 1, 2, 3, 4, 5});

        assertEquals(expected, got);
    }

    @Test
    public void testAddAllByMiddleIndexToList() {
        String got = StandartArr.addAll(3, new Object[]{11, 12}).toString();
        String expected = Arrays.toString(new int[]{1, 2, 3, 11, 12, 4, 5});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllByOutOfBoundIndexToList() {
        StandartArr.addAll(6, new Object[]{1, 2, 3, 4, 5});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFromEmptyList() {
        EmptyArr.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByOutOfBoundIndexFromList() {
        StandartArr.get(7);
    }

    @Test
    public void testGetByIndexList() {
        Object got = StandartArr.get(3);
        Object expected = 4;

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByFirstIndexFromEmptyList() {
        EmptyArr.remove(0);
    }

    @Test
    public void testRemoveByFirstIndexFromList() {
        String got = StandartArr.remove(0).toString();
        String expected = Arrays.toString(new int[]{2, 3, 4, 5});

        assertEquals(got, expected);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByOutOfBoundIndexFromList() {
        StandartArr.remove(8);
    }

    @Test
    public void testRemoveByMiddleIndexFromList() {
        String got = StandartArr.remove(3).toString();
        String expected = Arrays.toString(new int[]{1, 2, 3, 5});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOnFirstIndexFromEmptyList() {
        EmptyArr.set(0, 1);
    }

    @Test
    public void testSetOnFirstIndexFromList() {
        String got = StandartArr.set(0, 12).toString();
        String expected = Arrays.toString(new int[]{12, 2, 3, 4, 5});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOnOutOfBoundIndexFromList() {
        StandartArr.set(12, 12);
    }

    @Test
    public void testSetOnMiddleIndexFromList() {
        String got = StandartArr.set(4, 12).toString();
        String expected = Arrays.toString(new int[]{1, 2, 3, 4, 12});

        assertEquals(expected, got);
    }

    @Test
    public void testIndexOfFromEmptyList() {
        int got = EmptyArr.indexOf(1);
        int expected = -1;

        assertEquals(expected, got);
    }

    @Test
    public void testIndexOfFromListWithoutElement() {
        int got = StandartArr.indexOf(100);
        int expected = -1;

        assertEquals(expected, got);
    }

    @Test
    public void testIndexOfFromList() {
        int got = StandartArr.indexOf(4);
        int expected = 3;

        assertEquals(expected, got);
    }

    @Test
    public void testSizeOfEmptyList() {
        int got = EmptyArr.size();
        int expected = 0;

        assertEquals(expected, got);
    }

    @Test
    public void testSizeOfList() {
        int got = StandartArr.size();
        int expected = 5;

        assertEquals(expected, got);
    }

    @Test
    public void testClearEmptyList() {
        String got = EmptyArr.clear().toString();
        String expected = Arrays.toString(new Object[]{});

        assertEquals(expected, got);
    }

    @Test
    public void testClearList() {
        String got = StandartArr.clear().toString();
        String expected = Arrays.toString(new Object[]{});

        assertEquals(expected, got);
    }

    @Test
    public void testIsEmptyOfEmptyList() {
        boolean expected = true;
        boolean got = EmptyArr.isEmpty();

        assertEquals(expected, got);
    }

    @Test
    public void testIsEmptyList() {
        boolean expected = false;
        boolean got = StandartArr.isEmpty();

        assertEquals(expected, got);
    }

    @Test
    public void testToArrayEmptyList() {
        Object[] expected = {};
        Object[] got = EmptyArr.toArray();

        assertArrayEquals(expected, got);
    }

    @Test
    public void testToArrayList() {
        Object[] expected = {1, 2, 3, 4, 5};
        Object[] got = StandartArr.toArray();

        assertArrayEquals(expected, got);
    }

    @Test
    public void testToStringEmptyList() {
        String expected = Arrays.toString(new Object[]{});
        String got = EmptyArr.toString();

        assertEquals(expected, got);
    }

    @Test
    public void testToStringList() {
        String expected = Arrays.toString(new Object[]{1, 2, 3, 4, 5});
        String got = StandartArr.toString();

        assertEquals(expected, got);
    }

    @Test
    public void testAddFirstToEmptyList() {
        String expected = Arrays.toString(new Object[]{1});
        String got = EmptyArr.addFirst(1).toString();

        assertEquals(expected, got);
    }

    @Test
    public void testAddFirstToStandartList() {
        String expected = Arrays.toString(new Object[]{10, 1, 2, 3, 4, 5});
        String got = OrdinaryArr.addFirst(10).toString();

        assertEquals(expected, got);
    }

    @Test
    public void testAddLastToEmptyList() {
        String expected = Arrays.toString(new Object[]{1});
        String got = EmptyArr.addLast(1).toString();

        assertEquals(expected, got);
    }

    @Test
    public void testAddLastToStandartList() {
        String expected = Arrays.toString(new Object[]{1, 2, 3, 4, 5, 10});
        String got = OrdinaryArr.addLast(10).toString();

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tesGetFirstFromEmptyList() {
        EmptyArr.getFirst();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLastFromEmptyList() {
        EmptyArr.getLast();
    }

    @Test
    public void testGetFirstFromStandartList() {
        Object got = OrdinaryArr.getFirst();
        Object expected = 1;

        assertEquals(expected, got);
    }

    @Test
    public void testGetLastFromStandartList() {
        Object got = OrdinaryArr.getLast();
        Object expected = 5;

        assertEquals(expected, got);
    }

    @Test
    public void testRemoveLastFromStandartList() {
        String got = OrdinaryArr.removeLast().toString();
        String expected = Arrays.toString(new Object[] {1, 2, 3, 4});

        assertEquals(expected, got);
    }

    @Test
    public void testRemoveFirstFromStandartList() {
        String got = OrdinaryArr.removeFirst().toString();
        String expected = Arrays.toString(new Object[] {2, 3, 4, 5});

        assertEquals(expected, got);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveLastFromEmptyList() {
        EmptyArr.removeLast();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFirstFromEmptyList() {
        EmptyArr.removeFirst();
    }

}


