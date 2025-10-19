package lab1;

public class Receiver extends Thread {
    private final MessageQueueSync queue;
    private final String name;
    private final int messagesToReceive;

    public Receiver(String name, MessageQueueSync queue, int messagesToReceive) {
        this.name = name;
        this.queue = queue;
        this.messagesToReceive = messagesToReceive;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < messagesToReceive; i++) {
                Message msg = queue.receive();
                Thread.sleep(150);
                System.out.println(name + " прочитав: " + msg);
            }
        } catch (InterruptedException e) {
            System.out.println("Отримувач " + name + " перерваний.");
            Thread.currentThread().interrupt();
        }
    }
}
