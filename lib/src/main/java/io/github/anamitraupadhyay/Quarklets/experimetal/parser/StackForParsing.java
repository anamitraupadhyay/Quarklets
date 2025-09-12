package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

public class StackForParsing {
    private char[] array;
    private int top;        // Points to current top element
    private int capacity;   // Maximum size

    public StackForParsing(int initialCapacity) {
        this.capacity = initialCapacity;
        this.array = new char[capacity];
        this.top = -1;  // Empty stack
    }

    // Constructor from StringBuilder size
    public StackForParsing(StringBuilder obj) {
        this(obj.length());
    }

    public void push(char element) {
        if (isFull()) {
            throw new IllegalStateException("Stack overflow!");
        }
        array[++top] = element;
    }

    public char pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow!");
        }
        return array[top--];
    }

    public char peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }
}
