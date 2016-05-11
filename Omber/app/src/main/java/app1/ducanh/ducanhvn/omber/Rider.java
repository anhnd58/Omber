package app1.ducanh.ducanhvn.omber;

/**
 * Created by Dell 3360 on 5/9/2016.
 */
public class Rider {
    private String name, rate, phone, info;
    int id;
    double locationX, locationY;
    public Rider(int id, String name, double locationX, double locationY, String rate, String phone, String info){
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.phone = phone;
        this.info = info;
        this.locationX =locationX;
        this.locationY = locationY;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }

    public String getPhone() {
        return phone;
    }

    public String getInfo() {
        return info;
    }

    public int getId() {
        return id;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }
}
