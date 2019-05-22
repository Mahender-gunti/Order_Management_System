package com.nav.ordermanagementsystem.ui.table_view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.nav.ordermanagementsystem.R;
import com.nav.ordermanagementsystem.util.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MainActivityView, DialogueInterface, LocationListener {
    private ListAdapter listAdapter;
    private List<ListModel> listListModels;
    private final int REQUEST_CODE_EDIT = 101;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private ConstraintLayout coordinatorLayout;
    private String address;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        coordinatorLayout = findViewById(R.id.lyt_main);
        recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);
        checkForPermission();
        if (address == null) {
            getLocation();
        }

        listListModels = new ArrayList<>();
        ListModel listModel = new ListModel();
        listModel.setOrderNumber("1");
        listModel.setOrderDueDate("10-02-2019");
        listModel.setCustomerBuyerName("Mahender");
        listModel.setCustomerAddress("H.NO: 33-2/3 Hyderabad");
        listModel.setCustomerPhone("9999999999");
        listModel.setCustomerOrderTotal("20000");
        listModel.setCustomerGPSAddress(address);
        listListModels.add(listModel);

        listAdapter = new ListAdapter(listListModels, this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void deleteItem(ListModel listModel) {
        listListModels.remove(listModel);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void editItem(ListModel listModel, int index) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(AppConstants.PERSON_INTENT_EDIT, true);
        intent.putExtra(AppConstants.PERSON_INTENT_INDEX, index);
        intent.putExtra(AppConstants.PERSON_INTENT_OBJECT, listModel);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    public void onC1ickItem(ListModel listModel, int index) {
        ModifyDialogue instance = ModifyDialogue.getInstance();
        instance.init(this, this, listModel, index);
        instance.showConfirmDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                boolean isEdit = data.getBooleanExtra(AppConstants.PERSON_INTENT_EDIT, false);
                ListModel listModel = data.getParcelableExtra(AppConstants.PERSON_INTENT_OBJECT);
                if (isEdit) {
                    int index = data.getIntExtra(AppConstants.PERSON_INTENT_INDEX, -1);
                    if (index == -1) {
                        return;
                    }
                    listListModels.set(index, listModel);
                    listAdapter.notifyDataSetChanged();
                } else {
                    getLocation();
                    listModel.setCustomerGPSAddress(address);
                    listListModels.add(listModel);
                    listAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create_new) {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(AppConstants.PERSON_INTENT_EDIT, false);
            startActivityForResult(intent, REQUEST_CODE_EDIT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLanguageSelected(Dialog dialog) {
        dialog.dismiss();
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void checkForPermission() {
        int permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                createSnackbar("Location Permission Required!!", "Try Again");
            }
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);

        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale
                    (MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (!showRationale) {

                createSettingsSnackbar("Camera Permission Required!!");
            } else {
                createSnackbar("Camera Permission Required!!", "Try Again");
            }

        }

    }

    private void createSnackbar(String message, String action) {
        Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.
                                requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
                    }
                })
                .show();
    }

    private void createSettingsSnackbar(String message) {
        Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("SETTINGS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).show();

    }

    void getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address = addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}