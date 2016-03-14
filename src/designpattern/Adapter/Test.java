package designpattern.Adapter;

/**
 * Created by Yue on 2015/9/28.
 */
public class Test {

    public static void main(String[] args) {
        Duck duck=new RedDuck();
        duck.fly();duck.gua();
        Turkey turkey=new YellowTurkey();
        turkey.fly();turkey.scream();

        //让火鸡变成鸭子
        Duck duck1=new Adapter(turkey);
        duck1.fly();duck1.gua();

    }

}
