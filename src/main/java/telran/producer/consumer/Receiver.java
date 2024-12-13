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
            try {
                String message = messageBox.take();
                int msgNumber = getNumber(message);
                int receiverNumber = getNumber(getName());
                if (isEvenOrOdd(msgNumber, receiverNumber)) {
                    System.out.printf("Thread: %s, message: %s\n", getName(), message);
                } else {
                    messageBox.put(message);
                    sleep(10);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    private boolean isEvenOrOdd(int msgNumber, int receiverNumber) {
        return (msgNumber % 2 == 0 && receiverNumber % 2 == 0) ||
        (msgNumber % 2 != 0 && receiverNumber % 2 != 0); 
    }

    private int getNumber(String message) {
        int res = 0;
        Matcher matcher = Pattern.compile("\\d+").matcher(message);
        if (matcher.find()) {
            res = Integer.parseInt(matcher.group());
        }
        return res;
    }

}
