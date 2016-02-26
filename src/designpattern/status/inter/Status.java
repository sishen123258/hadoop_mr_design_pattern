package designpattern.status.inter;

import java.io.Serializable;

/**
 * Created by Tong on 2016/2/25.
 */
public interface Status extends Serializable{

    public void insertQuarter();
    public void ejectQuarter();
    public void turnCrank();
    public void dispense();

}
