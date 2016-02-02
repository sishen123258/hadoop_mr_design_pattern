package storm.trident.bean;

import java.io.Serializable;

/**
 * Created by Tong on 2016/2/2.
 */
public class DiagnosisEvent implements Serializable{

    public double lat;
    public double lng;
    public long time;
    public String diag;

    public DiagnosisEvent(double lat, double lng, long time, String diag) {
        this.lat = lat;
        this.lng = lng;
        this.time = time;
        this.diag = diag;
    }

}
