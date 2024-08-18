package com.kilobyte.gradientstripanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;

public class GradientStripAnimation {
    private static final String TAG = "GradientStripAnimation";
    private Context context;
    private LinearLayout container;
    private int stripCount;
    private long duration;
    private long stripDelay;
    private OnAnimationEndListener onAnimationEndListener;
    private Handler mainHandler;
    private List<Animator> runningAnimations;
    private List<StripConfig> stripConfigs;

    public interface OnAnimationEndListener {
        void onAnimationEnd();
    }

    public static class StripConfig {
        private int width;
        private int height;
        private @ColorInt int[] colors;
        private float cornerRadius;
        private boolean enableShadow;
        private int shadowColor;
        private float shadowRadius;
        private float shadowDx;
        private float shadowDy;

        public StripConfig(int width, int height, @ColorInt int[] colors) {
            this.width = width;
            this.height = height;
            this.colors = colors;
            this.cornerRadius = 4f;
            this.enableShadow = true;
            this.shadowColor = 0x66000000;
            this.shadowRadius = 1f;
            this.shadowDx = 0f;
            this.shadowDy = 0f;
        }

        // Getters and Setters
        public int getWidth() {
            return width;
        }

        public StripConfig setWidth(int width) {
            this.width = width;
            return this;
        }

        public int getHeight() {
            return height;
        }

        public StripConfig setHeight(int height) {
            this.height = height;
            return this;
        }

        public int[] getColors() {
            return colors;
        }

        public StripConfig setColors(@ColorInt int[] colors) {
            this.colors = colors;
            return this;
        }

        public float getCornerRadius() {
            return cornerRadius;
        }

        public StripConfig setCornerRadius(float cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }

        public boolean isEnableShadow() {
            return enableShadow;
        }

        public StripConfig setEnableShadow(boolean enableShadow) {
            this.enableShadow = enableShadow;
            return this;
        }

        public int getShadowColor() {
            return shadowColor;
        }

        public StripConfig setShadowColor(int shadowColor) {
            this.shadowColor = shadowColor;
            return this;
        }

        public float getShadowRadius() {
            return shadowRadius;
        }

        public StripConfig setShadowRadius(float shadowRadius) {
            this.shadowRadius = shadowRadius;
            return this;
        }

        public float getShadowDx() {
            return shadowDx;
        }

        public StripConfig setShadowDx(float shadowDx) {
            this.shadowDx = shadowDx;
            return this;
        }

        public float getShadowDy() {
            return shadowDy;
        }

        public StripConfig setShadowDy(float shadowDy) {
            this.shadowDy = shadowDy;
            return this;
        }
    }

    public GradientStripAnimation(Context context, LinearLayout container) {
        this.context = context;
        this.container = container;
        this.stripCount = 3;
        this.duration = 10000;
        this.stripDelay = 200;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.runningAnimations = new ArrayList<>();
        this.stripConfigs = new ArrayList<>();
    }

    public GradientStripAnimation setStripCount(int stripCount) {
        this.stripCount = stripCount;
        return this;
    }

    public GradientStripAnimation setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public GradientStripAnimation setStripDelay(long stripDelay) {
        this.stripDelay = stripDelay;
        return this;
    }

    public GradientStripAnimation setOnAnimationEndListener(OnAnimationEndListener listener) {
        this.onAnimationEndListener = listener;
        return this;
    }

    public GradientStripAnimation setStripConfigs(List<StripConfig> stripConfigs) {
        this.stripConfigs = stripConfigs;
        return this;
    }

    public void startAnimation() {
        if (container == null) {
            Log.e(TAG, "Container is null. Animation cannot start.");
            return;
        }

        Log.d(TAG, "Starting animation with stripCount: " + stripCount);

        container.removeAllViews();
        runningAnimations.clear();

        for (int i = 0; i < stripCount; i++) {
            StripConfig config = i < stripConfigs.size() ? stripConfigs.get(i) : getDefaultStripConfig();
            View strip = createStrip(i, config);
            container.addView(strip);
            animateStrip(strip, i * stripDelay, i, config);
        }
    }

