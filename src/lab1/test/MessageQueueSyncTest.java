package lab1.test;

import lab1.Message;
import lab1.MessageQueueSync;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.*;

class MessageQueueSyncTest {

    @Test
    void testSingleThreadSendAndReceive() throws InterruptedException {
        MessageQueueSync queue = new MessageQueueSync(2);
        Message msg = new Message("Tester", "Hello", 1);
        queue.send(msg);
        Message received = queue.receive();

        assertNotNull(received);
        assertEquals("Tester", received.toString().contains("Tester") ? "Tester" : null);
    }

    @Test
    void testBlockingSendAndReceive() throws Exception {
        MessageQueueSync queue = new MessageQueueSync(1);

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Void> sender = service.submit(() -> {
            queue.send(new Message("A", "First", 1));
            queue.send(new Message("A", "Second", 2));
            return null;
        });

        Future<Void> receiver = service.submit(() -> {
            Thread.sleep(200);
            queue.receive();
            queue.receive();
            return null;
        });

        sender.get(2, TimeUnit.SECONDS);
        receiver.get(2, TimeUnit.SECONDS);
        service.shutdownNow();
    }

    @Test
    void testQueueCapacity() throws InterruptedException {
        MessageQueueSync queue = new MessageQueueSync(2);
        queue.send(new Message("User1", "Msg1", 1));
        queue.send(new Message("User2", "Msg2", 2));
        assertEquals(2, queue.size());
    }
}

