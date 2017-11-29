package android.lab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameUpdate;
    private TextView passwordUpdate;
    private DataBaseHelperImpl dataBaseHelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usernameUpdate = (TextView) findViewById(R.id.usernameUpdate);
        passwordUpdate = (TextView) findViewById(R.id.passwordUpdate);
        username = getIntent().getStringExtra("username");
        dataBaseHelper = new DataBaseHelperImpl(this);
        usernameUpdate.setText(dataBaseHelper.getUserByUsername(username).getUsername());
        passwordUpdate.setText(dataBaseHelper.getUserByUsername(username).getPassword());
        for (User user : dataBaseHelper.getUserList()) {
            System.out.println(user.toString());
        }
    }

    public void onUpdateClick(View view) {
        User user = dataBaseHelper.getUserByUsername(username);
        user.setUsername(usernameUpdate.getText().toString());
        user.setPassword(passwordUpdate.getText().toString());
        dataBaseHelper.updateUser(user);
        usernameUpdate.setText(dataBaseHelper.getUserByUsername(user.getUsername()).getUsername());
        passwordUpdate.setText(dataBaseHelper.getUserByUsername(user.getUsername()).getPassword());
    }

    public void onLogoutClick(View view) {
        dataBaseHelper.deleteUsers();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
