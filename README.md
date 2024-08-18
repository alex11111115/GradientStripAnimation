# 🌈 GradientStripAnimation: Unleash the Power of Hypnotic UI Magic! 🚀

Are you tired of boring, static UIs that make your users yawn? Say goodbye to dull designs and hello to the mesmerizing world of **GradientStripAnimation**! This isn't just another animation library – it's a visual revolution that will have your users glued to their screens, questioning reality itself! 🤯

## 🎭 Features That Will Blow Your Mind

- 🌊 Fluid, endless gradient animations that defy the laws of physics
- 🎨 Customizable colors that would make a rainbow jealous
- 🕰️ Precise control over duration and delays (time is just a construct, right?)
- 🏗️ Flexible strip configurations for ultimate creative freedom
- 🎭 Shadow effects so realistic, you'll question your own existence

## 🚀 Why You Need This in Your Life

1. **Boost User Engagement**: Watch your retention rates skyrocket as users become hypnotized by your UI
2. **Stand Out from the Crowd**: Leave your competitors in the dust with animations they can only dream of
3. **Infinite Customization**: Create a unique visual identity that screams "I'm not like other apps!"
4. **Performance Optimized**: Silky smooth animations that won't make your app break a sweat

## 📹 Video



## 📸 Screenshots



## How to Use the Library?

### 1. Add it in your root build.gradle at the end of repositories:

```gradle
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
        //...
		maven { url 'https://jitpack.io' }
	}
}
```

### 2. Add the Library to Your Project

> Add the following to your project's `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.alex11111115:GradientStripAnimation:1.0'
}
```

## 🛠️ How to Wield This Powerful Tool

### Step 1: Prepare for Greatness

First, make sure you have a `LinearLayout` ready to be transformed into a canvas of moving art:

```java
package com.your.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kilobyte.gradientstripanimation.GradientStripAnimation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Context context = this;
    private LinearLayout container; //Replace with your LinearLayout
    private GradientStripAnimation animation;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        container = findViewById(R.id.container);
        animation = new GradientStripAnimation(this, container);
        
        
    }
}
```

### Step 2: Configure Your Masterpiece

Create a list of `StripConfig` objects to define each strip's appearance:

```java
List<GradientStripAnimation.StripConfig> configs = new ArrayList<>();

// Strip 1: The Ethereal Whisper
configs.add(new GradientStripAnimation.StripConfig(
    LinearLayout.LayoutParams.MATCH_PARENT, 
    dpToPx(18), 
    new int[]{0xFF2563EB, 0xFFffffff, 0xFF94C1D3}
).setCornerRadius(dpToPx(4))
 .setEnableShadow(true)
 .setShadowColor(0x66000000)
 .setShadowRadius(dpToPx(1))
 .setShadowDx(0)
 .setShadowDy(dpToPx(0)));

// Strip 2: The Ocean's Depth
configs.add(new GradientStripAnimation.StripConfig(
    LinearLayout.LayoutParams.MATCH_PARENT, 
    dpToPx(18), 
    new int[]{0xFF94C1D3, 0xFF3B82F6, 0xFF1E3A8A}
).setCornerRadius(dpToPx(4))
 .setEnableShadow(true)
 .setShadowColor(0x66000000)
 .setShadowRadius(dpToPx(1))
 .setShadowDx(0)
 .setShadowDy(dpToPx(0)));

// Strip 3: The Celestial Dance
configs.add(new GradientStripAnimation.StripConfig(
    dpToPx(450), 
    dpToPx(18), 
    new int[]{0xFF94C1D3, 0xFFffffff, 0xFF2563EB}
).setCornerRadius(dpToPx(4))
 .setEnableShadow(true)
 .setShadowColor(0xFF2563EB)
 .setShadowRadius(dpToPx(1))
 .setShadowDx(0)
 .setShadowDy(dpToPx(0)));
 
 //You can add more Strip with the features you want wherever you want.
 
```

### Step 3: Unleash the Magic

Now, let's bring your creation to life:

```java
new GradientStripAnimation(context, container)
    .setStripConfigs(configs)
    .setDuration(5000)  // 5 seconds of pure visual ecstasy
    .setStripDelay(150)  // A tantalizing 150ms delay between strips
    .setOnAnimationEndListener(() -> {
        // Add your method here
    }).startAnimation();
```

## 🧙‍♂️ Customization: Your Imagination is the Limit

- **Width & Height**: Go wild with `MATCH_PARENT` or specific dimensions in dp
- **Colors**: Use an array of colors to create mind-bending gradients
- **Corner Radius**: From sharp edges to smooth curves, the choice is yours
- **Shadows**: Add depth and mystery with customizable shadows
- **Duration & Delay**: Fine-tune the rhythm of your visual symphony

## 🚨 Warning: Side Effects May Include

- Increased user happiness
- Uncontrollable urges to stare at your app for hours
- Sudden realization that static UIs are a thing of the past
- Desire to redesign your entire app (or life) around these animations

## 🎓 Pro Tips for Ascended Developers

1. Combine multiple `GradientStripAnimation` instances for a truly psychedelic experience
2. Sync animations with your app's state changes for seamless transitions
3. Use complementary colors for a harmonious yet striking visual impact
4. Experiment with different strip counts and configurations to find your perfect balance

## 🌟 Conclusion: Embrace the Future of UI

Don't just build apps – create experiences. With `GradientStripAnimation`, you're not just a developer; you're a digital artist, a maestro of motion, a sorcerer of the screen. 

So, are you ready to elevate your UI game and leave the mundane world behind? The power is in your hands. Use it wisely, and watch as your app becomes the talk of the town, the envy of your peers, and the object of your users' undying affection.

Remember: In a world of static, be the motion. In a sea of boring, be the `GradientStripAnimation`. 🌈✨# GradientStripAnimation
