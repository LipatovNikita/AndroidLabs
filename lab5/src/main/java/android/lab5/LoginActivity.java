package android.lab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private DataBaseHelperImpl dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dataBaseHelper = new DataBaseHelperImpl(this);
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        User user = new User(username.getText().toString(), password.getText().toString());
        dataBaseHelper.addUser(user);
        intent.putExtra("username", username.getText().toString());
        startActivity(intent);
    }
}
