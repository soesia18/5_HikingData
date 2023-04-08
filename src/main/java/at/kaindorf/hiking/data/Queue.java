package at.kaindorf.hiking.data;

import lombok.ToString;

public class Queue {

    private final java.util.Queue<Trk> queue = new java.util.LinkedList<>();
    private final int size;

    private final int max;
    private int count = 0;

    public Queue(int size, int max) {
        this.size = size;
        this.max = max;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() == size;
    }

    public boolean isFinished() {
        return count == max;
    }

    public void enqueue(Trk trk) throws InterruptedException {
        synchronized (queue) {
            while (isFull()) {
                System.out.println("Queue is full");
                queue.wait();
            }
            queue.add(trk);
            count++;
            queue.notifyAll();
        }
    }

    public Trk dequeue() throws InterruptedException {
        synchronized (queue) {
            while (isEmpty()) {
                System.out.println("Queue is empty");
                queue.wait();
            }
            Trk trk = queue.remove();
            queue.notifyAll();
            return trk;
        }
    }

    /*private final Trk[] queue;
    private int front;
    private int rear;
    private final int size;

    public Queue(int size) {
        this.size = size;
        queue = new Trk[size];
        front = -1;
        rear = -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return front == 0 && rear == size - 1;
    }

    public void enqueue(Trk data) throws InterruptedException {
        synchronized (queue) {
            while (isFull()) {
                System.out.println("Queue is full");
                queue.wait();
            }
            if (front == -1) {
                front = 0;
            }
            rear++;
            queue[rear] = data;
            queue.notifyAll();
        }
    }

    public Trk dequeue() throws InterruptedException {
        synchronized (queue) {
            Trk data = null;
            while (isEmpty()) {
                System.out.println("Queue is empty");
                queue.wait();
            }
            data = queue[front];
            front++;
            if (front > rear) {
                front = rear = -1;
            }
            queue.notifyAll();
            return data;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("Queue elements are: ");
            for (int i = front; i <= rear; i++) {
                System.out.println(queue[i]);
            }
        }
    }*/
}
