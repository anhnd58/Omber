package app1.ducanh.ducanhvn.omber;
import app1.ducanh.ducanhvn.omber.adapter.GPSTracker;
import app1.ducanh.ducanhvn.omber.adapter.LocationServices;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Outline;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell 3360 on 5/2/2016.
 */
public class MapCustomer extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    private static final float ZOOM_CAMERA = 13.0f;
    private static final double LATITUDE_CAMERA = 21.03844442;
    private static final double LONGGITUDE_CAMERA = 105.78237534;
    private static final LatLng LEFT_BOTTOM_CONNER = new LatLng(21.036921, 105.781066);
    private static final LatLng RIGHT_TOP_CONNER = new LatLng(21.040987, 105.785605);
    private static final LatLng LOCATION_VNU = new LatLng(21.036787, 105.782040);
    private GoogleMap mMap;
    private List<MapMarker> markerList = new ArrayList<>();
    //  use to manage thread
    private Handler handler = null;
    private boolean isSearch = false;
    private Location location = null;
    private Circle circleLocation = null;
    //  marker of location
    //private Marker markerLocation = null;

    SlidingUpPanelLayout slidingUpPanelLayout = null;
    //  handle ponyline to show path
    private Polyline path = null;
    //  intent to get data
    private Intent intent;
    //  get setting data
    private SharedPreferences setting = null;
    private int numberofMarker;
    //  marker when touch
    private Marker markerChoose=null;
    ImageButton butSlidingmakePhone;

    private List<Rider> arrsRider = new ArrayList<Rider>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrsRider.add(new Rider(0, R.drawable.ava_da, "Nguyễn Đức Anh", 21.041798, 105.782565, "8.5", "01655756848", "Đẹp trai, nhiệt tình"));
        arrsRider.add(new Rider(1,R.drawable.ava_gaal, "Louis Van Gaal", 21.036762, 105.782393, "8.5", "01655756848", "thân thiện"));
        arrsRider.add(new Rider(2, R.drawable.ava_pa, "Phan Anh", 21.034609, 105.780548, "8.5", "01654393959", "Đẹp trai, chảnh chọe"));
        arrsRider.add(new Rider(3, R.drawable.ava_cuong, "Nguyễn Viết Cương", 21.036421, 105.779078, "8.5", "0988549694", "Đẹp trai, nhiệt tình"));
        arrsRider.add(new Rider(4, R.drawable.ava_pvc, "Phạm Văn Chính", 21.036341, 105.789131, "8.5", "01655756848", "Đẹp trai, lái lụa"));
        arrsRider.add(new Rider(5, R.drawable.ava_trang, "Trinh Vân Trang", 21.040326, 105.781020, "8.0", "01655756848", "Xinh, thân thiện"));
        arrsRider.add(new Rider(6, R.drawable.ava_cr7, "Cristiano Ronaldo", 21.038747, 105.790373, "8.0", "01694990606", "Chưa đánh giá"));
        arrsRider.add(new Rider(7, R.drawable.ava_user, "G-Dragon", 21.028862, 105.779528, "8.0", "01694932606", "Chưa đánh giá"));
        arrsRider.add(new Rider(8, R.drawable.ava_user, "Lê Thanh Ngọc", 21.025313, 105.788794, "8.0", "01624931106", "Chưa đánh giá"));
        arrsRider.add(new Rider(9, R.drawable.ava_user, "Doãn Thị Hiền", 21.029307, 105.802166, "8.0", "01674235106", "Chưa đánh giá"));

        setContentView(R.layout.activity_main_when_signin_success);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setLogo(R.drawable.logo_omber);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setContentView(R.layout.customer_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        slidingUpPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {

                RelativeLayout header = (RelativeLayout) findViewById(R.id.sliding_panel_header);

                TextView name = (TextView) findViewById(R.id.sliding_panel_txtViewName);
                TextView point = (TextView) findViewById(R.id.sliding_panel_tvPoint);
                //RatingBar rate = (RatingBar) findViewById(R.id.rating);
                if (v > 0) {
                    header.setBackgroundColor(getResources().getColor(R.color.blue));
                    name.setTextColor(getResources().getColor(R.color.white));
                    point.setTextColor(getResources().getColor(R.color.white));

                } else {
                    header.setBackgroundColor(Color.WHITE);
                    name.setTextColor(Color.BLACK);
                    point.setTextColor(getResources().getColor(R.color.button_material_dark));
                }
            }

            @Override
            public void onPanelCollapsed(View view) {
            }

            @Override
            public void onPanelExpanded(View view) {
            }

            @Override
            public void onPanelAnchored(View view) {
            }

            @Override
            public void onPanelHidden(View view) {
            }
        });
        // button goi nguoi lai xe
        butSlidingmakePhone = (ImageButton)findViewById(R.id.sliding_panel_butPhone);
        //update location
        //updateLocation();
        //
        final FloatingActionButton fab_location = (FloatingActionButton) findViewById(R.id.fab_location);
        fab_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                21.040987, 105.785605
