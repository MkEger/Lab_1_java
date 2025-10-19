package lab1;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueueUnsync {
    private final Queue<Message> queue = new LinkedList<>();
    private final int capacity;

    public MessageQueueUnsync(int capacity) {
        this.capacity = capacity;
    }

    public void send(Message msg) {
        if (queue.size() >= capacity) {
            System.out.println("[UNSYNC] Черга переповнена, відкидаємо " + msg);
            return;
        }
        queue.add(msg);
        System.out.println("[UNSYNC] Додано " + msg + ", розмір черги: " + queue.size());
    }

    public Message receive() {
        if (queue.isEmpty()) {
            System.out.println("[UNSYNC] Черга порожня — нічого отримати");
            return null;
        }
        Message msg = queue.poll();
        System.out.println("[UNSYNC] Отримано " + msg + ", розмір черги: " + queue.size());
        return msg;
    }
}
