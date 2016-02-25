package designpattern.status;

import designpattern.status.inter.Status;
import designpattern.status.pojo.HasQuarterStatus;
import designpattern.status.pojo.NoQuarterStatus;
import designpattern.status.pojo.SoldOutStatus;
import designpattern.status.pojo.SoldStatus;

/**
 * Created by Tong on 2016/2/25.
 */
public class StatusCandyMachine{
    private Status soldOutStatus;
    private Status soldStatus;
    private Status hasQuarterStatus;
    private Status noQuarterStatus;

    private Status status=soldStatus;
    private int count=0;

    public StatusCandyMachine() {
        this.soldOutStatus = new SoldOutStatus();
        this.soldStatus = new SoldStatus();
        this.hasQuarterStatus = new HasQuarterStatus();
        this.noQuarterStatus = new NoQuarterStatus();

        status=noQuarterStatus;
    }


    public void insertQuarter() {
        status.insertQuarter();
    }

    public void ejectQuarter() {
        status.ejectQuarter();
    }

    public void turnCrank() {
        status.turnCrank();
        status.dispense();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void releaseBall(){
        System.out.println("The candy is outing!");
        if(count>0){
            this.count--;
        }
    }
}
