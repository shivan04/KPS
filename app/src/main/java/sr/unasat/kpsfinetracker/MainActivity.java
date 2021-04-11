package sr.unasat.kpsfinetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sr.unasat.kpsfinetracker.activity.ContactActivity;
import sr.unasat.kpsfinetracker.fragments.LicensePlateFragment;
import sr.unasat.kpsfinetracker.fragments.MissingPersonsFragment;
import sr.unasat.kpsfinetracker.fragments.MostWantedFragment;
import sr.unasat.kpsfinetracker.services.MyService;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer, new LicensePlateFragment()).commit();


    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_license_plate:
                            selectedFragment = new LicensePlateFragment();
                            break;
                        case R.id.nav_missing_persons:
                            selectedFragment = new MissingPersonsFragment();
                            break;
                        case R.id.nav_most_wanted:
                            selectedFragment = new MostWantedFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer, selectedFragment).commit();

                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    //Log out, contact code
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutBtn:
                onLogout();
                break;
            case R.id.contactBtn:
                onContact();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

        private void onLogout () {
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity (intent);
            //service
            Intent intentservice1 = new Intent(this, MyService.class);
            stopService(intentservice1);
            Toast.makeText (this, "You're logged out", Toast.LENGTH_LONG).show ();
            finish ();
        }
    private void onContact() {
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }










}