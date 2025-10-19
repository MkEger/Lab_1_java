package lab1;

import java.util.ArrayList;
import java.util.List;

public class MessageSystemSimulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Лабораторна №1: Concurrency ===");
        System.out.println("Варіант 2 → Черга повідомлень, потоки = нащадки Thread");

        System.out.println("\n--- Фаза 1: Некоректна версія (без синхронізації) ---");
        runUnsyncDemo();

        System.out.println("\n--- Фаза 2: Коректна версія (з synchronized, wait/notifyAll) ---");
        runSyncDemo();
    }

    private static void runUnsyncDemo() throws InterruptedException {
        MessageQueueUnsync unsync = new MessageQueueUnsync(2);

        Thread sender = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                unsync.send(new Message("Sender", "Msg " + i, i));
                try { Thread.sleep(50); } catch (InterruptedException e)
                { Thread.currentThread().interrupt(); }
            }
        });

        Thread receiver = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                unsync.receive();
                try { Thread.sleep(80); } catch (InterruptedException e)
                { Thread.currentThread().interrupt(); }
            }
        });

        sender.start();
        receiver.start();
        sender.join();
        receiver.join();
    }

    private static void runSyncDemo() throws InterruptedException {
        MessageQueueSync queue = new MessageQueueSync(3);

        List<Thread> threads = new ArrayList<>();
        threads.add(new Sender("Alice", queue, 5));
        threads.add(new Sender("Bob", queue, 5));
        threads.add(new Receiver("Reader1", queue, 5));
        threads.add(new Receiver("Reader2", queue, 5));

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Симуляцію завершено. У черзі залишилось: " + queue.size() + " повідомлень.");
    }
}
