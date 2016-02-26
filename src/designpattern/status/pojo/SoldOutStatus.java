package designpattern.status.pojo;

import designpattern.status.StatusCandyMachine;
import designpattern.status.inter.Status;

/**
 * Created by Tong on 2016/2/25.
 */
public class SoldOutStatus implements Status {

    //transient这样就会告诉jvm在序列化该类的时候，不会序列化对象StatusCandyMachine
    private transient StatusCandyMachine candyMachine;

    public SoldOutStatus() {
    }

    public SoldOutStatus(StatusCandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("There is no candy,you can eject your quarter.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("The quarter is ejecting.");
    }

    @Override
    public void turnCrank() {
        System.out.println("There is no quarter,please don't turn crank.");
    }

    @Override
    public void dispense() {
        System.out.println("There is no candy to dispense.");
    }
}
