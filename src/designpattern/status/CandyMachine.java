package designpattern.status;

/**
 * Created by Tong on 2016/2/25.
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

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }
}
