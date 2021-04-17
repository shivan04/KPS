package sr.unasat.kpsfinetracker.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;

import sr.unasat.kpsfinetracker.MainActivity;
import sr.unasat.kpsfinetracker.R;
import sr.unasat.kpsfinetracker.databases.DatabaseHelper;
import sr.unasat.kpsfinetracker.entities.Person;
import sr.unasat.kpsfinetracker.entities.Vehicle;
import sr.unasat.kpsfinetracker.fragments.LicensePlateFragment;

public class LicensePlateDetailsActivity extends AppCompatActivity {

    private EditText lastnameTxt, firstnameTxt, idNumberTxt, emailTxt, phoneTxt, licensePlateNumberTxt;
    private Button updateBtn, deleteBtn;
    private Spinner vehicleTypeSpinner;
    private DatabaseHelper dbHelper;

    private Person person;
    private Vehicle vehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_license_plate_details);
        dbHelper = new DatabaseHelper(LicensePlateDetailsActivity.this);

        lastnameTxt = (EditText) findViewById(R.id.lastname_text);
        firstnameTxt = (EditText) findViewById(R.id.firstname_text);
        idNumberTxt = (EditText) findViewById(R.id.id_number_text);
        emailTxt = (EditText) findViewById(R.id.email_text);
        phoneTxt = (EditText) findViewById(R.id.phone_text);
        licensePlateNumberTxt = (EditText) findViewById(R.id.license_plate_number);

        vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicle_type);
        String vehicleList[] = new String[]{"Car", "Motorcycle", "Other"};
        ArrayAdapter<String> vehicleTypesArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleList);
        vehicleTypesArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        vehicleTypeSpinner.setAdapter(vehicleTypesArrayAdapter);

        updateBtn = (Button) findViewById(R.id.update_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);

        getVehicleInformation();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastname = lastnameTxt.getText().toString();
                String firstname = firstnameTxt.getText().toString();
                String idNumber = idNumberTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String phone = phoneTxt.getText().toString();
                String licensePlateNumber = licensePlateNumberTxt.getText().toString();

                updateInformation(lastname, firstname, idNumber, email, phone, vehicle.getId() ,licensePlateNumber);
                Toast.makeText(getApplicationContext(), "Information has been updated", Toast.LENGTH_SHORT).show();
                backToList();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVehicle(vehicle.getId());
                Toast.makeText(getApplicationContext(), "Vehicle has been deleted", Toast.LENGTH_SHORT).show();
                backToList();
            }
        });
    }

    public void deleteVehicle(int vehicleId){
        dbHelper.deleteLicensePlate(String.valueOf(vehicleId));
    }

    public void getVehicleInformation(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("LICENSE_PLATE_NUMBER");
            Cursor vehicleCursor = dbHelper.getLicensePlate(value);

            if (vehicleCursor.getCount() != 0) {
                while (vehicleCursor.moveToNext()){
                    vehicle = new Vehicle(vehicleCursor.getInt(0), vehicleCursor.getString(1));

                    int personId = vehicleCursor.getInt(2);

                    if (personId > 1) {
                        Cursor personCursor = dbHelper.getPerson(String.valueOf(personId));
            
                        System.out.println(personCursor.getCount());
                        if (personCursor.getCount() != 0) {
                            while (personCursor.moveToNext()) {
                                person = new Person(personCursor.getInt(0), personCursor.getString(1) ,personCursor.getString(2) ,personCursor.getString(3) ,personCursor.getString(4) ,personCursor.getString(5) ,personCursor.getString(6));

                                lastnameTxt.setText(person.getLastname());
                                firstnameTxt.setText(person.getFirstname());
                                idNumberTxt.setText(person.getIdNumber());
                                emailTxt.setText(person.getEmailAddress());
                                phoneTxt.setText(person.getPhoneNumber());
                                licensePlateNumberTxt.setText(vehicle.getLicensePlateNumber());
                            }
                        }
                    } else {
                        licensePlateNumberTxt.setText(vehicle.getLicensePlateNumber());
                    }

                }
            }
        }
    }

    public void updateInformation(String lastname, String firstname, String idNumber, String email, String phone, int vehicleId, String licensePlateNumber) {
        long results;
        ContentValues personContent = new ContentValues();
        personContent.put("lastname", lastname);
        personContent.put("firstname", firstname);
        personContent.put("id_number", idNumber);
        personContent.put("email_address", email);
        personContent.put("phone_number", phone);

        if (person != null && dbHelper.checkPersonExist(String.valueOf(person.getId()))){
            results = dbHelper.updateOneRecord("people", personContent, String.valueOf(person.getId()));
        } else{
            results = dbHelper.insertOneRecord("people", personContent);
        }
        System.out.println(results);

        ContentValues vehicleContent = new ContentValues();
        vehicleContent.put("license_plate_number", licensePlateNumber);
        vehicleContent.put("person_id", results);

        if (dbHelper.checkVehicleExist(String.valueOf(vehicle.getId()))){
            dbHelper.updateOneRecord("vehicles", vehicleContent, String.valueOf(vehicle.getId()));
        } else {
           dbHelper.insertOneRecord("vehicles", vehicleContent);
        }
    }


    public void backToList(){
        Intent intent = new Intent(LicensePlateDetailsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

