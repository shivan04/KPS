package sr.unasat.kpsfinetracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import sr.unasat.kpsfinetracker.LoginActivity;
import sr.unasat.kpsfinetracker.MainActivity;
import sr.unasat.kpsfinetracker.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MissingPersonsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_missing_persons, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);


    }

}
