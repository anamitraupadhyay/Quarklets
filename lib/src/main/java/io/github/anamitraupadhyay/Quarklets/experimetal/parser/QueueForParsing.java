package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

public class QueueForParsing {
    private char[] array;
    private int front;      // Points to first element
    private int rear;       // Points to last element
    private int capacity;   // Maximum size
    private int size;       // Current number of elements

    public QueueForParsing(int initialCapacity) {
        this.capacity = initialCapacity;
        this.array = new char[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Constructor from StringBuilder size
    public QueueForParsing(StringBuilder obj) {
        this(obj.length());
    }

    public void enqueue(char element) {
        if (isFull()) {
            throw new IllegalStateException("Queue overflow!");
        }
        rear = (rear + 1) % capacity;  // Circular queue
        array[rear] = element;
        size++;
    }

    public char dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue underflow!");
        }
        char element = array[front];
        front = (front + 1) % capacity;  // Circular queue
        size--;
        return element;
    }

    public char peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }
}
