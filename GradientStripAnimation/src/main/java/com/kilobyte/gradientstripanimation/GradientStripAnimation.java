package com.kilobyte.gradientstripanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import androidx.annotation.ColorInt;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradientStripAnimation {
	private static final String TAG = "GradientStripAnimation";
	private Context context;
	private LinearLayout container;
	private long duration;
	private long stripDelay;
	private OnAnimationEndListener onAnimationEndListener;
	private Handler mainHandler;
	private List<Animator> runningAnimations;
	private List<StripConfig> stripConfigs;
	private boolean isAnimating;
	private boolean showWithAnimation;
	private boolean hideWithAnimation;
	private Runnable animationRunnable;
	
	public interface OnAnimationEndListener {
		void onAnimationEnd();
	}
	
	public static class StripConfig {
		private int width;
		private int height;
		private @ColorInt int[] colors;
		private float[] cornerRadii;
		private boolean enableShadow;
		private int shadowColor;
		private float shadowRadius;
		private float shadowDx;
		private float shadowDy;
		private int paddingLeft;
		private int paddingTop;
		private int paddingRight;
		private int paddingBottom;
		private int marginLeft;
		private int marginTop;
		private int marginRight;
		private int marginBottom;
		private int gravity;
		
		public StripConfig(int width, int height, @ColorInt int[] colors) {
			this.width = width;
			this.height = height;
			this.colors = colors;
			this.cornerRadii = new float[]{4f, 4f, 4f, 4f, 4f, 4f, 4f, 4f};
			this.enableShadow = true;
			this.shadowColor = 0x66222327;
			this.shadowRadius = 1f;
			this.shadowDx = 0f;
			this.shadowDy = 0f;
			this.paddingLeft = 0;
			this.paddingTop = 0;
			this.paddingRight = 0;
			this.paddingBottom = 0;
			this.marginLeft = 4;
			this.marginTop = 4;
			this.marginRight = 4;
			this.marginBottom = 4;
			this.gravity = Gravity.START;
		}
		
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
		
		public float[] getCornerRadii() {
			return cornerRadii;
		}
		
		public StripConfig setCornerRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
			this.cornerRadii = new float[]{
				topLeft, topLeft,
				topRight, topRight,
				bottomRight, bottomRight,
				bottomLeft, bottomLeft
			};
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
		
		public StripConfig setPadding(int left, int top, int right, int bottom) {
			this.paddingLeft = left;
			this.paddingTop = top;
			this.paddingRight = right;
			this.paddingBottom = bottom;
			return this;
		}
		
		public StripConfig setMargin(int left, int top, int right, int bottom) {
			this.marginLeft = left;
			this.marginTop = top;
			this.marginRight = right;
			this.marginBottom = bottom;
			return this;
		}
		
		public StripConfig setGravity(int gravity) {
			this.gravity = gravity;
			return this;
		}
		
		public int getPaddingLeft() { return paddingLeft; }
		public int getPaddingTop() { return paddingTop; }
		public int getPaddingRight() { return paddingRight; }
		public int getPaddingBottom() { return paddingBottom; }
		public int getMarginLeft() { return marginLeft; }
		public int getMarginTop() { return marginTop; }
		public int getMarginRight() { return marginRight; }
		public int getMarginBottom() { return marginBottom; }
		public int getGravity() { return gravity; }
	}
	
	public GradientStripAnimation(Context context, LinearLayout container) {
		this.context = context;
		this.container = container;
		this.duration = 10000;
		this.stripDelay = 200;
		this.mainHandler = new Handler(Looper.getMainLooper());
		this.runningAnimations = new ArrayList<>();
		this.stripConfigs = new ArrayList<>();
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
	
	public void startContinuousAnimation(boolean showWithAnimation, boolean hideWithAnimation) {
		if (isAnimating) {
			return;
		}
		
		this.showWithAnimation = showWithAnimation;
		this.hideWithAnimation = hideWithAnimation;
		isAnimating = true;
		
		animationRunnable = new Runnable() {
			@Override
			public void run() {
				if (isAnimating) {
					startSingleAnimation();
					mainHandler.postDelayed(this, 5000);
				}
			}
		};
		mainHandler.post(animationRunnable);
	}
	
	public void stopAnimation(boolean hideWithAnimation) {
		isAnimating = false;
		mainHandler.removeCallbacks(animationRunnable);
		
		if (hideWithAnimation) {
			fadeOutAllStrips(() -> {
				container.removeAllViews();
				runningAnimations.clear();
			});
		} else {
			for (Animator animator : runningAnimations) {
				animator.cancel();
			}
			runningAnimations.clear();
			container.removeAllViews();
		}
	}
	
	private void fadeOutAllStrips(Runnable onComplete) {
		int childCount = container.getChildCount();
		if (childCount == 0) {
			onComplete.run();
			return;
		}
		
		final int[] completedAnimations = {0};
		
		for (int i = 0; i < childCount; i++) {
			View child = container.getChildAt(i);
			ObjectAnimator fadeOut = ObjectAnimator.ofFloat(child, "alpha", 1f, 0f);
			fadeOut.setDuration(duration / 8);
			fadeOut.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					completedAnimations[0]++;
					if (completedAnimations[0] == childCount) {
						onComplete.run();
					}
				}
			});
			fadeOut.start();
		}
	}
	
	private void startSingleAnimation() {
		if (container == null) {
			Log.e(TAG, "Container is null. Animation cannot start.");
			return;
		}
		
		Log.d(TAG, "Starting animation with stripCount: " + stripConfigs.size());
		
		if (hideWithAnimation && container.getChildCount() > 0) {
			fadeOutAllStrips(() -> {
				container.removeAllViews();
				createAndAnimateStrips();
			});
		} else {
			container.removeAllViews();
			createAndAnimateStrips();
		}
	}
	
	private void createAndAnimateStrips() {
		runningAnimations.clear();
		
		for (int i = 0; i < stripConfigs.size(); i++) {
			StripConfig config = stripConfigs.get(i);
			View strip = createStrip(i, config);
			container.addView(strip);
			animateStrip(strip, i * stripDelay, i, config);
		}
	}
	
	public void startAnimation() {
		if (container == null) {
			Log.e(TAG, "Container is null. Animation cannot start.");
			return;
		}
		
		Log.d(TAG, "Starting animation with stripCount: " + stripConfigs.size());
		container.removeAllViews();
		runningAnimations.clear();
		
		for (int i = 0; i < stripConfigs.size(); i++) {
			StripConfig config = stripConfigs.get(i);
			View strip = createStrip(i, config);
			container.addView(strip);
			animateStrip(strip, i * stripDelay, i, config);
		}
	}
	
	private StripConfig getDefaultStripConfig() {
		return new StripConfig(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(18), new int[]{0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591, 0xFF34A79A, 0xFF8D8CD3});
	}
	
	private View createStrip(int index, StripConfig config) {
		View strip = new View(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(config.getWidth(), config.getHeight());
		params.setMargins(dpToPx(config.getMarginLeft()), dpToPx(config.getMarginTop()), dpToPx(config.getMarginRight()), dpToPx(config.getMarginBottom()));
		params.gravity = config.getGravity();
		strip.setLayoutParams(params);
		strip.setPadding(dpToPx(config.getPaddingLeft()), dpToPx(config.getPaddingTop()), dpToPx(config.getPaddingRight()), dpToPx(config.getPaddingBottom()));
		strip.setClipToOutline(true);
		
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
		MovingGradientDrawable drawable = new MovingGradientDrawable(config.getColors(), config.getCornerRadii());
		drawable.setOffset(offset);
		
		if (config.isEnableShadow()) {
			view.setOutlineProvider(new ViewOutlineProvider() {
				@Override
				public void getOutline(View view, android.graphics.Outline outline) {
					outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), config.getCornerRadii()[0]);
				}
			});
            
			view.setClipToOutline(true);
			ViewCompat.setElevation(view, config.getShadowRadius());
			view.setTranslationZ(config.getShadowRadius());
			view.setBackgroundColor(config.getShadowColor());
		} else {
			ViewCompat.setElevation(view, 0);
			view.setTranslationZ(0);
			view.setClipToOutline(false);
		}
		view.setForeground(drawable);
	}
	
	private void animateStrip(final View view, long delay, int index, StripConfig config) {
		Log.d(TAG, "Animating strip " + index + " with delay " + delay);
		
		ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
		fadeInAnimator.setDuration(duration / 8);
		fadeInAnimator.setInterpolator(new LinearInterpolator());
		
		ValueAnimator gradientAnimator = ValueAnimator.ofFloat(0f, 1f);
		gradientAnimator.setDuration(duration / 2);
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
    if (colorString == null || colorString.isEmpty()) {
        throw new IllegalArgumentException("Color string cannot be null or empty");
    }

    colorString = colorString.trim().toUpperCase();

    try {
        // استخدام Regular Expressions للتعرف على الصيغ المختلفة بشكل أسرع
        if (colorString.matches("^#[0-9A-F]{3,8}$")) {
            return parseHexColor(colorString);
        } else if (colorString.matches("^0X[0-9A-F]{1,8}$")) {
            return (int) Long.parseLong(colorString.substring(2), 16);
        } else if (colorString.matches("^(RGB|RGBA|ARGB|HSL|HSLA)\\s*\\([^)]+\\)$")) {
            return parseColorFunction(colorString);
        } else {
            // التعرف على الألوان المسماة
            Integer namedColor = COLOR_MAP.get(colorString);
            if (namedColor != null) {
                return namedColor;
            }
            throw new IllegalArgumentException("Unknown color format: " + colorString);
        }
    } catch (Exception e) {
        throw new IllegalArgumentException("Error parsing color: " + colorString, e);
    }
}