//                21.036921, 105.781066
                updateLocation();
//
                /*Location myLocation = null;
                LatLng myLatLng = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(myLatLng)
                        .zoom(14)
                        .tilt(60)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("my location");
                markerOptions.snippet("...");
                markerOptions.position(myLatLng);
                Marker currentMarker = mMap.addMarker(markerOptions);
                currentMarker.showInfoWindow();*/
            }
        });
        fab_location.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Direction md = new Direction();
        /*Document doc = md.getDocument(latlngPhamHung, latlngGiaiPhong, Direction.MODE_DRIVING);
        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(2).color(Color.RED); // Màu và độ rộng
        for(int i = 0 ; i < directionPoint.size() ; i++) {
            rectLine.add(directionPoint.get(i));
        }
        mMap.addPolyline(rectLine);*/
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                slidingUpPanelLayout.setPanelHeight(0);
                markerChoose = null;
                if (markerList.size() > 0) {
                    for (int i = 0; i < markerList.size(); i++) {
                        markerList.get(i).getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(SettingActivity.MENU_COLOR_FLOAT[3]));
                    }
                }
            }
        });
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        addMarker(arrsRider);
//      setting camera
        LatLng posCamera = new LatLng(21.038387, 105.783603);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(posCamera)
                .zoom(14)
                .tilt(60)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                markerChoose = marker;
                for (int i = 0; i < markerList.size(); i++) {
                    if (marker.equals(markerList.get(i).getMarker())) {
                        //Building building = ArrayBuildings.getInstance(MapsActivity.this).getBuildingById(markerList.get(i).getId());
                        Rider rider = arrsRider.get(markerList.get(i).getId());
                        if (rider != null) {
                            ImageView img_rider = (ImageView) findViewById(R.id.sliding_panel_ImgAvarta);
                            img_rider.setImageResource(rider.getImg());

                            TextView name_rider = (TextView) findViewById(R.id.sliding_panel_txtViewName);
                            name_rider.setText(String.valueOf(rider.getName()));
                            TextView point_rider = (TextView) findViewById(R.id.sliding_panel_tvPoint);
                            point_rider.setText("Chấm điểm: " + String.valueOf(rider.getRate()));
                            TextView phone_rider = (TextView) findViewById(R.id.sliding_panel_txtPhone);
                            if (rider.getPhone() != null) {
                                phone_rider.setText(String.valueOf("Số điện thoại : " + rider.getPhone()));
                            } else {
                                phone_rider.setText(String.valueOf("Số điện thoại : Đang cập nhật"));
                            }
                            TextView info = (TextView) findViewById(R.id.sliding_panel_txtInfo);
                            if (rider.getInfo() != null) {
                                info.setText("Thái độ phục vụ: " + String.valueOf(rider.getInfo()));
                            } else {
                                info.setText(String.valueOf("Thái độ phục vụ: Chưa có đánh giá"));
                            }
                        }
                        markerList.get(i).getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(SettingActivity.MENU_COLOR_FLOAT[3]));
                        LatLng posCamera = new LatLng(markerList.get(i).getMarker().getPosition().latitude,
                                markerList.get(i).getMarker().getPosition().longitude);
                        //// test can test
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(posCamera)
                                .zoom(15)
                                .tilt(60)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        slidingUpPanelLayout.setPanelHeight(180);

                        butSlidingmakePhone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ////// do sth
                                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                                alertDialogFragment.show(getFragmentManager(), "alert");
                            }
                        });

                    } /*else {
                        markerList.get(i).getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(SettingActivity.MENU_COLOR_FLOAT[
                                setting.getInt(SettingActivity.KEY_COLOR, 0)]));
                    }*/
                }
                return true;

            }
        });
    }


    /* Ve duong di */
    private void drawPath(String path) {
        try {
            JSONObject jsonObject = new JSONObject(path);
            Log.d("MAPDRAW", jsonObject.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("routes");
            JSONObject routes = jsonArray.getJSONObject(0);
            JSONObject overview_polyline = routes.getJSONObject("overview_polyline");
            String polyline = overview_polyline.getString("points");
            List<LatLng> latLngs = JSONMap.decodePoly(polyline);
            this.path = mMap.addPolyline(new PolylineOptions().addAll(latLngs).width(13).color(Color.parseColor("#05b1fb")).geodesic(true));
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    private class DownloadAndDrawPath extends AsyncTask<Void, Void, String> {
        private String url = null;

        DownloadAndDrawPath(String url) {
            this.url = url;
        }

        protected String doInBackground(Void... paramas) {
            JSONMap jsonMap = new JSONMap();
            String result = jsonMap.getJSONFromURL(url);
            Log.d("MAPDRAW", result);
            return result;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                drawPath(result);
            }
        }
    }

    public void addMarker(List<Rider> riders) {
        if (riders != null) {
            for (int i = 0; i < riders.size(); i++) {
                LatLng latLng = new LatLng(riders.get(i).getLocationX(), riders.get(i).getLocationY());
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(riders.get(i).getName()));
                markerList.add(new MapMarker(riders.get(i).getId(), marker));
            }
        }
    }

    public void deleteMarker() {
        if (markerList.size() > 0) {
            for (int i = 0; i < markerList.size(); i++) {
                markerList.get(i).getMarker().setVisible(false);
            }
            markerList.clear();
        }
    }

    public void focusAMarker(Rider rider) {
        if (rider != null) {
            deleteMarker();
            Location location = new Location("");
            location.setLatitude(rider.getLocationX());
            location.setLongitude(rider.getLocationY());
            //List<Building> buildingList = ArrayBuildings.getInstance(MapsActivity.this).getBuildingsByLocation(numberofMarker, location);
            //List<Rider> riderList
            addMarker(arrsRider);
            LatLng posCamera = new LatLng(location.getLatitude(), location.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(posCamera)
                    .zoom(15)
                    .tilt(60)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            TextView name = (TextView) findViewById(R.id.sliding_panel_txtViewName);
            name.setText(String.valueOf(rider.getName()));
            TextView point = (TextView) findViewById(R.id.sliding_panel_tvPoint);
            point.setText(String.valueOf(rider.getRate()));
            TextView phone = (TextView) findViewById((R.id.sliding_panel_txtPhone));
            phone.setText(String.valueOf(rider.getPhone()));
            TextView info = (TextView) findViewById((R.id.sliding_panel_txtInfo));
            info.setText(String.valueOf(rider.getInfo()));
        }
        slidingUpPanelLayout.setPanelHeight(160);
        if (markerList.size() > 0) {
            for (int k = 0; k < markerList.size(); k++) {
                if (markerList.get(k).getMarker().getPosition().latitude == rider.getLocationX() &&
                        markerList.get(k).getMarker().getPosition().longitude == rider.getLocationY()) {
                    markerList.get(k).getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(SettingActivity.MENU_COLOR_FLOAT[3]));
                } else {
                    markerList.get(k).getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(SettingActivity.MENU_COLOR_FLOAT[0]));
                }
            }
        }

    }

    public void updateLocation() {

        //TextView text = (TextView) findViewById(R.id.texts);
        GPSTracker tracker = new GPSTracker(this);
        if (!tracker.canGetLocation()) {
            tracker.showSettingsAlert();
        } else {

            LatLng myLatLng = new LatLng(tracker.getLatitude(),tracker.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(myLatLng)
                    .zoom(14)
                    .tilt(60)
                    .build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("my location");
            markerOptions.snippet("...");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location));
            markerOptions.position(myLatLng);
            Marker currentMarker = mMap.addMarker(markerOptions);
            currentMarker.showInfoWindow();
        }
    }
    private class AlertDialogFragment extends DialogFragment {
        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Bạn có muốn gọi xe ôm này?");
            // User cannot dismiss dialog by hitting back button
            builder.setCancelable(false);
            // Set up No Button
            builder.setNegativeButton("Không!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.dismiss();
                        }
                    });

            // Set up Yes Button
            builder.setPositiveButton("Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(
                                final DialogInterface dialog, int id) {
                            Intent newIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01655756848"));
                            startActivity(newIntent);
                        }
                    });
            return builder.create();
        }
    }
    private class AlertDialogLogOut extends DialogFragment {
        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Bạn có muốn đăng xuất?");
            // User cannot dismiss dialog by hitting back button
            builder.setCancelable(false);
            // Set up No Button
            builder.setNegativeButton("Không!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.dismiss();
                        }
                    });

            // Set up Yes Button
            builder.setPositiveButton("Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(
                                final DialogInterface dialog, int id) {
                            SignIn.CHECK_SIGNIN = false;
                            Intent intent = new Intent(MapCustomer.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
            return builder.create();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.manage_profile) {
            Intent intent = new Intent(MapCustomer.this, ManageProfile.class);
            startActivity(intent);

        } else if (id == R.id.sign_out) {
            AlertDialogLogOut alertDialogLogOut = new AlertDialogLogOut();
            alertDialogLogOut.show(getFragmentManager(), "alert");
        } else if (id == R.id.feedback) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=")));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=")));
            }
        } else if (id == R.id.help) {
            Intent intent = new Intent(this, Info.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
