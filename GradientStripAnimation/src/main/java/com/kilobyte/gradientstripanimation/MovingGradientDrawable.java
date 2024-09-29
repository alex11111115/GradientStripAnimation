package com.kilobyte.gradientstripanimation;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovingGradientDrawable extends Drawable {
    private final Paint paint;
    private final int[] colors;
    private final float[] cornerRadii;
    private float offset;
    private LinearGradient gradient;
    private Path path;
    private RectF rectF;
    private int lastWidth = -1;
    private int lastHeight = -1;

    public MovingGradientDrawable(int[] colors, float[] cornerRadii) {
        this.colors = colors;
        this.cornerRadii = cornerRadii;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.offset = 0f;
        this.path = new Path();
        this.rectF = new RectF();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();

        if (width != lastWidth || height != lastHeight) {
            updateGradient(width, height);
            updatePath(bounds);
            lastWidth = width;
            lastHeight = height;
        }

        paint.setShader(gradient);
        canvas.drawPath(path, paint);
    }

    private void updateGradient(int width, int height) {
        gradient = new LinearGradient(
            -width + (offset * width * 2), 0,
            width + (offset * width * 2), 0,
            colors, null, Shader.TileMode.CLAMP);
    }

    private void updatePath(Rect bounds) {
        rectF.set(bounds);
        path.rewind();
        path.addRoundRect(rectF, cornerRadii, Path.Direction.CW);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public int getAlpha() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ? paint.getAlpha() : 255;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && paint.getAlpha() == 255 
               ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }

    public void setOffset(float offset) {
        if (this.offset != offset) {
            this.offset = offset;
            updateGradient(lastWidth, lastHeight);
            invalidateSelf();
        }
    }
}