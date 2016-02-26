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

    private String location;

    public StatusCandyMachine(int count) {
        this.soldOutStatus = new SoldOutStatus(this);
        this.soldStatus = new SoldStatus(this);
        this.hasQuarterStatus = new HasQuarterStatus(this);
        this.noQuarterStatus = new NoQuarterStatus(this);
        if(count>0)
            status=noQuarterStatus;
        this.count=count;
    }

    public StatusCandyMachine(int count, String location) {
        this(count);
        this.location=location;
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

    public String getLocation() {
        return location;
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

    public Status getSoldOutStatus() {
        return soldOutStatus;
    }

    public Status getSoldStatus() {
        return soldStatus;
    }

    public Status getHasQuarterStatus() {
        return hasQuarterStatus;
    }

    public Status getNoQuarterStatus() {
        return noQuarterStatus;
    }

    public int getCount() {
        return count;
    }
}
