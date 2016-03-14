package designpattern.Decorator;

/**
 * Created by Yue on 2015/9/23.
 */
public class CatCoffee extends Beverage {

    public CatCoffee() {
        this.description="I am CatCoffee";
    }

    @Override
    public int cost() {
        return 2;
    }
}
