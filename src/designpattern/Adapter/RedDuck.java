package designpattern.Adapter;

/**
 * Created by Yue on 2015/9/28.
 */
public class RedDuck implements Duck {
    @Override
    public void gua() {
        System.out.println("RedDuck.gua");
    }

    @Override
    public void fly() {
        System.out.println("RedDuck.fly");
    }
}
