package android.lab6;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

public class DrawView extends View {

    private Paint paint;
    private Rect rect;
    private StringBuilder stringBuilder;

    public DrawView(Context context) {
        super(context);
        paint = new Paint();
        rect = new Rect(100, 200, 200, 300);
        stringBuilder = new StringBuilder();
    }

    public void onDraw(Canvas canvas) {
        canvas.drawARGB(80, 102, 204, 255);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setTextSize(70);
        stringBuilder.setLength(0);
        stringBuilder.append("width = ").append(canvas.getWidth())
                .append(", height = ").append(canvas.getHeight());
        canvas.drawText(stringBuilder.toString(), 100, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.STROKE);
        rect.offset(150, 0);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        rect.offset(150, 0);
        canvas.drawRect(rect, paint);

        canvas.clipPath(getHexagonPath());
        canvas.drawColor(Color.BLACK);
        super.onDraw(canvas);
    }

    public Path getHexagonPath() {
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 150, y + 200);
        path.lineTo(x, y + 200);
        path.lineTo(x - 150, y + 200);
        path.lineTo(x - 250, y);
        path.lineTo(x - 150, y - 200);
        path.lineTo(x + 150, y - 200);
        path.lineTo(x + 250, y);
        path.lineTo(x + 150, y + 200);
        return path;
    }
}
