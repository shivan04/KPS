package sr.unasat.kpsfinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sr.unasat.kpsfinetracker.activity.ContactActivity;
import sr.unasat.kpsfinetracker.activity.ProfileActivity;
import sr.unasat.kpsfinetracker.fragments.LicencePlateFragment;
import sr.unasat.kpsfinetracker.fragments.MissingPersonsListFragment;
import sr.unasat.kpsfinetracker.fragments.MostWantedListFragment;
import sr.unasat.kpsfinetracker.fragments.SectionsStatePagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    FloatingActionButton fabmain;
    FloatingActionButton fabMain;
    FloatingActionButton fab2;
    FloatingActionButton fab1;
    FloatingActionButton fab3;
    Float translationY = 100f;
    boolean isMenuOpen = true;
    OvershootInterpolator interpolator = new OvershootInterpolator ();




    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "onCreate: Started.");
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        //setup the pager
        setupViewPager(mViewPager);

        initFabMenu ();

    }
    private void initFabMenu() {
        fabMain = findViewById(R.id.fabmain);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);

        fab1.setAlpha(0f);
        fab2.setAlpha(0f);
        fab3.setAlpha(0f);

        fab1.setTranslationY(translationY);
        fab2.setTranslationY(translationY);
        fab3.setTranslationY(translationY);

        fabMain.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

        fab1.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();


    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fab1.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

    }




    @Override
    public void onClick(View view) {



        switch (view.getId()) {
            case R.id.fabmain:
                Log.i(TAG, "onClick: fab main");
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fab1:
                Log.i(TAG, "onClick: fab one");

                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fab2:
                Log.i(TAG, "onClick: fab two");
                if (isMenuOpen){
                    closeMenu ();
                } else {
                    openMenu ();
                }
                break;
            case R.id.fab3:
                Log.i(TAG, "onClick: fab three");
                if (isMenuOpen){
                    closeMenu ();
                } else {
                    openMenu ();
                }
                break;
        }

    }

    //frag
    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LicencePlateFragment(), "Fragment1");
        adapter.addFragment(new MostWantedListFragment(), "Fragment2");
        adapter.addFragment(new MissingPersonsListFragment(), "Fragment3");
        viewPager.setAdapter(adapter);

    }
    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main1, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutBtn:
                onLogout();
                break;
            case R.id.profileBtn:
                onProfile();
                break;
            case R.id.contactBtn:
                onContact();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onLogout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this,"You're logged out", Toast.LENGTH_LONG).show();
        finish();
    }
    private void onProfile() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    private void onContact() {
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }


}