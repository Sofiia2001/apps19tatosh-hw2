package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

class Stack {
    private ImmutableLinkedList array;

    Stack() {
        array = new ImmutableLinkedList();
    }

    Object peek() {
        return array.getLast();
    }

    Object pop() {
        Object first = peek();
        array = array.removeLast();
        return first;
    }

    void push(Object e) {
        array = array.addLast(e);
    }
}
