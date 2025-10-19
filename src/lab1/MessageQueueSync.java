package lab1;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueueSync {
    private final Queue<Message> queue = new LinkedList<>();
    private final int capacity;

    public MessageQueueSync(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void send(Message msg) throws InterruptedException {
        while (queue.size() >= capacity) {
            wait();
        }
        queue.add(msg);
        System.out.println("[SYNC] Відправлено " + msg + ", розмір черги: " + queue.size());
        notifyAll();
    }

    public synchronized Message receive() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Message msg = queue.poll();
        System.out.println("[SYNC] Отримано " + msg + ", розмір черги: " + queue.size());
        notifyAll();
        return msg;
    }

    public synchronized int size() {
        return queue.size();
    }
}

