package sr.unasat.kpsfinetracker;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.kpsfinetracker.databases.DatabaseHelper;
import sr.unasat.kpsfinetracker.entities.User;

public class LoginActivity extends AppCompatActivity {
    Button btn_lregister, btn_llogin;
    EditText et_lusername, et_lpassword;

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


        btn_llogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                Boolean checklogin = databaseHelper.CheckLogin(username, password);
                if(checklogin == true){
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

