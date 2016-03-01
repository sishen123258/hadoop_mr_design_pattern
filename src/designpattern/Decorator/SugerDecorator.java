package designpattern.Decorator;

/**
 * Created by Yue on 2015/9/23.
 */
public class SugerDecorator extends ConcreteDecorator {

    private Beverage beverage;

    public SugerDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return 5+this.beverage.cost();
    }
}
