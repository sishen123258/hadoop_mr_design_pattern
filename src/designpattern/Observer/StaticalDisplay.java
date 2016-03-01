package designpattern.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yue on 2015/9/23.
 */
public class StaticalDisplay implements Observer,DisplayElem {
    private int temperature;
    private int humidity;
    private int pressure;

    @Override
    public void display() {
        System.out.println("StaticalDisplay.update"+this.toString());
    }

    @Override
    public void update(Observable o, Object arg) {
        this.temperature=((WeatherData)o).getTemperature();
        this.humidity=((WeatherData)o).getHumidity();
        this.pressure=((WeatherData)o).getPressure();
        this.display();

    }

    @Override
    public String toString() {
        return "StaticalDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
