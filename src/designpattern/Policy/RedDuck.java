package designpattern.Policy;

/**
 * Created by Yue on 2015/9/22.
 */
public class RedDuck implements Flyable {
    @Override
    public void fly() {
        System.out.printf("flying");
    }

}
