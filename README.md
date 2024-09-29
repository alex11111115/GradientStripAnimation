[![StandWithPalestine](https://raw.githubusercontent.com/karim-eg/StandWithPalestine/main/assets/palestine_badge.svg)](https://github.com/karim-eg/StandWithPalestine)   [![](https://jitpack.io/v/alex11111115/GradientStripAnimation.svg)](https://jitpack.io/#alex11111115/GradientStripAnimation) 

[![ReadMeSupportPalestine](https://raw.githubusercontent.com/Safouene1/support-palestine-banner/master/banner-support.svg)](https://techforpalestine.org/learn-more)

# 🌈 GradientStripAnimation: Unleash the Power of Hypnotic UI Magic! 🚀

Are you tired of boring, static UIs that make your users yawn? Say goodbye to dull designs and hello to the mesmerizing world of **GradientStripAnimation**! This isn't just another animation library – it's a visual revolution that will have your users glued to their screens, questioning reality itself! 🤯

![GradientStripAnimation](https://github.com/user-attachments/assets/8e66fe51-57a0-40e0-8b56-a77ace5eaa81)

## 🎭 Features That Will Blow Your Mind [![API](https://img.shields.io/badge/API-19%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=19) 

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
https://github.com/user-attachments/assets/c68bcd1f-f130-4226-ac00-451dc97f32f2


## 📸 Screenshots
![Screenshot_٢٠٢٤٠٨١٩-٠٢١٦١٩_GradientStripAnimation](https://github.com/user-attachments/assets/8f1fb519-3bed-487e-8520-34e807ae3aa7)
![Screenshot_٢٠٢٤٠٨١٩-١٩٣٥٣٤_GradientStripAnimation](https://github.com/user-attachments/assets/d3cf6407-26c5-425c-ad82-f556ea096b21)
![Screenshot_٢٠٢٤٠٨١٩-٠٢١٦٢٢_GradientStripAnimation](https://github.com/user-attachments/assets/382ee354-67b4-4543-a243-72f551619e58)
![Screenshot_٢٠٢٤٠٨١٩-٠٢١٦٣٩_GradientStripAnimation](https://github.com/user-attachments/assets/9d50aca2-acbb-4b9e-bff9-7bb9755be018)
![Screenshot_٢٠٢٤٠٨١٩-٠٢١٧٤٩_GradientStripAnimation](https://github.com/user-attachments/assets/1393c5e7-dfd3-4b9d-86ec-c81884db5a56)



## How to Use the Library? ![GitHub top language](https://img.shields.io/github/languages/top/alex11111115/GradientStripAnimation?style=flat&color=red)

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
    implementation 'com.github.alex11111115:GradientStripAnimation:1.2'
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
configs.add(new GradientStripAnimation.StripConfig(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(18), 
 new int[]{0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591, 0xFF34A79A, 0xFF8D8CD3 /*You can add more colors to infinity according to your need.*/})
 .setCornerRadius(dpToPx(4))
 .setEnableShadow(true)
 .setShadowColor(0x66222327)
 .setShadowRadius(dpToPx(1))
 .setShadowDx(0)
 .setShadowDy(dpToPx(0)));

// Strip 2: The Ocean's Depth
configs.add(new GradientStripAnimation.StripConfig(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(18), 
 new int[]{0xFF8D8CD3, 0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591, 0xFF34A79A /*You can add more colors to infinity according to your need.*/})
 .setCornerRadius(dpToPx(4))
 .setEnableShadow(true)
 .setShadowColor(0x66222327)
 .setShadowRadius(dpToPx(1))
 .setShadowDx(0)
 .setShadowDy(dpToPx(0)));

// Strip 3: The Celestial Dance
configs.add(new GradientStripAnimation.StripConfig(dpToPx(450), dpToPx(18), 
 new int[]{0xFF34A79A, 0xFF8D8CD3, 0xFF8EA3FE, 0xFFA179C6, 0xFFB44F8F, 0xFF946591 /*You can add more colors to infinity according to your need.*/})
 .setCornerRadius(dpToPx(4))
 .setEnableShadow(true)
 .setShadowColor(0x66222327)
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
        // Add your own method here so that when the animation is finished something happens like text appears etc.
    }).startAnimation();
```

### Step 4: Very important function

Add this method in your activity or class to convert dp to Px

```java
private int dpToPx(int dp) {
    float density = context.getResources().getDisplayMetrics().density;
    return Math.round((float) dp * density);
}
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

## Credits

GradientStripAnimation is developed and maintained by [alex11111115](https://github.com/alex11111115).

## Contribution

We welcome contributions from the community! If you have ideas or improvements, feel free to submit pull requests or open issues on the [GitHub repository](https://github.com/alex11111115/GradientStripAnimation). 

## License

WavePlayerView is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt). ![GitHub license](https://img.shields.io/github/license/alex11111115/GradientStripAnimation?style=flat&color=blue)

[![StandWithPalestine](https://raw.githubusercontent.com/Safouene1/support-palestine-banner/master/StandWithPalestine.svg)](https://techforpalestine.org/learn-more) [![StandWithPalestineBadgeBordered](https://raw.githubusercontent.com/saedyousef/StandWithPalestine/main/badges/flat/bordered/StandWithPalestine.svg)](https://techforpalestine.org/learn-more)

## More Info

<div style="display: flex; gap: 10px; flex-wrap: wrap;">
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
    <p>Total Downloads</p>
    <img src="https://img.shields.io/github/downloads/alex11111115/GradientStripAnimation/total?style=flat&color=brightgreen" alt="Total Downloads">
  </div>
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
    <p>Repo Size</p>
    <img src="https://img.shields.io/github/repo-size/alex11111115/GradientStripAnimation?style=flat&color=blue" alt="Repo Size">
  </div>
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
    <p>Code Size</p>
    <img src="https://img.shields.io/github/languages/code-size/alex11111115/GradientStripAnimation?style=flat&color=orange" alt="Code Size">
  </div>
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
    <p>Last Commit</p>
    <img src="https://img.shields.io/github/last-commit/alex11111115/GradientStripAnimation?style=flat&color=yellow" alt="Last Commit">
  </div>
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
    <p>Latest Release</p>
    <img src="https://img.shields.io/github/v/release/alex11111115/GradientStripAnimation?style=flat&color=blue" alt="Latest Release">
  </div>
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
    <p>Project Status</p>
    <img src="https://img.shields.io/badge/status-active-brightgreen?style=flat" alt="Project Status">
  </div>
  <div style="border: 1px solid #e1e4e8; border-radius: 5px; padding: 10px; width: 200px; text-align: center;">
