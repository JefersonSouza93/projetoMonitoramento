package com.projetoteste.labsoft.projetoteste1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    private Button find;

    private static final int MY_PERMISSIONS_REQUEST_CODE = 11;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 10;
    private Location mLastLocation;

    double latitude, longitude;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private static int UPDATE_INTERVAL = 5000;
    private static int FASTEST_INTERVAL = 3000;
    private static int DISPLACEMENT = 10;

    HashMap<Marker, Market> mapMarket = new HashMap<Marker, Market>();

    Marker myCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setUpLocation();
        find = findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double northLat = mMap.getProjection().getVisibleRegion().latLngBounds.northeast.latitude;
                double southLat = mMap.getProjection().getVisibleRegion().latLngBounds.southwest.latitude;
                double eastLng = mMap.getProjection().getVisibleRegion().latLngBounds.northeast.longitude;
                double westLng = mMap.getProjection().getVisibleRegion().latLngBounds.southwest.longitude;
                mMap.clear();
                displayLocation(false);
                try {
                    JSONArray mercados = getMercados(northLat,southLat, eastLng, westLng);
                    for (int i = 0; i < mercados.length(); i++) {
                        JSONObject item = mercados.getJSONObject(i);
                        LatLng latLng = new LatLng(item.getDouble("latitude"), item.getDouble("longitude"));
                        double lotacaoPorcentagem = item.getDouble("lotacao") * 100 / item.getDouble("maximo");
                        Marker marker;
                        if (lotacaoPorcentagem <= 30) {
                            marker = mMap.addMarker(new MarkerOptions().position(latLng).title(item.getString("nome")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        } else if (lotacaoPorcentagem <= 60) {
                            marker = mMap.addMarker(new MarkerOptions().position(latLng).title(item.getString("nome")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        } else {
                            marker = mMap.addMarker(new MarkerOptions().position(latLng).title(item.getString("nome")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        }
                        Market market = new Market();
                        market.setId(item.getLong("id"));
                        market.setLotacao(item.getInt("lotacao"));
                        market.setMaximo(item.getInt("maximo"));
                        market.setNome(item.getString("nome"));
                        mapMarket.put(marker,market);
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                Market market = mapMarket.get(marker);
                                if(market!=null){
                                Intent intent = new Intent(MapsActivity.this, DetailsActivity.class);
                                intent.putExtra("nomeMercado", market.getNome());
                                intent.putExtra("lotacao", market.getLotacao());
                                intent.putExtra("maximo", market.getMaximo());
                                intent.putExtra("id", market.getId());
                                startActivity(intent);
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
    }

    private void setUpLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestRuntimePermision();
        } else if (checkPlayServices()) {
            buildGoogleApiClient();
            createLocationRequest();
            displayLocation(true);
        }

    }

    private void displayLocation(boolean zoomAnimation) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null){
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();

            //add Marker
            if (myCurrent != null){
                myCurrent.remove();
            }
            myCurrent=mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Sua posição").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            if (zoomAnimation) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18.0f));
                double northLat = mMap.getProjection().getVisibleRegion().latLngBounds.northeast.latitude;
                double southLat = mMap.getProjection().getVisibleRegion().latLngBounds.southwest.latitude;
                double eastLng = mMap.getProjection().getVisibleRegion().latLngBounds.northeast.longitude;
                double westLng = mMap.getProjection().getVisibleRegion().latLngBounds.southwest.longitude;
                try {
                    JSONArray mercados = getMercados(northLat,southLat, eastLng, westLng);
                    for (int i = 0; i < mercados.length(); i++) {
                        JSONObject item = mercados.getJSONObject(i);
                        LatLng latLng = new LatLng(item.getDouble("latitude"), item.getDouble("longitude"));
                        double lotacaoPorcentagem = item.getDouble("lotacao") * 100 / item.getDouble("maximo");
                        if (lotacaoPorcentagem <= 30) {
                            mMap.addMarker(new MarkerOptions().position(latLng).title(item.getString("nome")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).snippet("TESTE"));
                        } else if (lotacaoPorcentagem <= 60) {
                            mMap.addMarker(new MarkerOptions().position(latLng).title(item.getString("nome")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        } else {
                            mMap.addMarker(new MarkerOptions().position(latLng).title(item.getString("nome")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        }
                    }
                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Market market = mapMarket.get(marker);
                            if(market!=null){
                                Intent intent = new Intent(MapsActivity.this, DetailsActivity.class);
                                intent.putExtra("nomeMercado", market.getNome());
                                intent.putExtra("lotacao", market.getLotacao());
                                intent.putExtra("maximo", market.getMaximo());
                                startActivity(intent);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createLocationRequest() {
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    protected void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();;
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(this, "This device can't support", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (checkPlayServices()) {
                        buildGoogleApiClient();
                        createLocationRequest();
                        displayLocation(true);
                    }
                }
                break;
        }
    }

    private void requestRuntimePermision() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_CODE);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation(true);
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
        displayLocation(false);
    }

    public JSONArray getMercados(double latitude, double longitude) throws JSONException{
        JSONArray mercados = new JSONArray();
        for (int i = 0; i< 5; i++){
           JSONObject mercado = new JSONObject();
           String nome = "Supermercado ";
           Random rn = new Random();
           int randMercado = rn.nextInt(3) + 1;
           if (randMercado == 1) {
               nome += "Extra";
           } else if (randMercado == 2){
               nome += "Carrefour";
           } else if (randMercado == 3){
               nome += "Pão de Açucar";
           }
           mercado.put("nome",nome);
           int randMaximo = rn.nextInt(2001) + 1000;
           int randPorcentagem = rn.nextInt(101);
           double lotacao = (((double) randPorcentagem)/100)*randMaximo;
           mercado.put("maximo",randMaximo);
           mercado.put("lotacao", lotacao);
           int latitudeMercado = rn.nextInt(401);
           int longitudeMercado = rn.nextInt(401);
           mercado.put("longitude",longitude - 0.02 + (double) longitudeMercado/10000);
           mercado.put("latitude",latitude - 0.02 + (double) latitudeMercado/10000);
           mercados.put(mercado);
        }
        return mercados;
    }

    public JSONArray getMercados(double northLat, double southLat, double eastLng, double westLng) throws JSONException{
        JSONArray mercados = new JSONArray();
        for (int i = 0; i< 5; i++){
            JSONObject mercado = new JSONObject();
            String nome = "Supermercado ";
            Random rn = new Random();
            int randMercado = rn.nextInt(3) + 1;
            if (randMercado == 1) {
                nome += "Extra";
            } else if (randMercado == 2){
                nome += "Carrefour";
            } else if (randMercado == 3){
                nome += "Pão de Açucar";
            }
            mercado.put("nome",nome);
            int randMaximo = rn.nextInt(2001) + 1000;
            int randPorcentagem = rn.nextInt(101);
            double lotacao = (((double) randPorcentagem)/100)*randMaximo;
            mercado.put("maximo",randMaximo);
            mercado.put("lotacao", lotacao);
            double randomLat = southLat + (northLat - southLat) * rn.nextDouble();
            double randomLng = westLng + (eastLng - westLng) * rn.nextDouble();
            mercado.put("latitude",randomLat);
            mercado.put("longitude",randomLng);
            mercado.put("id", rn.nextLong());
            mercados.put(mercado);
        }
        return mercados;
    }

    private class Market {
        private Long id;
        private String nome;
        private int lotacao;
        private int maximo;

        public int getMaximo() {
            return maximo;
        }

        public void setMaximo(int maximo) {
            this.maximo = maximo;
        }

        public int getLotacao() {
            return lotacao;
        }

        public void setLotacao(int lotacao) {
            this.lotacao = lotacao;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
