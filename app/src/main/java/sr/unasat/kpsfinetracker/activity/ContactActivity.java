package sr.unasat.kpsfinetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import sr.unasat.kpsfinetracker.LoginActivity;
import sr.unasat.kpsfinetracker.MainActivity;
import sr.unasat.kpsfinetracker.R;
import sr.unasat.kpsfinetracker.rest.WeatherMainActivity;
import sr.unasat.kpsfinetracker.services.MyService;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_contact);
    }

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
        Intent intent = new Intent (ContactActivity.this, LoginActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity (intent);
        //service
        Intent intentservice1 = new Intent(this, MyService.class);
        stopService(intentservice1);
        Toast.makeText (this, "You're logged out", Toast.LENGTH_LONG).show ();
        finish ();
    }
    private void onContact() {
        Intent intent = new Intent(ContactActivity.this, ContactActivity.class);
        startActivity(intent);
    }
    private void onWeather() {
        Intent intentweather = new Intent(ContactActivity.this, WeatherMainActivity.class);
        startActivity(intentweather);
    }




}