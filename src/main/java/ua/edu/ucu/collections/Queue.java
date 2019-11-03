package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

class Queue {
    private ImmutableLinkedList array;

    Queue() {
        array = new ImmutableLinkedList();
    }

    Object peek() {
        return array.getFirst();
    }

    Object dequeue() {
        Object first = peek();
        array = array.removeFirst();
        return first;
    }

    void enqueue(Object e) {
        array = array.addLast(e);
    }
}
