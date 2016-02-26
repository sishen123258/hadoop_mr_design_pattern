package designpattern.status.pojo;

import designpattern.status.StatusCandyMachine;
import designpattern.status.inter.Status;

/**
 * Created by Tong on 2016/2/25.
 */
public class HasQuarterStatus implements Status {

    private transient  StatusCandyMachine candyMachine;

    public HasQuarterStatus() {
    }

    public HasQuarterStatus(StatusCandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't add more quarters.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Please wait,the quarter is outing.");
        candyMachine.setStatus(candyMachine.getNoQuarterStatus());
    }

    @Override
    public void turnCrank() {
        System.out.println("Please wait,the candy is outing.");
        this.dispense();
    }

    @Override
    public void dispense() {
        System.out.println("The candy is outed.");
        candyMachine.setStatus(candyMachine.getSoldStatus());
    }
}
