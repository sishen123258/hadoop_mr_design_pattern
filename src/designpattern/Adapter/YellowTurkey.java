package designpattern.Adapter;

/**
 * Created by Yue on 2015/9/28.
 */
public class YellowTurkey implements Turkey {
    @Override
    public void scream() {
        System.out.println("YellowTurkey.scream");
    }

    @Override
    public void fly() {
        System.out.println("YellowTurkey.fly");
    }
}
