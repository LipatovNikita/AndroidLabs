package android.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView welcome;
    private final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        welcome = (TextView) findViewById(R.id.welcome);
        String username = getIntent().getStringExtra("username");
        welcome.setText(welcome.getText() + " " + username);
    }

    public void onLogoutClick(View view) {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Activity возобновляется");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Появилась на экране");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Активна и готова для взаимодействия");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Не активна, но частично видна");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Перекрыта другой Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activity уничтожена");
    }
}
