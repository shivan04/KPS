package sr.unasat.kpsfinetracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sr.unasat.kpsfinetracker.MainActivity;
import sr.unasat.kpsfinetracker.R;

public class LicencePlateFragment extends Fragment {
    private static final String TAG = "LicencePlateFragment";
    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    FloatingActionButton fab1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.licenceplatefragment_layout, container, false);
        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavfrag1);
        btnNavFrag2 = (Button) view.findViewById(R.id.btnNavfrag2);
        btnNavFrag3 = (Button) view.findViewById(R.id.btnNavfrag3);

        fab1 = (FloatingActionButton) view.findViewById (R.id.fab1);



        Log.d(TAG, "onCreateView: started.");

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Licence plate search", Toast.LENGTH_SHORT).show();
                //navigate to fragment method called

                ((MainActivity)getActivity()).setViewPager(0);

            }
        });



        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Most wanted list", Toast.LENGTH_SHORT).show();
                //navigate to fragment method called
                ((MainActivity)getActivity()).setViewPager(1);

            }
        });
        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Missing persons list", Toast.LENGTH_SHORT).show();
                //navigate to fragment method called
                ((MainActivity) getActivity()).setViewPager(2);

            }
        });


        return view;
    }

}
