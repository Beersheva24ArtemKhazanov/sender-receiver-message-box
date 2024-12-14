package telran.producer.consumer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receiver extends Thread {
    private MessageBox messageBox;

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
        setDaemon(true);
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (true) {
            String message;
            try {
                message = messageBox.take();
                System.out.printf("Thread: %s, message: %s\n", getName(), message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
