package com.apm.core.utils;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * Created by Ing. Oscar G. Medina Cruz on 14/12/15.
 *
 * Create and personalize animations based on Android Animation class, or use Animation resource.
 */
public class AnimateUtils {

    //region RESOURCE ANIMATIONS
    /**
     * Animate view using animation resource
     *
     * @param context      current application context
     * @param viewToAnim   view that plays animation
     * @param hideAtEnd    if the view will hide when the animation finishes
     * @param isInvisible  if the view current visibility state is invisible
     * @param animResource animation resource id
     */
    public static void AnimateView(Context context, final View viewToAnim, final boolean hideAtEnd,
                                   final boolean isInvisible, int animResource) {
        Animation animation = AnimationUtils.loadAnimation(context, animResource);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hideAtEnd) {
                    if (isInvisible)
                        viewToAnim.setVisibility(View.INVISIBLE);
                    else
                        viewToAnim.setVisibility(View.GONE);
                } else
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(animation);
    }

    /**
     * Animate view using animation resource
     *
     * @param context      current application context
     * @param viewToAnim   view that plays animation
     * @param hideAtEnd    if the view will hide when the animation finishes
     * @param isInvisible  if the view current visibility state is invisible
     * @param overrideDuration if we need other duration than the stablished in animation resource
     * @param animResource animation resource id
     */
    public static void AnimateView(Context context, final View viewToAnim, final boolean hideAtEnd,
                                   final boolean isInvisible, int overrideDuration, int animResource) {
        Animation animation = AnimationUtils.loadAnimation(context, animResource);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hideAtEnd) {
                    if (isInvisible)
                        viewToAnim.setVisibility(View.INVISIBLE);
                    else
                        viewToAnim.setVisibility(View.GONE);
                } else
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setDuration(overrideDuration);
        viewToAnim.startAnimation(animation);
    }

    /**
     * Animate view using animation resource and using custom animation listener
     *
     * @param context           current application context
     * @param viewToAnim        view tat plays animation
     * @param animResource      animation resource
     * @param animationListener custom animation event listener
     */
    public static void AnimateView(Context context, final View viewToAnim, int animResource,
                                   Animation.AnimationListener animationListener) {
        Animation animation = AnimationUtils.loadAnimation(context, animResource);
        animation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(animation);
    }
    //endregion

    //region SCALE IN ANIMATIONS

    /**
     * Scale in animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim   view that plays animation
     * @param visibleAtEnd if the view will show when the animation finishes
     * @param isInvisible  if the view current visibility state is invisible
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     */
    public static void ScaleIn(final View viewToAnim, int startOffset, int duration,
                               final boolean visibleAtEnd, final boolean isInvisible) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isInvisible)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibleAtEnd)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param animationListener custom animation event listener
     */
    public static void ScaleIn(View viewToAnim, int startOffset, int duration,
                               Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param animationListener custom animation event listener
     */
    public static void ScaleIn(View viewToAnim, int startOffset, int duration, int relativeToValue,
                               float pivotX, float pivotY, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim   view that plays animation
     * @param visibleAtEnd if the view will show when the animation finishes
     * @param isInvisible  if the view current visibility state is invisible
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param interpolator custom animation interpolator
     */
    public static void ScaleIn(final View viewToAnim, int startOffset, int duration, final boolean visibleAtEnd, final boolean isInvisible, Interpolator interpolator) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isInvisible)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibleAtEnd)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     */
    public static void ScaleIn(View viewToAnim, int startOffset, int duration, Interpolator interpolator, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setFillEnabled(true);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     */
    public static void ScaleIn(View viewToAnim, int startOffset, int duration, int relativeToValue, float pivotX, float pivotY, Interpolator interpolator, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Build scale in animation, optimizing visibility state and animation parameters
     *
     * @param startOffset start offset of the animation
     * @param duration    animation duration
     * @param fromValue   start value of animation (Scale width, scale height)
     * @param toValue     end value of animation (Scale width, scale height)
     * @return ScaleInAnimation
     */
    public static ScaleAnimation BuildScaleInAnimation(int startOffset, int duration, float fromValue, float toValue) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        return scaleAnimation;
    }

    /**
     * Build scale in animation, optimizing visibility state and animation parameters
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param animationListener custom animation event listener
     * @return ScaleInAnimation
     */
    public static ScaleAnimation BuildScaleInAnimation(int startOffset, int duration, float fromValue, float toValue, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param animationListener custom animation event listener
     * @return ScaleInAnimation
     */
    public static ScaleAnimation BuildScaleInAnimation(int startOffset, int duration, float fromValue, float toValue, int relativeToValue, float pivotX, float pivotY, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * Build scale in animation, optimizing visibility state and animation parameters
     *
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param fromValue    start value of animation (Scale width, scale height)
     * @param toValue      end value of animation (Scale width, scale height)
     * @param interpolator custom animation interpolator
     * @return ScaleInAnimation
     */
    public static ScaleAnimation BuildScaleInAnimation(int startOffset, int duration, float fromValue, float toValue, Interpolator interpolator) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        return scaleAnimation;
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     * @return ScaleInAnimation
     */
    public static ScaleAnimation BuildScaleInAnimation(int startOffset, int duration, float fromValue, float toValue, Interpolator interpolator, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     * @return ScaleInAnimation
     */
    public static ScaleAnimation BuildScaleInAnimation(int startOffset, int duration, float fromValue, float toValue, int relativeToValue, float pivotX, float pivotY, Interpolator interpolator, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
    //endregion

    //region SCALE OUT ANIMATION

    /**
     * Scale out animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim  view that plays animation
     * @param hideAtEnd   if the view will hide when the animation finishes
     * @param isInvisible if the view current visibility state is invisible
     * @param startOffset start offset of the animation
     * @param duration    animation duration
     */
    public static void ScaleOut(final View viewToAnim, int startOffset, int duration,
                                final boolean hideAtEnd, final boolean isInvisible) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0, 1f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hideAtEnd)
                    if (isInvisible)
                        viewToAnim.setVisibility(View.INVISIBLE);
                    else
                        viewToAnim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale out animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param animationListener custom animation event listener
     */
    public static void ScaleOut(View viewToAnim, int startOffset, int duration,
                                Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0, 1f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param animationListener custom animation event listener
     */
    public static void ScaleOut(View viewToAnim, int startOffset, int duration, int relativeToValue,
                                float pivotX, float pivotY, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0, 1f, 0, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale out animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim   view that plays animation
     * @param hideAtEnd    if the view will hide when the animation finishes
     * @param isInvisible  if the view current visibility state is invisible
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param interpolator custom animation interpolator
     */
    public static void ScaleOut(final View viewToAnim, int startOffset, int duration,
                                final boolean hideAtEnd, final boolean isInvisible, Interpolator interpolator) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0, 1f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hideAtEnd)
                    if (isInvisible)
                        viewToAnim.setVisibility(View.INVISIBLE);
                    else
                        viewToAnim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale out animation, optimizing visibility state and animation parameters
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     */
    public static void ScaleOut(View viewToAnim, int startOffset, int duration, Interpolator interpolator,
                                Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0, 1f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setFillEnabled(true);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Scale in animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     */
    public static void ScaleOut(View viewToAnim, int startOffset, int duration, int relativeToValue,
                                float pivotX, float pivotY, Interpolator interpolator,
                                Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0, 1f, 0, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(scaleAnimation);
    }

    /**
     * Build scale out animation, optimizing visibility state and animation parameters
     *
     * @param startOffset start offset of the animation
     * @param duration    animation duration
     * @param fromValue   start value of animation (Scale width, scale height)
     * @param toValue     end value of animation (Scale width, scale height)
     * @return ScaleOutAnimation
     */
    public static ScaleAnimation BuildScaleOutAnimation(int startOffset, int duration,
                                                        float fromValue, float toValue) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        return scaleAnimation;
    }

    /**
     * Build scale out animation, optimizing visibility state and animation parameters
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param animationListener custom animation event listener
     * @return ScaleOutAnimation
     */
    public static ScaleAnimation BuildScaleOutAnimation(int startOffset, int duration, float fromValue,
                                                        float toValue, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * Build scale out animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param animationListener custom animation event listener
     * @return ScaleOutAnimation
     */
    public static ScaleAnimation BuildScaleOutAnimation(int startOffset, int duration, float fromValue,
                                                        float toValue, int relativeToValue, float pivotX,
                                                        float pivotY, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * Build scale out animation, optimizing visibility state and animation parameters
     *
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param fromValue    start value of animation (Scale width, scale height)
     * @param toValue      end value of animation (Scale width, scale height)
     * @param interpolator custom animation interpolator
     * @return ScaleOutAnimation
     */
    public static ScaleAnimation BuildScaleOutAnimation(int startOffset, int duration, float fromValue,
                                                        float toValue, Interpolator interpolator) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        return scaleAnimation;
    }

    /**
     * Build scale out animation, optimizing visibility state and animation parameters
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     * @return ScaleOutAnimation
     */
    public static ScaleAnimation BuildScaleOutAnimation(int startOffset, int duration, float fromValue,
                                                        float toValue, Interpolator interpolator,
                                                        Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * Build scale out animation, optimizing visibility state and animation parameters, and declaring pivot points for the animation
     *
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param fromValue         start value of animation (Scale width, scale height)
     * @param toValue           end value of animation (Scale width, scale height)
     * @param relativeToValue   relative point to the animation
     * @param pivotX            pivot point at x axis
     * @param pivotY            pivot point at y axis
     * @param interpolator      custom animation interpolator
     * @param animationListener custom animation event listener
     * @return ScaleOutAnimation
     */
    public static ScaleAnimation BuildScaleOutAnimation(int startOffset, int duration, float fromValue,
                                                        float toValue, int relativeToValue, float pivotX,
                                                        float pivotY, Interpolator interpolator,
                                                        Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromValue, toValue, fromValue, toValue, relativeToValue, pivotX, relativeToValue, pivotY);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    //endregion

    //region ROTATE ANIMATION

    /**
     * Rotate view with animation with certain parameters
     *
     * @param viewToAnim  view that plays animation
     * @param fillAfter   if the view preserve the final state of the animation
     * @param startOffset start offset of the animation
     * @param duration    animation duration
     * @param fromDegrees initial degrees of animation
     * @param toDegrees   final degrees of animation
     */
    public static void Rotate(View viewToAnim, final boolean fillAfter, int startOffset,
                              int duration, float fromDegrees, float toDegrees) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setStartOffset(startOffset);
            rotateAnimation.setInterpolator(new OvershootInterpolator());
            rotateAnimation.setDuration(duration);
            rotateAnimation.setFillEnabled(fillAfter);
            rotateAnimation.setFillAfter(fillAfter);
            viewToAnim.startAnimation(rotateAnimation);
        } else {
            RotateCompat(viewToAnim, duration, fromDegrees, toDegrees);
        }
    }

    /**
     * Rotate view with animation with certain parameters
     *
     * @param viewToAnim   view that plays animation
     * @param fillAfter    if the view preserve the final state of the animation
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param fromDegrees  initial degrees of animation
     * @param toDegrees    final degrees of animation
     * @param interpolator custom animation interpolator
     */
    public static void Rotate(View viewToAnim, final boolean fillAfter, int startOffset,
                              int duration, float fromDegrees, float toDegrees, Interpolator interpolator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setStartOffset(startOffset);
            rotateAnimation.setInterpolator(interpolator);
            rotateAnimation.setDuration(duration);
            rotateAnimation.setFillEnabled(fillAfter);
            rotateAnimation.setFillAfter(fillAfter);
            viewToAnim.startAnimation(rotateAnimation);
        } else {
            RotateCompat(viewToAnim, duration, fromDegrees, toDegrees, interpolator);
        }
    }

    /**
     * Rotate animation for pre lollipop devices
     *
     * @param viewToAnim  view that plays animation
     * @param duration    animation duration
     * @param fromDegrees initial degrees of animation
     * @param toDegrees   final degrees of animation
     */
    private static void RotateCompat(View viewToAnim, int duration, float fromDegrees, float toDegrees) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewToAnim, "rotation", fromDegrees, toDegrees);
        objectAnimator.setInterpolator(new OvershootInterpolator());
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    /**
     * Rotate animation for pre lollipop devices
     *
     * @param viewToAnim   view that plays animation
     * @param duration     animation duration
     * @param fromDegrees  initial degrees of animation
     * @param toDegrees    final degrees of animation
     * @param interpolator custom animation interpolator
     */
    private static void RotateCompat(View viewToAnim, int duration, float fromDegrees,
                                     float toDegrees, Interpolator interpolator) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewToAnim, "rotation", fromDegrees, toDegrees);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    //endregion

    //region FADE IN ANIMATION

    /**
     * Fade in view with animation
     *
     * @param viewToAnim   view that plays animation
     * @param visibleAtEnd if the
     * @param isInvisible  if the view current visibility state is invisible
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     */
    public static void FadeIn(final View viewToAnim, final boolean visibleAtEnd,
                              final boolean isInvisible, int startOffset, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isInvisible)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibleAtEnd)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(alphaAnimation);
    }

    /**
     * Fade in view with animation, and custom animation listener
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param animationListener custom animation event listener
     */
    public static void FadeIn(View viewToAnim, int startOffset, int duration, Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(alphaAnimation);
    }

    /**
     * Fade in view with animation
     *
     * @param viewToAnim   view that plays animation
     * @param visibleAtEnd if the
     * @param isInvisible  if the view current visibility state is invisible
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param interpolator custom animation interpolator
     */
    public static void FadeIn(final View viewToAnim, final boolean visibleAtEnd,
                              final boolean isInvisible, int startOffset, int duration,
                              Interpolator interpolator) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isInvisible)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibleAtEnd)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(alphaAnimation);
    }

    /**
     * Fade in view with animation, and custom animation listener
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param animationListener custom animation event listener
     * @param interpolator      custom animation interpolator
     */
    public static void FadeIn(View viewToAnim, int startOffset, int duration, Interpolator interpolator,
                              Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(alphaAnimation);
    }

    //endregion

    //region FADE OUT ANIMATION

    /**
     * Fade out view with animation
     *
     * @param viewToAnim  view that plays animation
     * @param hideAtEnd   if the view will hide when the animation finishes
     * @param isInvisible if the view current visibility state is invisible
     * @param startOffset start offset of the animation
     * @param duration    animation duration
     */
    public static void FadeOut(final View viewToAnim, final boolean hideAtEnd, final boolean isInvisible,
                               int startOffset, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hideAtEnd)
                    if (isInvisible)
                        viewToAnim.setVisibility(View.INVISIBLE);
                    else
                        viewToAnim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(alphaAnimation);
    }

    /**
     * Fade out view with animation, and custom animation listener
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param animationListener custom animation event listener
     */
    public static void FadeOut(View viewToAnim, int startOffset, int duration, Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(alphaAnimation);
    }

    /**
     * Fade out view with animation
     *
     * @param viewToAnim   view that plays animation
     * @param hideAtEnd    if the view will hide when the animation finishes
     * @param isInvisible  if the view current visibility state is invisible
     * @param startOffset  start offset of the animation
     * @param duration     animation duration
     * @param interpolator custom animation interpolator
     */
    public static void FadeOut(final View viewToAnim, final boolean hideAtEnd, final boolean isInvisible,
                               int startOffset, int duration, Interpolator interpolator) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hideAtEnd)
                    if (isInvisible)
                        viewToAnim.setVisibility(View.INVISIBLE);
                    else
                        viewToAnim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(alphaAnimation);
    }

    /**
     * Fade out view with animation, and custom animation listener
     *
     * @param viewToAnim        view that plays animation
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param animationListener custom animation event listener
     * @param interpolator      custom animation interpolator
     */
    public static void FadeOut(View viewToAnim, int startOffset, int duration, Interpolator interpolator,
                               Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(alphaAnimation);
    }

    //endregion

    //region TRANSLATE ANIMATIONS

    /**
     * Translate view in specific values of X and Y
     *
     * @param viewToAnim  view that plays animation
     * @param fromX       start value of translation in X
     * @param fromY       start value of translation in Y
     * @param toX         end value of translation in X
     * @param toY         end value of translation in Y
     * @param startOffset start offset of the animation
     * @param duration    animation duration
     */
    public static void TranslateView(final View viewToAnim, float fromX, float fromY, float toX, float toY,
                                     int startOffset, int duration) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setDuration(duration);
        viewToAnim.startAnimation(translateAnimation);
    }

    /**
     * Translate view in specific values of X and Y
     *
     * @param viewToAnim    view that plays animation
     * @param fromX         start value of translation in X
     * @param fromY         start value of translation in Y
     * @param toX           end value of translation in X
     * @param toY           end value of translation in Y
     * @param startOffset   start offset of the animation
     * @param duration      animation duration
     * @param visibleAtEnd  view is visible at end of animation
     * @param isInvisible   view is invisible at start of animation
     */
    public static void TranslateView(final View viewToAnim, float fromX, float fromY, float toX, float toY,
                                     int startOffset, int duration, final boolean visibleAtEnd, final boolean isInvisible) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isInvisible)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibleAtEnd)
                    viewToAnim.setVisibility(View.VISIBLE);
                else if (isInvisible) {
                    viewToAnim.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(translateAnimation);
    }

    /**
     * Translate view in specific values of X and Y
     *
     * @param viewToAnim    view that plays animation
     * @param fromX         start value of translation in X
     * @param fromY         start value of translation in Y
     * @param toX           end value of translation in X
     * @param toY           end value of translation in Y
     * @param startOffset   start offset of the animation
     * @param duration      animation duration
     * @param animationListener listener of the animation
     */
    public static void TranslateView(final View viewToAnim, float fromX, float fromY, float toX, float toY,
                                     int startOffset, int duration, Animation.AnimationListener animationListener) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(translateAnimation);
    }

    /**
     * Translate view in specific values of X and Y
     *
     * @param viewToAnim    view that plays animation
     * @param fromX         start value of translation in X
     * @param fromY         start value of translation in Y
     * @param toX           end value of translation in X
     * @param toY           end value of translation in Y
     * @param startOffset   start offset of the animation
     * @param duration      animation duration
     * @param interpolator  interpolator for animation
     */
    public static void TranslateView(final View viewToAnim, float fromX, float fromY, float toX, float toY,
                                     int startOffset, int duration, Interpolator interpolator) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setDuration(duration);
        viewToAnim.startAnimation(translateAnimation);
    }

    /**
     * Translate view in specific values of X and Y
     *
     * @param viewToAnim    view that plays animation
     * @param fromX         start value of translation in X
     * @param fromY         start value of translation in Y
     * @param toX           end value of translation in X
     * @param toY           end value of translation in Y
     * @param startOffset   start offset of the animation
     * @param duration      animation duration
     * @param visibleAtEnd  view is visible at end of animation
     * @param isInvisible   view is invisible at start of animation
     * @param interpolator  animation interpolator
     */
    public static void TranslateView(final View viewToAnim, float fromX, float fromY, float toX, float toY,
                                     int startOffset, int duration, final boolean visibleAtEnd,
                                     final boolean isInvisible, Interpolator interpolator) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isInvisible)
                    viewToAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibleAtEnd)
                    viewToAnim.setVisibility(View.VISIBLE);
                else if (isInvisible) {
                    viewToAnim.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewToAnim.startAnimation(translateAnimation);
    }

    /**
     * Translate view in specific values of X and Y
     *
     * @param viewToAnim        view that plays animation
     * @param fromX             start value of translation in X
     * @param fromY             start value of translation in Y
     * @param toX               end value of translation in X
     * @param toY               end value of translation in Y
     * @param startOffset       start offset of the animation
     * @param duration          animation duration
     * @param interpolator      animation interpolator
     * @param animationListener listener of the animation
     */
    public static void TranslateView(final View viewToAnim, float fromX, float fromY, float toX, float toY,
                                     int startOffset, int duration, Interpolator interpolator,
                                     Animation.AnimationListener animationListener) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(animationListener);
        viewToAnim.startAnimation(translateAnimation);
    }
    //endregion

    //region COUNT ANIMATIONS

    /**
     * Animate count of numeric values
     *
     * @param textView      TextView when the numeric value appear
     * @param startCount    Numeric value of animation start
     * @param endCount      Numeric value of animation end
     * @param duration      Animation duration
     */
    public static void AnimateCount(final TextView textView, float startCount, float endCount, int duration){
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(startCount, endCount);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setEvaluator(new TypeEvaluator<Float>() {
            public Float evaluate(float fraction, Float startValue, Float endValue) {
                return (float) Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(duration);
        animator.start();
    }

    public static void AnimateCount(final TextView textView, float startCount, float endCount, int duration, final int formatResource){
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(startCount, endCount);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(textView.getContext().getString(formatResource, animation.getAnimatedValue()));
            }
        });
        animator.setEvaluator(new TypeEvaluator<Float>() {
            public Float evaluate(float fraction, Float startValue, Float endValue) {
                return (float) Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(duration);
        animator.start();
    }
    //endregion
}
