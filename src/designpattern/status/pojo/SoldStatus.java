package designpattern.status.pojo;

import designpattern.status.StatusCandyMachine;
import designpattern.status.inter.Status;

/**
 * Created by Tong on 2016/2/25.
 */
public class SoldStatus implements Status {

    private transient  StatusCandyMachine candyMachine;

    public SoldStatus() {
    }

    public SoldStatus(StatusCandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait,we are already give you a candy.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry,you already get the candy.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You should not turn the crank twice.");
    }

    @Override
    public void dispense() {
        candyMachine.releaseBall();
        int count = candyMachine.getCount();
        if(count>0){
            candyMachine.setStatus(candyMachine.getNoQuarterStatus());
        }else {
            System.out.println("Oops,candy is sold out.");
            candyMachine.setStatus(candyMachine.getSoldOutStatus());
        }
    }
}
