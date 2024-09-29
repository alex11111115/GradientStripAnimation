[![StandWithPalestine](https://raw.githubusercontent.com/karim-eg/StandWithPalestine/main/assets/palestine_badge.svg)](https://github.com/karim-eg/StandWithPalestine)   [![](https://jitpack.io/v/alex11111115/GradientStripAnimation.svg)](https://jitpack.io/#alex11111115/GradientStripAnimation) 

[![ReadMeSupportPalestine](https://raw.githubusercontent.com/Safouene1/support-palestine-banner/master/banner-support.svg)](https://techforpalestine.org/learn-more)

# 🌈✨ GradientStripAnimation: Revolutionize Your Android UI! 🚀🔥

[![API](https://img.shields.io/badge/API-19%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=19)
![GitHub top language](https://img.shields.io/github/languages/top/alex11111115/GradientStripAnimation?style=flat&color=red)

Tired of boring, static UIs? 😴 Say hello to the future of Android design! 🎉 GradientStripAnimation brings your layouts to life with mind-blowing, customizable gradient animations that will leave your users in awe! 🤯💖

![GradientStripAnimation Demo](https://github.com/user-attachments/assets/8e66fe51-57a0-40e0-8b56-a77ace5eaa81)

## 🚀 Features That Will Blow Your Mind

- 🌊 Hypnotic, endless gradient flows that defy physics
- 🎨 Infinite color combinations to match any theme
- ⏱️ Precision timing control for perfectly synced animations
- 🔧 Flexible configurations for ultimate creative freedom
- 🎭 Eye-popping shadow effects for that extra oomph
- 🏎️ Blazing-fast performance that won't slow you down

## 💥 Why GradientStripAnimation Will Change Your Life

1. **🔥 Skyrocket User Engagement**: Watch those retention rates soar!
2. **🦄 Stand Out from the Crowd**: Leave boring apps in the dust
3. **🎨 Unleash Your Inner Artist**: Create UI masterpieces effortlessly
4. **🚀 Boost Performance**: Smooth animations without the lag

## 🎬 See the Magic in Action

### 🕰️ Perfect Timing, Every Time
Watch GradientStripAnimation dance to your app's rhythm:

[Witness the Timed Magic](https://github.com/user-attachments/assets/3aff3def-c0b4-4788-89b1-b6be0c76a7dd)

### ✨ Shimmer Like a Superstar
Upgrade your loading screens from meh to marvelous:

[Experience the Shimmer](https://github.com/user-attachments/assets/0ec8b304-49bd-4e0e-a68a-7a013d216951)

### 📸 Feast Your Eyes on These Beauties

Prepare to be mesmerized:

![Mind-Blowing Design 1](https://github.com/user-attachments/assets/72eec53b-5eee-4dd3-bce3-5bea385a614c)
![Stunning Visual 2](https://github.com/user-attachments/assets/18b396b2-b9e4-4660-8509-eaf0090955cc)
![UI Magic 3](https://github.com/user-attachments/assets/b377bb33-18bf-4626-9534-58dbaaf33eb3)

## 🛠️ Installation: Let's Get This Party Started!

### 🎵 Gradle Groove
1. Drop this beat in your root `build.gradle`:

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

2. Now, spice up your app `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.alex11111115:GradientStripAnimation:1.2'
}
```

## 🚀 Usage: Time to Create Some Magic!

### 🌟 Basic Spell Casting

1. Prepare your canvas:

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

2. Mix your potion (configure those strips):

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

3. Wave your wand (start the animation):

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
### 🧙‍♂️ Advanced Sorcery

- 🔧 Tweak dimensions, colors, and curves to your heart's content
- 🕴️ Levitate your UI with gravity-defying shadow effects
- 🎭 Orchestrate multiple animations for a symphony of motion
- 🚦 Transform loading screens from dull to dazzling

## 🏆 Pro Tips for UI Wizards

1. 🌈 Blend complementary colors for eye-catching gradients
2. 🧪 Experiment with strip counts - sometimes less is more, sometimes more is MORE!
3. 🔄 Sync animations with user actions for an interactive wonderland
4. 🚀 Keep it smooth - balance beauty with performance for the ultimate UX

## 🚨 Warning: Side Effects May Include

- Increased user happiness
- Uncontrollable urges to stare at your app for hours
- Sudden realization that static UIs are a thing of the past
- Desire to redesign your entire app (or life) around these animations

## 🌟 Conclusion: Embrace the Future of UI

Don't just build apps – create experiences. With `GradientStripAnimation`, you're not just a developer; you're a digital artist, a maestro of motion, a sorcerer of the screen. 

So, are you ready to elevate your UI game and leave the mundane world behind? The power is in your hands. Use it wisely, and watch as your app becomes the talk of the town, the envy of your peers, and the object of your users' undying affection.

Remember: In a world of static, be the motion. In a sea of boring, be the `GradientStripAnimation`. 🌈✨ #GradientStripAnimation

## Credits

GradientStripAnimation is developed and maintained by [alex11111115](https://github.com/alex11111115).

## Contribution

We welcome contributions from the community! If you have ideas or improvements, feel free to submit pull requests or open issues on the [GitHub repository](https://github.com/alex11111115/GradientStripAnimation). 

## License

GradientStripAnimation is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt). ![GitHub license](https://img.shields.io/github/license/alex11111115/GradientStripAnimation?style=flat&color=blue)

[![StandWithPalestine](https://raw.githubusercontent.com/Safouene1/support-palestine-banner/master/StandWithPalestine.svg)](https://techforpalestine.org/learn-more) [![StandWithPalestineBadgeBordered](https://raw.githubusercontent.com/saedyousef/StandWithPalestine/main/badges/flat/bordered/StandWithPalestine.svg)](https://techforpalestine.org/learn-more)

---

🚀 Ready to transform your Android app from snooze-fest to spectacular? GradientStripAnimation is your ticket to the UI big leagues. Don't just build apps - create experiences that users will rave about! Let's make your app the talk of the Play Store! 🌟🎉

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