private static int parseHexColor(String hexColor) {
    hexColor = hexColor.replace("#", "");
    switch (hexColor.length()) {
        case 3: // #RGB
            return Color.parseColor("#" + hexColor.replaceAll(".", "$0$0"));
        case 4: // #ARGB
            return Color.parseColor("#" + hexColor.substring(1) + hexColor.charAt(0));
        case 6: // #RRGGBB
        case 8: // #AARRGGBB
            return Color.parseColor("#" + hexColor);
        default:
            throw new IllegalArgumentException("Invalid HEX color format");
    }
}

private static int parseColorFunction(String colorString) {
    Matcher matcher = Pattern.compile("(\\w+)\\s*\\(([^)]+)\\)").matcher(colorString);
    if (matcher.find()) {
        String function = matcher.group(1);
        String[] values = matcher.group(2).split(",");

        switch (function) {
            case "RGB":
                return parseRGB(values, false);
            case "RGBA":
                return parseRGB(values, true);
            case "ARGB":
                return parseARGB(values);
            case "HSL":
                return parseHSL(values, false);
            case "HSLA":
                return parseHSL(values, true);
            default:
                throw new IllegalArgumentException("Unsupported color function: " + function);
        }
    }
    throw new IllegalArgumentException("Invalid color function format");
}

