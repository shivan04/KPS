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

public class RegisterActivity extends Activity{
    private EditText lastnameTxt, firstnameTxt, dobTxt, emailTxt, phoneTxt, policeTxt, regionTxt, districtTxt;
    private Button registerBtn;
    private Spinner subjectSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        lastnameTxt = (EditText) findViewById(R.id.lastname_text);
        firstnameTxt = (EditText) findViewById(R.id.firstname_text);
        dobTxt = (EditText) findViewById(R.id.dob_text);
        emailTxt = (EditText) findViewById(R.id.email_text);
        phoneTxt = (EditText) findViewById(R.id.phone_text);
        policeTxt = (EditText) findViewById(R.id.policestation_text);
        regionTxt = (EditText) findViewById(R.id.region_text);
        districtTxt = (EditText) findViewById(R.id.district_text);

        subjectSpinner = (Spinner) findViewById(R.id.option_spinner);
        String subjectsList[] = new String[]{"Select Subject", "Register", "Reset Password", "Reset Username"};
        ArrayAdapter<String> subjectsArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjectsList);
        subjectsArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        subjectSpinner.setAdapter(subjectsArrayAdapter);

        subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        registerBtn = (Button) findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lastnameTxt.getText().toString().length() == 0) {
                    lastnameTxt.setError("Fill in your lastname");
                } else if (firstnameTxt.getText().toString().length() == 0) {
                    firstnameTxt.setError("Fill in your firstname");
                } else if (dobTxt.getText().toString().length()==0) {
                    dobTxt.setError("Fill in your birthdate (ex. 01-01-2021)");
                } else if (emailTxt.getText().toString().length() == 0) {
                    emailTxt.setError("Fill in your valid email address");
                }else if (phoneTxt.getText().toString().length() !=7 ) {
                    phoneTxt.setError("Fill in a valid phone number");
                } else if (policeTxt.getText().toString().length() == 0) {
                    policeTxt.setError("Fill in the police station where you work");
                }else if (regionTxt.getText().toString().length() ==0) {
                    regionTxt.setError("Fill in the region of the police station where you work");
                }else if (districtTxt.getText().toString().length() ==0) {
                    districtTxt.setError("Fill in the district of the police station where you work");
                } else if (subjectSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please select the subject", Toast.LENGTH_SHORT).show();
                } else {
                    sendRegisterEmail();
                }
            }
        });
    }


    private void sendRegisterEmail(){
        String body =
                "Lastname : " + lastnameTxt.getText().toString() + "<br>Firstname :" + firstnameTxt.getText().toString() +
                        "<br>Birthdate :" + dobTxt.getText().toString()+
                        "<br>Email :" + emailTxt.getText().toString() +
                        "<br>Phone number :" + phoneTxt.getText().toString()+
                        "<br>Police station :" + policeTxt.getText().toString()+
                        "<br>Region :"+ regionTxt.getText().toString()+
                        "<br>District: "+ districtTxt.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        email.setPackage("com.google.android.gm");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"vds.gov@outlook.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, subjectSpinner.getSelectedItem().toString());
        email.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
        startActivity(Intent.createChooser(email, "Register"));
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        new AlertDialog.Builder(RegisterActivity.this)
//                .setMessage("You will be contacted after we have verified your contact details")
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                })
//                .show();
//    }
}