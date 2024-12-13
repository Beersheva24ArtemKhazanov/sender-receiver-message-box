package telran.producer.consumer;

import java.util.Arrays;

public class Main {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBox);
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < receivers.length; i++) {
            receivers[i] = new Receiver(messageBox);
            receivers[i].start();
        }

        sender.start();
        sender.join();
        Arrays.stream(receivers).forEach(Receiver::shutDown);
    }
}