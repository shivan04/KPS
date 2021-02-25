package sr.unasat.kpsfinetracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener, OnItemSelectedListener {
    private EditText lastnameField;
    private EditText firstnameField;
    private EditText birthdatefield;
    private EditText emailField;
    private EditText phoneField;
    private EditText policeField;
    private EditText regionField;
    private EditText districtField;
    private Spinner subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        lastnameField = (EditText) findViewById(R.id.lastname_text);
        firstnameField = (EditText) findViewById(R.id.firstname_text);
        birthdatefield = (EditText) findViewById(R.id.date_text);
        emailField = (EditText) findViewById(R.id.email_text);
        phoneField = (EditText) findViewById(R.id.phone_text);
        policeField = (EditText) findViewById(R.id.policestation_text);
        regionField = (EditText) findViewById(R.id.region_text);
        districtField = (EditText) findViewById(R.id.district_text);

        subject = (Spinner) findViewById(R.id.option_spinner);
        String subjects[] = new String[]{"Select subject", "Register", "Reset Password", "Reset Username"};
        subject.setOnItemSelectedListener(this);
        ArrayAdapter<String> sa = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, subjects);
        sa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        subject.setAdapter(sa);
        ;


        final Button buttonSend = (Button) findViewById(R.id.register_button1);
        buttonSend.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (lastnameField.getText().toString().length() == 0) {
            lastnameField.setError("Fill in your lastname");
        } else if (firstnameField.getText().toString().length() == 0) {
            firstnameField.setError("Fill in your firstname");
        } else if (birthdatefield.getText().toString().length()==0) {
            birthdatefield.setError("Fill in your birthdate (ex. 01-01-2021)");
        } else if (emailField.getText().toString().length() == 0) {
            emailField.setError("Fill in your valid email address");
        }else if (phoneField.getText().toString().length() !=7 ) {
            phoneField.setError("Fill in a valid phone number");
        } else if (policeField.getText().toString().length() == 0) {
            policeField.setError("Fill in the police station where you work");
        }else if (regionField.getText().toString().length() ==0) {
            regionField.setError("Fill in the region of the police station where you work");
        }else if (districtField.getText().toString().length() ==0) {
            districtField.setError("Fill in the district of the police station where you work");
        } else if (subject.getSelectedItemPosition() == 0) {
            Toast.makeText(RegisterActivity.this, "Please select the Subject", Toast.LENGTH_SHORT).show();
        } else {
            String body =
                    "Lastname : " + lastnameField.getText().toString() + "<br>Firstname :" + firstnameField.getText().toString() +
                            "<br>Birthdate :" +birthdatefield.getText().toString()+
                            "<br>Email :" + emailField.getText().toString() +
                            "<br>Phone number :" +phoneField.getText().toString()+
                            "<br>Police station :" + policeField.getText().toString()+
                            "<br>Region :"+regionField.getText().toString()+
                            "<br>District: "+districtField.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("plain/text");
            email.setPackage("com.google.android.gm");
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"vds.gov@outlook.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, subject.getSelectedItem().toString());
            email.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
            startActivity(Intent.createChooser(email, "Register"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        new AlertDialog.Builder(RegisterActivity.this)
                .setMessage("You will be contacted after we have verified your contact details")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub


    }
}

