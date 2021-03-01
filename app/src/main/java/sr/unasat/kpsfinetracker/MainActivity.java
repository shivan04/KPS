package sr.unasat.kpsfinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import sr.unasat.kpsfinetracker.fragments.LicencePlateFragment;
import sr.unasat.kpsfinetracker.fragments.MissingPersonsListFragment;
import sr.unasat.kpsfinetracker.fragments.MostWantedListFragment;
import sr.unasat.kpsfinetracker.fragments.SectionsStatePagerAdapter;

public class MainActivity extends AppCompatActivity {
    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

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

    }
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
}