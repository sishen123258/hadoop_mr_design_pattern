package designpattern.Decorator;

/**
 * Created by Yue on 2015/9/23.
 */
public class MocaCoffee extends Beverage {

    public MocaCoffee() {
        this.description="I am MocaCoffee";
    }

    @Override
    public int cost() {
        return 1;
    }
}
