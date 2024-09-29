package com.kilobyte.gradientstripanimation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	
	private Context context = this;
	
	// UI components
	private LinearLayout container;
	private TextView textViewResult;
	private Button auto;
	private Button stop;
	private Button start;
	
	// Result text to display after animation
	private String result = "# 🌈 GradientStripAnimation: Unleash the Power of Hypnotic UI Magic! 🚀 \n Are you tired of boring, static UIs that make your users yawn? Say goodbye to dull designs and hello to the mesmerizing world of **GradientStripAnimation**! This isn't just another animation library – it's a visual revolution that will have your users glued to their screens, questioning reality itself! 🤯";
	private boolean stopp = false;
    
	// Gradient animation manager
	private GradientStripAnimation animation;
	
	// Handler to manage the timing of the typing effect
	private Handler handler = new Handler(Looper.getMainLooper());
	private Runnable runnable;
	private List<GradientStripAnimation.StripConfig> configs = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initialize UI components and set up button click listeners
		initializeViews();
		setupButtons();
	}
	
	/**
	* Initializes the UI components by finding them from the layout.
	*/
	private void initializeViews() {
		container = findViewById(R.id.container);
		textViewResult = findViewById(R.id.textViewResult);
		auto = findViewById(R.id.auto);
		stop = findViewById(R.id.stop);
		start = findViewById(R.id.start);
		animation = new GradientStripAnimation(this, container);
	}
	
	/**
	* Sets up the click listener for the start button to reset the UI and prepare for the animation.
	*/
	private void setupButtons() {
		
		auto.setOnClickListener(v -> {
			handler.removeCallbacks(runnable); // Clear any pending typing effects
			textViewResult.setText(""); // Clear the result text
			textViewResult.setVisibility(View.GONE); // Hide result TextView
			container.setVisibility(View.VISIBLE); // Show animation container
			setupAnimation(); //To start animation
			formalStart();
                stopp = false;
			configs.clear();
		});
		
		start.setOnClickListener(v -> {
			textViewResult.setText(""); // Clear the result text
			textViewResult.setVisibility(View.GONE); // Hide result TextView
			container.setVisibility(View.VISIBLE); // Show animation container   
			setupAnimation();
			start();
			configs.clear();
                stopp = false;
		});
		
		stop.setOnClickListener(v -> {
			animation.stopAnimation(true);
                stopp = true;
		});
	}
	
	/**
	* Configures and starts the gradient strip animation with predefined settings.
	* After the animation ends, it starts the typing effect.
	*/
	public void setupAnimation() {
		// Reinitialize configs to ensure it's not null
		configs = new ArrayList<>();
		
		// Strip 1: The Ethereal Whisper
		configs.add(new GradientStripAnimation.StripConfig(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(18),
		new int[]{0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591, 0xFF34A79A, 0xFF8D8CD3})
		.setCornerRadii(dpToPx(4), dpToPx(8), dpToPx(4), dpToPx(8)) // topLeft, topRight, bottomRight, bottomLeft
		.setEnableShadow(true)
		.setShadowColor(0x66222327)
		.setShadowRadius(dpToPx(1))
		.setShadowDx(0)
		.setShadowDy(dpToPx(0))
		.setPadding(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4))
		.setMargin(dpToPx(4), dpToPx(8), dpToPx(4), dpToPx(8))
		.setGravity(Gravity.START | Gravity.CENTER_VERTICAL));
		
		// Strip 2: The Ocean's Depth
		configs.add(new GradientStripAnimation.StripConfig(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(18),
		new int[]{0xFF8D8CD3, 0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591, 0xFF34A79A})
		.setCornerRadii(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4)) // topLeft, topRight, bottomRight, bottomLeft
		.setEnableShadow(true)
		.setShadowColor(0x66222327)
		.setShadowRadius(dpToPx(1))
		.setShadowDx(0)
		.setShadowDy(dpToPx(0))
		.setPadding(dpToPx(4), dpToPx(8), dpToPx(4), dpToPx(8))
		.setMargin(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4))
		.setGravity(Gravity.END | Gravity.CENTER_VERTICAL));
		
		// Strip 3: The Celestial Dance
		configs.add(new GradientStripAnimation.StripConfig(dpToPx(280), dpToPx(18),
		new int[]{0xFF34A79A, 0xFF8D8CD3, 0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591})
		.setCornerRadii(dpToPx(4), dpToPx(4), dpToPx(8), dpToPx(8)) // topLeft, topRight, bottomRight, bottomLeft
		.setEnableShadow(true)
		.setShadowColor(0x66222327)
		.setShadowRadius(dpToPx(1))
		.setShadowDx(0)
		.setShadowDy(dpToPx(0))
		.setPadding(dpToPx(6), dpToPx(6), dpToPx(6), dpToPx(6))
		.setMargin(dpToPx(6), dpToPx(6), dpToPx(6), dpToPx(6))
		.setGravity(Gravity.CENTER));
	}
	
	private void formalStart(){
		new GradientStripAnimation(context, container)
		.setStripConfigs(configs) // Set up and start the animation with the configured strips.
		.setDuration(10000) // Set animation duration to 10 seconds.
		.setStripDelay(120) // 200ms delay between strips
		.setOnAnimationEndListener(() -> {
                stopp = true;
			showTypingEffect(result);
		}).startAnimation();
	}
	
	private void start(){
        configs.clear();
        setupAnimation();
    new GradientStripAnimation(context, container)
		.setStripConfigs(configs) // Set up and start the animation with the configured strips.
		.setDuration(1200) // Set animation duration to 10 seconds.
		.setStripDelay(80) // 200ms delay between strips
		.setOnAnimationEndListener(() -> {
			// Show typing effect after animation ends.
                if(stopp != true) {
                	start();
                } else {
                	showTypingEffect(result);
                }
		}).startAnimation();
}
	
	/**
	* Converts density-independent pixels (dp) to pixels (px) based on the screen density.
	*
	* @param dp The value in dp to be converted.
	* @return The converted value in pixels.
	*/
	private int dpToPx(int dp) {
		float density = context.getResources().getDisplayMetrics().density;
		return Math.round((float) dp * density);
	}
	
	/**
	* Displays the text with a typing effect, line by line, character by character.
	*
	* @param text The text to display with the typing effect.
	*/
	private void showTypingEffect(final String text) {
		container.setVisibility(View.GONE);
		textViewResult.setVisibility(View.VISIBLE);
		textViewResult.setText("");
		
		final String[] lines = text.split("\\n");
		final int delay = 25;
		final int[] lineIndex = {0};
		final int[] charIndex = {0};
		
		AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
		fadeIn.setDuration(500);
		fadeIn.setFillAfter(true);
		
		runnable = new Runnable() {
			@Override
			public void run() {
				if (lineIndex[0] < lines.length) {
					String line = lines[lineIndex[0]];
					if (charIndex[0] < line.length()) {
						textViewResult.append(String.valueOf(line.charAt(charIndex[0])));
						charIndex[0]++;
						handler.postDelayed(this, delay);
					} else {
						textViewResult.append("\n");
						lineIndex[0]++;
						charIndex[0] = 0;
						handler.postDelayed(this, delay); // Adjust delay if needed for line break
					}
				} else {
					textViewResult.startAnimation(fadeIn);
				}
			}
		};
		handler.postDelayed(runnable, delay);
		
		ObjectAnimator animator = ObjectAnimator.ofFloat(textViewResult, "translationY", 10f, 0f);
		animator.setDuration(300);
		animator.start();
	}
}