package sr.unasat.kpsfinetracker;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.kpsfinetracker.databases.DatabaseHelper;
import sr.unasat.kpsfinetracker.entities.User;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

public class LoginActivity extends AppCompatActivity {
    Button btn_lregister, btn_llogin;
    EditText et_lusername, et_lpassword;
    CheckBox showpassword;

    DatabaseHelper databaseHelper;
    @Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_login);

    databaseHelper = new DatabaseHelper (this);
        et_lusername = (EditText)findViewById(R.id.usernameTxt);
        et_lpassword = (EditText)findViewById(R.id.passwordTxt);

        btn_llogin = (Button)findViewById(R.id.loginBtn);
        btn_lregister = (Button)findViewById(R.id.register_button);
        showpassword = (CheckBox)findViewById(R.id.showPaswCheckBox);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    et_lpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    et_lpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        btn_llogin.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View v) {
                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                Boolean checklogin = databaseHelper.CheckLogin(username, password);

                if(checklogin == true)

                    {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }

        });


        btn_lregister.setOnClickListener(new OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }



}





