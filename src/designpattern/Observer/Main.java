package designpattern.Observer;

/**
 * Created by Yue on 2015/9/23.
 */
public class Main {

    public static void main(String[] args) {
        //主题，继承Observable
        WeatherData weatherData=new WeatherData(100,200,300);
        //观察者1
        GeneralDisplay generalDisplay=new GeneralDisplay();
        //观察者2
        StaticalDisplay staticalDisplay=new StaticalDisplay();
        //注册
        weatherData.addObserver(generalDisplay);
        //注册
        weatherData.addObserver(staticalDisplay);
        //设置状态变更，由于Observable的setChanged是protected，只能有其子类或者包类调用
        weatherData.setChanged();
        //通知所有的观察者，jdk会自己调用observer的update方法
        weatherData.notifyObservers();
    }



}
