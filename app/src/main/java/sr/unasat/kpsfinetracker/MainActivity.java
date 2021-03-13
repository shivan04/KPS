package sr.unasat.kpsfinetracker;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sr.unasat.kpsfinetracker.fragments.LicensePlateFragment;
import sr.unasat.kpsfinetracker.fragments.MissingPersonsFragment;
import sr.unasat.kpsfinetracker.fragments.MostWantedFragment;

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
}