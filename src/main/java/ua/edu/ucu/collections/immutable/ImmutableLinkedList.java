package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableLinkedList implements ImmutableList,
        ImmutableOptionalLinkedList {
    private Node head, tail;
    private int size = 0;

    static class Node {
        private Object value;
        private Node next, prev;

        Node(Object val) {
            value = val;
            next = null;
            prev = null;
        }
    }

    private void throwIndexExceptionAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void throwIndexExceptionGet(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return addAll(size, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        throwIndexExceptionAdd(index);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size + c.length;

        Node continuation = head;
        newArr.head = new Node(c[0]);
        Node temp = newArr.head;
        newArr.tail = newArr.head;
        int startingIndex = 1;

        if (index != 0) {
            newArr.head = head;
            newArr.tail = tail;
            temp = newArr.head;
            startingIndex = 0;

            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            continuation = temp.next;

        }
        for (int i = startingIndex; i < c.length; i++) {
            temp.next = new Node(c[i]);
            temp.next.prev = temp;
            temp = temp.next;
        }
        temp.next = continuation;

        return newArr;
    }

    @Override
    public Object get(int index) {
        throwIndexExceptionGet(index);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        throwIndexExceptionGet(index);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size;

        if (index == 0) {
            newArr.head = head;
            return newArr.removeFirst();
        } else {
            newArr.size--;
            Node currHead = head;
            newArr.head = new Node(currHead.value);
            Node change = newArr.head;
            currHead = currHead.next;
            int counter = 0;
            while (counter < index - 1) {
                change.next = new Node(currHead.value);
                change = change.next;
                currHead = currHead.next;
                counter++;
            }
            currHead = currHead.next;
            while (currHead != null) {
                change.next = new Node(currHead.value);
                change = change.next;
                currHead = currHead.next;
            }
            return newArr;
        }
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        throwIndexExceptionGet(index);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size;

        newArr.head = head;
        Node temp = newArr.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.value = e;
        return newArr;
    }

    @Override
    public int indexOf(Object e) {
        int toReturn = -1;
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value.equals(e)) {
                toReturn = i;
                break;
            }
            temp = temp.next;
        }
        return toReturn;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Node temp = head;
        Object[] arr;
        arr = new Object[size];
        int i = 0;
        while (temp != null) {
            arr[i] = temp.value;
            i++;
            temp = temp.next;
        }
        return arr;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public ImmutableLinkedList addFirst(Object e) {
        Node node = new Node(e);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size + 1;
        if (head == null) {
            newArr.head = node;
            newArr.tail = newArr.head;
        } else {
            node.next = head;
            node.next.prev = node;
            newArr.head = node;
            newArr.tail = tail;
        }
        return newArr;
    }

    @Override
    public ImmutableLinkedList addLast(Object e) {
        Node node = new Node(e);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size + 1;
        if (head == null) {
            newArr.head = node;
            newArr.tail = newArr.head;
        } else {
            newArr.head = head;
            newArr.tail = tail;
            newArr.tail.next = node;
            newArr.tail.next.prev = newArr.tail;
            newArr.tail = newArr.tail.next;
        }
        return newArr;
    }

    @Override
    public Object getFirst() {
        throwIndexExceptionGet(0);
        return head.value;
    }

    @Override
    public Object getLast() {
        throwIndexExceptionGet(0);
        return tail.value;
    }

    @Override
    public ImmutableLinkedList removeFirst() {
        throwIndexExceptionGet(0);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.head = head.next;
        newArr.size = size - 1;
        return newArr;
    }

    @Override
    public ImmutableLinkedList removeLast() {
        throwIndexExceptionGet(0);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.head = head;
        newArr.tail = tail;
        newArr.tail = newArr.tail.prev;
        newArr.tail.next = null;
        newArr.size = size - 1;
        return newArr;
    }
}
