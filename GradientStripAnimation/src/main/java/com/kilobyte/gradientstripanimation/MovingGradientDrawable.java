package com.kilobyte.gradientstripanimation;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
    private final float cornerRadius;
    private float offset;

    public MovingGradientDrawable(int[] colors, float cornerRadius) {
        this.colors = colors;
        this.cornerRadius = cornerRadius;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.offset = 0f;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();

        LinearGradient gradient = new LinearGradient(
                -width + (offset * width * 2), 0,
                width + (offset * width * 2), 0,
                colors, null, Shader.TileMode.CLAMP);

        paint.setShader(gradient);

        RectF rect = new RectF(bounds);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public int getAlpha() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return paint.getAlpha();
        } else {
            // For older versions, we can't get the alpha directly from the paint
            // So we'll return the full opacity
            return 255;
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return paint.getAlpha() == 255 ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
        } else {
            // For older versions, we'll assume it's always translucent
            return PixelFormat.TRANSLUCENT;
        }
    }

    public void setOffset(float offset) {
        this.offset = offset;
        invalidateSelf();
    }
}