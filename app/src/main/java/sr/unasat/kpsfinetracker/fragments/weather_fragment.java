package sr.unasat.kpsfinetracker.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import sr.unasat.kpsfinetracker.R;


public class weather_fragment extends Fragment {
    String City = "London";
    //Your Key
    String Key = "2a20d2a71c5a0737f88861d43949d630";

    String url1 = "https://samples.openweathermap.org/data/2.5/weather?q=London&appid=439d4b804bc8187953eb36d2a8c26a02";



    TextView txtCity,txtTime,txtValue,txtValueFeelLike,txtValueHumidity,txtValueVision;

    String nameIcon = "10d";








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_weather_fragment, container, false);

    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);





}

}