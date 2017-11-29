package android.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private StringBuilder stringBuilder = new StringBuilder();
    private TextView textView;
    private int upPI = 0;
    private int downIP = 0;
    boolean inTouch = false;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setTextSize(15);
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int actionMask = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: // первое касание
                inTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN: // последующие касание
                downIP = pointerIndex;
                break;
            case MotionEvent.ACTION_UP: // прерывание последнего касания
                inTouch = false;
                stringBuilder.setLength(0);
            case MotionEvent.ACTION_POINTER_UP: // прерывание касаний
                upPI = pointerIndex;
                break;
            case MotionEvent.ACTION_MOVE:
                stringBuilder.setLength(0);
                for (int i = 0; i < 10; i++) {
                    stringBuilder.append("Index = " + i);
                    if (i < pointerCount) {
                        stringBuilder.append(", ID = " + event.getPointerId(i));
                        stringBuilder.append(", X = " + event.getX());
                        stringBuilder.append(", Y = " + event.getY());
                    } else {
                        stringBuilder.append(", ID = ");
                        stringBuilder.append(", X = ");
                        stringBuilder.append(", Y = ");
                    }
                    stringBuilder.append("\r\n");
                }
                break;
        }
        result = "down: " + downIP + "\n" + "up: " + upPI + "\n";
        if (inTouch) {
            result += "pointerCount = " + pointerCount + "\n" + stringBuilder.toString();
        }
        textView.setText(result);
        return true;
    }
}
