package multithread.ProducerConsumer;

/**
 * Created by Yue on 2015/7/23.
 *
 * ����ʢ��
 */
public class Table {

    private final String[] buffer;
    private int counter;
    private int tail; //put�����λ��
    private int head;//get�����λ��

    public Table(int len) {
        this.buffer =new String[len];
        counter=0;
        tail=0;
        head=0;
    }

    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" PUT "+ cake);
        //�����ʱ�����ϵĵ�������ˣ���ô�͵ȴ�
        while (counter>=buffer.length){
            wait();
        }

        buffer[tail]=cake;
        tail=(tail+1)%buffer.length;
        counter++;
        notifyAll();
    }

    public synchronized String getCake() throws InterruptedException {

        //�ж���û�е��������
        while (counter<=0){
            wait();
        }
        String cake = buffer[head];
        counter--;
        head=(head+1)%buffer.length;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+" GET "+cake);
        return cake;
    }

}
