package designpattern.Adapter;

/**
 * Created by Yue on 2015/9/28.
 *
 *
 * 实现Duck接口将火鸡变为鸭子
 */
public class Adapter implements Duck{

    private Turkey turkey;

    public Adapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void gua() {
        turkey.scream();
    }

    @Override
    public void fly() {
        turkey.fly();
    }
}
