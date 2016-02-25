package designpattern.status;

/**
 * 当需要添加新的状态的时候，就需要更改现在有的代码，为了封装变化的部分，采用了接口，加状态类的设计模式。
 */
public class CandyMachine {

    private static String SOLD_OUT = "SOLD_OUT";
    private static String NO_QUARTER = "NO_QUARTER";
    private static String HAS_QUARTER = "HAS_QUARTER";
    private static String SOLD = "SOLD";

    private String status = SOLD_OUT;
    private int count = 0;

    public CandyMachine(int count) {
        this.count = count;
        if (this.count > 0) {
            this.status=NO_QUARTER;
        }
    }

    public void insertQuarter(){
        if(status==HAS_QUARTER){
            System.out.println("You can't insert more quarter.");
        }else if (status==NO_QUARTER){
            this.status=HAS_QUARTER;
            System.out.println("You inserted a quarter.");
        }else if (status==SOLD_OUT){
            System.out.println("Candy is sold out");
        }else if (status==SOLD){
            System.out.println("Pleasing waiting , candy is making!");
        }
    }

    public void ejectQuarter(){
        if(status==HAS_QUARTER){
            this.status=NO_QUARTER;
            System.out.println("Quarter returned.");
        }else if (NO_QUARTER == status) {
            System.out.println("You haven't inserted quarter.");
        }else if (status==SOLD_OUT){
            System.out.println("You can't eject.You haven't inserted quarter yet.");
        }else if(status==SOLD){
            System.out.println("Sourry , you have already turned the crank.");
        }
    }

    public void turnCrank(){
        if(status==SOLD_OUT){
            System.out.println("You turned the crank,but there is no candy.");
        }else if (status==SOLD) {
            System.out.println("You have turn crank once before, please insert another quarter.");
        }else if (status==NO_QUARTER){
            System.out.println("There is no quarters.");
        }else if(status==HAS_QUARTER){
            status=SOLD;
            dispense();
        }
    }

    private void dispense() {
        if (status==SOLD){
            System.out.println("The candy is out.");
            count--;
            if (count>0){
                status=NO_QUARTER;
            }else {
                status=SOLD_OUT;
            }
        }else if(status==NO_QUARTER){
            System.out.println("You need a pay first.");
        }else if(status==HAS_QUARTER){
            System.out.println("No candy dispense.");
        }else if(status==SOLD_OUT){
            System.out.println("No candy dispense.");
        }
    }

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }
}
