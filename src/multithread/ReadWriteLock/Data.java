package multithread.ReadWriteLock;


/**
 * Created by Yue on 2015/7/23.
 */
public class Data {

    private final MyReadWriteLock lock=new MyReadWriteLock();
    private final char[] buffer;


    public Data(int size) {
        this.buffer =new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i]='*';
        }
    }


    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        }finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        }finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {

        for (int i = 0; i < buffer.length; i++) {
            buffer[i]=c;
            slowly();
        }

    }

    private char[] doRead() {
        char[] newBuf=new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuf[i]=buffer[i];
        }
        slowly();
        return newBuf;
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
