package open.gfl.chipcalc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class StatProgressView extends View {
    private int[] colors;
    private final Paint paint;
    private int[] values;

    public StatProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setStyle(Paint.Style.FILL);
    }

    public void setColors(int i, int i2, int i3) {
        this.colors = new int[]{i, i2, i3};
        invalidate();
    }

    public void setValues(int i, int i2, int i3) {
        this.values = new int[]{i, i2, i3};
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(this.colors[2]);
        float width = getWidth(this.colors[0]);
        float width2 = getWidth(this.colors[1]);
        this.paint.setColor(this.colors[1]);
        float height = (float) getHeight();
        canvas.drawRect(width, 0.0f, width2, height, this.paint);
        this.paint.setColor(this.colors[0]);
        canvas.drawRect(0.0f, 0.0f, width, height, this.paint);
    }

    private float getWidth(int i) {
        return ((float) (i * getWidth())) / ((float) this.values[2]);
    }
}
