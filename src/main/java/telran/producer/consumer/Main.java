package telran.producer.consumer;

public class Main {
    private static final int N_MESSAGES = 200;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox evenMsgBox = new SimpleMessageBox();
        MessageBox oddMsgBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, evenMsgBox, oddMsgBox);
        for (int i = 0; i < N_RECEIVERS; i++) {
            MessageBox curMsgBox = sender.getCurMessageBox(i);
            new Receiver(curMsgBox).start();
        }
        sender.start();
        sender.join();
        Thread.sleep(100);
    }
}