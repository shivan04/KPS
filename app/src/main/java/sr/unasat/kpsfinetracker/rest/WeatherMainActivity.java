package sr.unasat.kpsfinetracker.rest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sr.unasat.kpsfinetracker.LoginActivity;
import sr.unasat.kpsfinetracker.R;
import sr.unasat.kpsfinetracker.activity.ContactActivity;
import sr.unasat.kpsfinetracker.services.MyService;

public class WeatherMainActivity extends AppCompatActivity {

    Button btn_CityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);

        btn_CityID = (Button)findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = (Button)findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = (Button)findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = (EditText)findViewById(R.id.et_dataInput);
        lv_weatherReport = (ListView)findViewById(R.id.lv_weatherReports);

        final WeatherDataService weatherDataService = new WeatherDataService(WeatherMainActivity.this);

        //Click listener for each button
        btn_CityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //this didn't return anything.
                weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(WeatherMainActivity.this, "Something wrong",  Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(WeatherMainActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(WeatherMainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        // put the entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(WeatherMainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);

                    }
                });



            }
        });
        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.GetCityForecastByNameCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(WeatherMainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        // put the entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(WeatherMainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);

                    }
                });

            }
        });
    }
    //menu

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
            case R.id.weatherBtn:
                onWeather();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onLogout () {
        Intent intent = new Intent (WeatherMainActivity.this, LoginActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity (intent);
        //service
        Intent intentservice1 = new Intent(this, MyService.class);
        stopService(intentservice1);
        Toast.makeText (this, "You're logged out", Toast.LENGTH_LONG).show ();
        finish ();
    }
    private void onContact() {
        Intent intent = new Intent(WeatherMainActivity.this, ContactActivity.class);
        startActivity(intent);
    }
    private void onWeather() {
        Intent intentweather = new Intent(WeatherMainActivity.this, WeatherMainActivity.class);
        startActivity(intentweather);
    }
}