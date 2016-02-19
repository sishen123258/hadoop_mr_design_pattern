package test;
public class Test {
    public static void main(String[] args) {
        B b=new B();
        b.method1();
    }
}
abstract class A{
    public void method1(){
        System.out.println(this);
    }
    public abstract void method2();
    @Override
    public String toString() {
        return "A.toString";
    }
}
class B extends A{
    @Override
    public void method2() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "B";
    }
}