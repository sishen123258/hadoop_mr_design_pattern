package designpattern.status.pojo;

import designpattern.status.StatusCandyMachine;
import designpattern.status.inter.Status;

/**
 * Created by Tong on 2016/2/25.
 */
public class NoQuarterStatus implements Status {

    private transient  StatusCandyMachine candyMachine;

    public NoQuarterStatus() {
    }

    public NoQuarterStatus(StatusCandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You inserted a quarter.");
        candyMachine.setStatus(candyMachine.getHasQuarterStatus());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("There is no quarters.");
    }

    @Override
    public void turnCrank() {
        System.out.println("Please insert a quarter before you turned the crank.");
    }

    @Override
    public void dispense() {
        System.out.println("please insert quarter,the you can get a candy.");
    }
}