    private StripConfig getDefaultStripConfig() {
        return new StripConfig(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dpToPx(18),
                new int[]{0xFF1E3A8A, 0xFF3B82F6, 0xFF93C5FD, 0xFF3B82F6}
        );
    }

    private View createStrip(int index, StripConfig config) {
        View strip = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                config.getWidth(),
                config.getHeight()
        );
        params.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4));
        strip.setLayoutParams(params);

        strip.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                strip.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                updateGradient(strip, 0f, config);
            }
        });

        strip.setAlpha(0f);
        Log.d(TAG, "Created strip " + index);
        return strip;
    }

    private void updateGradient(View view, float offset, StripConfig config) {
        MovingGradientDrawable drawable = new MovingGradientDrawable(config.getColors(), config.getCornerRadius());
        drawable.setOffset(offset);

        if (config.isEnableShadow()) {
            ViewCompat.setElevation(view, config.getShadowRadius());
            view.setTranslationZ(config.getShadowRadius());
            ViewCompat.setBackgroundTintList(view, null);
            view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        } else {
            ViewCompat.setElevation(view, 0);
            view.setTranslationZ(0);
        }

        ViewCompat.setBackground(view, drawable);
    }

    private void animateStrip(final View view, long delay, int index, StripConfig config) {
        Log.d(TAG, "Animating strip " + index + " with delay " + delay);

        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(duration / 8);
        fadeInAnimator.setInterpolator(new LinearInterpolator());

        ValueAnimator gradientAnimator = ValueAnimator.ofFloat(0f, 1f);
        gradientAnimator.setDuration(duration);
        gradientAnimator.setRepeatCount(ValueAnimator.INFINITE);
        gradientAnimator.setRepeatMode(ValueAnimator.RESTART);
        gradientAnimator.setInterpolator(new LinearInterpolator());
        gradientAnimator.addUpdateListener(animation -> {
            if (container.indexOfChild(view) == -1) return;
            float progress = (float) animation.getAnimatedValue();
            updateGradient(view, progress, config);
        });

        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        fadeOutAnimator.setDuration(duration / 8);
        fadeOutAnimator.setStartDelay(duration * 7 / 8);
        fadeOutAnimator.setInterpolator(new LinearInterpolator());

        Animator.AnimatorListener animationEndListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "Animation ended for strip " + index);
                runningAnimations.remove(animation);
                checkAllAnimationsCompleted();
            }
        };

        fadeOutAnimator.addListener(animationEndListener);

        mainHandler.postDelayed(() -> {
            fadeInAnimator.start();
            gradientAnimator.start();
            fadeOutAnimator.start();
            runningAnimations.add(fadeOutAnimator);
        }, delay);
    }

    private void checkAllAnimationsCompleted() {
        Log.d(TAG, "Checking animations. Running animations count: " + runningAnimations.size());
        if (runningAnimations.isEmpty()) {
            Log.d(TAG, "All strip animations have ended");
            mainHandler.post(() -> {
                Log.d(TAG, "Calling onAnimationEnd on main thread");
                if (onAnimationEndListener != null) {
                    onAnimationEndListener.onAnimationEnd();
                }
            });
            container.clearAnimation();
            for (int i = 0; i < container.getChildCount(); i++) {
                container.getChildAt(i).clearAnimation();
            }
        }
    }

    public static int parseColor(String colorString) {
        if (colorString.startsWith("#")) {
            return (int) Long.parseLong(colorString.substring(1), 16);
        } else if (colorString.startsWith("ARGB")) {
            String[] parts = colorString.substring(5, colorString.length() - 1).split(",");
            int a = Integer.parseInt(parts[0].trim());
            int r = Integer.parseInt(parts[1].trim());
            int g = Integer.parseInt(parts[2].trim());
            int b = Integer.parseInt(parts[3].trim());
            return (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
        } else {
            throw new IllegalArgumentException("Unknown color format");
        }
    }

    private int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}