package telran.producer.consumer;

public class Sender extends Thread {
    private int nMessages;
    private MessageBox evenMsgBox;
    private MessageBox oddMsgBox;

    public Sender(int nMessages, MessageBox evenMsgBox, MessageBox oddMsgBox) {
        this.nMessages = nMessages;
        this.evenMsgBox = evenMsgBox;
        this.oddMsgBox = oddMsgBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            MessageBox curMsgBox = getCurMessageBox(i);
            try {
                curMsgBox.put("Message" + (i + 1));
            } catch (InterruptedException e) {

            }
        }
    }

    public MessageBox getCurMessageBox(int nMessage) {
        return (nMessage + 1) % 2 == 0 ? evenMsgBox : oddMsgBox;
    }
}
