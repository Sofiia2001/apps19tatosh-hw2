package ua.edu.ucu.collections.immutable;

public interface ImmutableOptionalLinkedList {
    ImmutableLinkedList addFirst(Object e);

    ImmutableLinkedList addLast(Object e);

    Object getFirst();

    Object getLast();

    ImmutableLinkedList removeFirst();

    ImmutableLinkedList removeLast();
}
