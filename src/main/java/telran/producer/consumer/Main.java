package telran.producer.consumer;

public class Main {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox evenMsgBox = new SimpleMessageBox();
        MessageBox oddMsgBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, evenMsgBox, oddMsgBox);
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver(null);
            if (getNumberOfReceiver(receivers[i]) % 2 == 0) {
                receivers[i].setMessageBox(evenMsgBox);
            } else {
                receivers[i].setMessageBox(oddMsgBox);
            }
            receivers[i].start();
        }
        sender.start();
        sender.join();
        Thread.sleep(100);
    }

    private static int getNumberOfReceiver(Receiver receiver) {
        return Integer.parseInt(receiver.getName().replaceAll("\\D", ""));
    }
}