package designpattern.Observer;

import java.util.Observable;

/**
 * Created by Yue on 2015/9/23.
 */
public class WeatherData extends Observable {

    private int temperature;
    private int humidity;
    private int pressure;

    public WeatherData(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
