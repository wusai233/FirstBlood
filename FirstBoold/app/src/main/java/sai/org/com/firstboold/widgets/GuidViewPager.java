package sai.org.com.firstboold.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by wujamie on 16/12/26.
 */

public class GuidViewPager extends ViewPager {
    private Bitmap bitmap;
    private Paint paint = new Paint(1);

    public GuidViewPager(Context context) {
        super(context);
    }

    public GuidViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (this.bitmap != null) {
            int width = this.bitmap.getWidth();
            int height = this.bitmap.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();
            int n = height * getWidth() / getHeight();
            int w = x * ((width - n) / (count - 1)) / getWidth();
            canvas.drawBitmap(this.bitmap, new Rect(w, 0, n + w, height), new Rect(x, 0, x + getWidth(), getHeight()), this.paint);
        }
        super.dispatchDraw(canvas);
    }

    public void setBackground(Bitmap paramBitmap) {
        this.bitmap = paramBitmap;
        this.paint.setFilterBitmap(true);
    }
}
