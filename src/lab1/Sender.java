package lab1;

public class Sender extends Thread {
    private final MessageQueueSync queue;
    private final String name;
    private final int messagesToSend;

    public Sender(String name, MessageQueueSync queue, int messagesToSend) {
        this.name = name;
        this.queue = queue;
        this.messagesToSend = messagesToSend;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= messagesToSend; i++) {
                Message msg = new Message(name, "Привіт №" + i, i);
                queue.send(msg);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Відправник " + name + " перерваний.");
            Thread.currentThread().interrupt();
        }
    }
}
