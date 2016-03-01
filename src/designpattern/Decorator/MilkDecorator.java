package designpattern.Decorator;

/**
 * Created by Yue on 2015/9/23.
 */
public class MilkDecorator extends ConcreteDecorator{

    private Beverage beverage;

    public MilkDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return 6+this.beverage.cost();
    }
}
