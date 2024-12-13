package telran.producer.consumer;

public class Receiver extends Thread {
    private MessageBox messageBox;
    private boolean running;

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
        this.running = true;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String message = messageBox.take();
                System.out.printf("Thread: %s, message: %s\n", getName(), message);
            } catch (InterruptedException e) {

            }
        }
    }

    public void shutDown() {
        running = false;
        interrupt();
    }
    
}
