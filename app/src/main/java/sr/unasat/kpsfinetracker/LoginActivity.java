package sr.unasat.kpsfinetracker;

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
import sr.unasat.kpsfinetracker.databases.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    Button registerBtn, loginBtn;
    EditText usernameTxt, passwordTxt;
    CheckBox showPasswordCheckBox;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        dbHelper = new DatabaseHelper (this);
        usernameTxt = (EditText)findViewById(R.id.username_txt);
        passwordTxt = (EditText)findViewById(R.id.password_txt);

        loginBtn = (Button)findViewById(R.id.login_btn);
        registerBtn = (Button)findViewById(R.id.register_btn);
        showPasswordCheckBox = (CheckBox)findViewById(R.id.showpasw_checkbox);

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    passwordTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                System.out.println(username + " " + password);
                boolean results = login(username, password);
                System.out.println(results);

                if(results){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean login(String username, String password){
        return dbHelper.validateLogin(username, password);
    }

}





