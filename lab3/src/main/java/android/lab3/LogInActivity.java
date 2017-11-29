package android.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText username;
    private final String TAG = "LogInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        username = (EditText) findViewById(R.id.username);
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", username.getText().toString());
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
