package designpattern.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yue on 2015/9/23.
 */
public class GeneralDisplay implements Observer,DisplayElem{

    private Observable observable;

    private int temperature;
    private int humidity;
    private int pressure;

    @Override
    public void update(Observable o, Object arg) {
        this.temperature=((WeatherData)o).getTemperature();
        this.humidity=((WeatherData)o).getHumidity();
        this.pressure=((WeatherData)o).getPressure();
        this.display();
    }


    @Override
    public void display() {
        System.out.println("GeneralDisplay.display"+this.toString());
    }

    @Override
    public String toString() {
        return "GeneralDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
