package android.lab7task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

public class DrawView extends View {

    DisplayMetrics metrics = this.getResources().getDisplayMetrics();

    private Paint paint;
    private Bitmap bitmap = Bitmap.createBitmap(metrics.widthPixels, metrics.heightPixels, Bitmap.Config.ARGB_8888);
    private Canvas drawCanvas = new Canvas(bitmap);
    private HashMap<Integer, Float> mX = new HashMap<>();
    private HashMap<Integer, Float> mY = new HashMap<>();
    private HashMap<Integer, Path> paths = new HashMap<>();

    public DrawView(Context context) {
        super(context);
        setupDraw();
    }

    private void setupDraw() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, paint);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);
            if (path != null) {
                canvas.drawPath(path, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int maskedAction = event.getActionMasked();
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                for (int i = 0; i < event.getPointerCount(); i++) {
                    Path path = new Path();
                    path.moveTo(event.getX(i), event.getY(i));
                    paths.put(event.getPointerId(i), path);
                    mX.put(event.getPointerId(i), event.getX(i));
                    mY.put(event.getPointerId(i), event.getY(i));
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                for (int i = 0; i < event.getPointerCount(); i++) {
                    Path path = paths.get(event.getPointerId(i));
                    if (path != null) {
                        float x = event.getX(i);
                        float y = event.getY(i);
                        path.quadTo(mX.get(event.getPointerId(i)), mY.get(event.getPointerId(i)), x , y);
                        mX.put(event.getPointerId(i), event.getX(i));
                        mY.put(event.getPointerId(i), event.getY(i));
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                for (int i = 0; i < event.getPointerCount(); i++) {
                    Path path = paths.get(event.getPointerId(i));
                    if (path != null) {
                        path.lineTo(event.getX(i), event.getY(i));
                        drawCanvas.drawPath(path, paint);
                        paths.remove(event.getPointerId(i));
                        mX.remove(event.getPointerId(i));
                        mY.remove(event.getPointerId(i));
                    }
                }
                break;
            }
        }
        return true;
    }
}
