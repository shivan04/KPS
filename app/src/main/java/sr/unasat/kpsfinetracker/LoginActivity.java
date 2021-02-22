package sr.unasat.kpsfinetracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sr.unasat.kpsfinetracker.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {
    UserRepository userRepo;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private Button loginBtn;
    private CheckBox showPasswordChexkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = (EditText)findViewById(R.id.usernameTxt);
        passwordTxt = (EditText)findViewById(R.id.passwordTxt);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        showPasswordChexkBox = (CheckBox)findViewById(R.id.showPaswCheckBox);
        userRepo = new UserRepository(this);

        showPasswordChexkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    passwordTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameTxt.length() != 0 && passwordTxt.length() != 0) {
                    validate(usernameTxt.getText().toString(), passwordTxt.getText().toString());
                }
                else{
                    toastMessageMaker("Please make sure you fill in both fields", false);
                }
            }
        });
    }

    private void validate(String userName, String userPassword) {
        boolean validation = userRepo.validateLogin(userName, userPassword);

        if (validation) {
            toastMessageMaker("You have successfully logged in!", true);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            toastMessageMaker("Something went wrong!", false);
        }
    }

    private void toastMessageMaker(String message, boolean fast){
        if (fast) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }


}
