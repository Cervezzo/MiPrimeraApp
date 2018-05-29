package com.android.teaching.miprimeraapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.teaching.miprimeraapp.interactors.GameInteractorCallback;
import com.android.teaching.miprimeraapp.interactors.GamesInteractor;
import com.android.teaching.miprimeraapp.interactors.GamesInteractorFirebase;
import com.android.teaching.miprimeraapp.login.view.LoginActivity;
import com.android.teaching.miprimeraapp.view.GameDetailActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private GamesInteractorFirebase gamesInteractorFirebase;
    private MyAdapter myAdapter;
    private ListView listView;
    private MyConnectivityBroadcastReceiver myConnectivityBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // Tenemos permisos
            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            obtenerUbicacion();

        } else {
            // No tenemos permisos
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
        }

        //determinar si tenemos internet o no
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean hasConnectivity = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (hasConnectivity) {
            //region Firebase
            String token = FirebaseInstanceId.getInstance().getToken();
            //PRUEBAS FIREBASE
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase
                    .getReference("device_push_token");
            myRef.setValue(token);
            gamesInteractorFirebase = new GamesInteractorFirebase();
            gamesInteractorFirebase.getGames(new GameInteractorCallback() {
                @Override
                public void onGamesAvailable() {
                    findViewById(R.id.loading).setVisibility(View.GONE);
                    //Aquí, GamesInteractorFirebase ya tiene la lista de juegos

                    myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);
                }
            });//endregion
        } else {
            //mostrar TOAST y esconder loading
            findViewById(R.id.loading).setVisibility(View.GONE);
            Toast.makeText(this, "no hay conexión a internet",
                    Toast.LENGTH_SHORT).show();
        }
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Abrir activity de detalle
                Intent intent = new Intent(ListActivity.this,
                        GameDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //el usuario acepta los permisos
            } else {
                //el usuario NO acepta los permisos
            }
        }
    }

    @SuppressLint("MissingPermission")

    private void obtenerUbicacion(){

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location changed", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);

    }

    @Override
    public void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        myConnectivityBroadcastReceiver = new MyConnectivityBroadcastReceiver();
        registerReceiver(myConnectivityBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myConnectivityBroadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater myInflater = getMenuInflater();
        myInflater.inflate(R.menu.menu_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Open LoginActivity
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater myInflater = getMenuInflater();
        myInflater.inflate(R.menu.delete_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // El usuario ha seleccionado un elemento del menu contextual
        myAdapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gamesInteractorFirebase.getGames().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item, parent, false);

            ImageView icon = rowView.findViewById(R.id.image_view);

            //cargar imágenes!!!!!!
            Glide.with(ListActivity.this).load(gamesInteractorFirebase.getGames().get(position).getIcon()).into(icon);
            //icon.setImageResource(gameIcons.get(position));

            TextView textView = rowView.findViewById(R.id.text_view);
            textView.setText(gamesInteractorFirebase.getGames()
                    .get(position).getName());

            return rowView;
        }
    }
}
