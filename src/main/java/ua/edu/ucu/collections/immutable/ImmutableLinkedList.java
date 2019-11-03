package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList, ImmutableOptionalLinkedList {
    private Node head, tail;
    private int size = 0;

    static class Node {
        Object value;
        Node next, prev;

        Node(Object val) {
            value = val;
            next = null;
            prev = null;
        }
    }

    private void throwIndexException(int index) {
        if ((index > size - 1) || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList add(Object e) {
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        Node node = new Node(e);
        node.next = null;
        newArr.size = size;

        if (head == null) {
            return newArr.addFirst(e);
        } else {
            Node prevNode = tail;
            newArr.head = head;

            Node temp = newArr.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
            temp.next.prev = prevNode;
            newArr.tail = temp.next;
            newArr.size++;
            return newArr;
        }
    }

    @Override
    public ImmutableList add(int index, Object e) {
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size;

        Node node = new Node(e);
        throwIndexException(index);

        newArr.head = head;

        if (size == 1 || index == 0) {
            return newArr.addFirst(e);
        } else {
            Node temp1 = newArr.head;
            Node temp2 = newArr.head.next;

            int temp2Index = 1;
            while (temp2Index < index) {
                temp1 = temp1.next;
                temp2 = temp2.next;
                temp2Index++;
            }
            temp1.next = node;
            temp1 = temp1.next;
            temp1.next = temp2;

            newArr.size++;
            return newArr;
        }
    }

    private void checkNullAddAll(Object[] c) {
        head = new Node(c[0]);
        Node temp = head;
        tail = head;

        for (int i = 1; i < c.length; i++) {
            temp.next = new Node(c[i]);
            temp.next.prev = temp;
            temp = temp.next;
            tail = temp;
        }
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size + c.length;
        if (head == null) {
            newArr.checkNullAddAll(c);
        } else {
            newArr.head = head;
            newArr.tail = tail;

            newArr.tail.next = new Node(c[0]);
            Node nodeC = newArr.tail.next;
            for (int i = 1; i < c.length; i++) {
                nodeC.next = new Node(c[i]);
                nodeC.next.prev = nodeC;
                nodeC = nodeC.next;
                newArr.tail = nodeC;
            }
        }
        return newArr;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.size = size + c.length;
        throwIndexException(index);

        if (index == 0) {
            Node continuation = head;
            newArr.head = new Node(c[0]);
            Node temp = newArr.head;
            newArr.tail = newArr.head;

            for (int i = 1; i < c.length; i++) {
                temp.next = new Node(c[i]);
                temp.next.prev = temp;
                temp = temp.next;
            }
            temp.next = continuation;
        } else {
            newArr.head = head;
            newArr.tail = tail;
            Node beg = newArr.head;

            for (int i = 0; i < index; i++) {
                beg = beg.next;
            }

            Node stored = beg;
            beg = beg.prev;

            for (int j = 0; j < c.length; j++) {
                beg.next = new Node(c[j]);
                beg.next.prev = beg;
                beg = beg.next;
            }
            beg.next = stored;

        }
        return newArr;
    }

    @Override
    public Object get(int index) {
        throwIndexException(index);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    @Override
    public ImmutableList remove(int index) {
        throwIndexException(index);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.head = head;
        newArr.size = size;
        if (index == 0) {
            return newArr.removeFirst();
        } else {
            Node temp = newArr.head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            Node left = temp.next;
            temp = temp.prev;
            temp.next = left;
            newArr.size--;
            return newArr;
        }
    }

    @Override
    public ImmutableList set(int index, Object e) {
        throwIndexException(index);
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
            if (temp.value == e) {
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
    public ImmutableList clear() {
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
        throwIndexException(0);
        return head.value;
    }

    @Override
    public Object getLast() {
        throwIndexException(0);
        return tail.value;
    }

    @Override
    public ImmutableLinkedList removeFirst() {
        throwIndexException(0);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.head = head.next;
        newArr.size = size - 1;
        return newArr;
    }

    @Override
    public ImmutableLinkedList removeLast() {
        throwIndexException(0);
        ImmutableLinkedList newArr = new ImmutableLinkedList();
        newArr.head = head;
        newArr.tail = tail;
        newArr.tail = newArr.tail.prev;
        newArr.tail.next = null;
        newArr.size = size - 1;
        return newArr;
    }
}
