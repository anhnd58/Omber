package app1.ducanh.ducanhvn.omber;

/**
 * Created by Dell 3360 on 5/9/2016.
 */
import com.google.android.gms.maps.model.Marker;

public class MapMarker {
    private Marker marker;
    //  id of building in map.xml
    private int id;

    public MapMarker(int id, Marker marker){
        this.id = id;
        this.marker = marker;
    }

    public int getId() {
        return id;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