private static int parseRGB(String[] values, boolean hasAlpha) {
    if (values.length != (hasAlpha ? 4 : 3)) {
        throw new IllegalArgumentException("Invalid number of components for RGB(A)");
    }
    int r = parseColorComponent(values[0], 255);
    int g = parseColorComponent(values[1], 255);
    int b = parseColorComponent(values[2], 255);
    int a = hasAlpha ? parseColorComponent(values[3], 255) : 255;
    return Color.argb(a, r, g, b);
}

private static int parseARGB(String[] values) {
    if (values.length != 4) {
        throw new IllegalArgumentException("Invalid number of components for ARGB");
    }
    int a = parseColorComponent(values[0], 255);
    int r = parseColorComponent(values[1], 255);
    int g = parseColorComponent(values[2], 255);
    int b = parseColorComponent(values[3], 255);
    return Color.argb(a, r, g, b);
}

private static int parseHSL(String[] values, boolean hasAlpha) {
    if (values.length != (hasAlpha ? 4 : 3)) {
        throw new IllegalArgumentException("Invalid number of components for HSL(A)");
    }
    float h = parseColorComponent(values[0], 360) / 360f;
    float s = parseColorComponent(values[1], 100) / 100f;
    float l = parseColorComponent(values[2], 100) / 100f;
    int a = hasAlpha ? parseColorComponent(values[3], 255) : 255;
    float[] hsl = {h, s, l};
    return ColorUtils.setAlphaComponent(ColorUtils.HSLToColor(hsl), a);
}

private static int parseColorComponent(String value, int max) {
    value = value.trim();
    if (value.endsWith("%")) {
        float percent = Float.parseFloat(value.substring(0, value.length() - 1));
        return Math.round(percent * max / 100f);
    }
    return Math.round(Float.parseFloat(value));
}

private static final Map<String, Integer> COLOR_MAP = new HashMap<String, Integer>() {{
    put("BLACK", Color.BLACK);
    put("WHITE", Color.WHITE);
    put("RED", Color.RED);
    put("GREEN", Color.GREEN);
    put("BLUE", Color.BLUE);
    put("YELLOW", Color.YELLOW);
}};
	
	private int dpToPx(int dp) {
		float density = context.getResources().getDisplayMetrics().density;
		return Math.round((float) dp * density);
	}
}