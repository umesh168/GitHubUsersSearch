package com.umesh.github.app.githubsearch.views.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * @author Umesh
 *
 */
public class AnimUtils {

	public static Animation getFadeInAnimation(long durationMillis) {
		
		return getFadeInAnimation(durationMillis, null);
	}
	
	public static Animation getFadeInAnimation(long durationMillis, Animation.AnimationListener animationListener) {
		
		Animation fadeInAnimation = new AlphaAnimation(0, 1);
		fadeInAnimation.setDuration(durationMillis);
		if(null != animationListener) {
			fadeInAnimation.setAnimationListener(animationListener);
		}
		return fadeInAnimation;
	}
	
	public static Animation getFadeOutAnimation(long durationMillis) {
		
		return getFadeOutAnimation(durationMillis, null);
	}
	
	public static Animation getFadeOutAnimation(long durationMillis, Animation.AnimationListener animationListener) {
		
		Animation fadeOutAnimation = new AlphaAnimation(1, 0);
		fadeOutAnimation.setDuration(durationMillis);
		if(null != animationListener) {
			fadeOutAnimation.setAnimationListener(animationListener);
		}
		return fadeOutAnimation;
	}
	
	public static void animateView(View view, Context context, int resId, Animation.AnimationListener animationListener) {
		
		Animation animation = AnimationUtils.loadAnimation(context, resId);
		if(null != animationListener) {
			animation.setAnimationListener(animationListener);
		}
		view.startAnimation(animation);
	}

}
