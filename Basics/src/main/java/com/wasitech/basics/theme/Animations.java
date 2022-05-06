package com.wasitech.basics.theme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.wasitech.basics.R;
import com.wasitech.basics.classes.Issue;

public class Animations {
    public static void slideUpShow(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),         // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
    public static void slideUpHide(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),         // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
    public static void slideDownShow(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),                 // fromYDelta
                0); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
    public static void slideDownHide(View view){
        view.animate()
                .translationY(view.getHeight())
                .alpha(0.0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
        
    }


    /**
     *  shake(View v)   250ms <br>
     *  upDown(View v)  1600ms <br>
     *  zoom(View v)    200ms <br>
     *  colorChange(View v) infinite <br>
     *  rotateItSelf(View v) i=24 && i*150  times <br>
     * */
    public static void shake(View view) {
        try {
            if (view == null) return;
            RotateAnimation rotate = new RotateAnimation(-5, 5, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(250);
            rotate.setStartOffset(50);
            rotate.setRepeatMode(Animation.REVERSE);
            rotate.setInterpolator(new CycleInterpolator(5));
            view.startAnimation(rotate);
        } catch (Exception e) {
            Issue.print(e, Animations.class.getName());
        }
    }
    public static void shake(View view,long time) {
        try {
            if (view == null) return;
            RotateAnimation rotate = new RotateAnimation(-5, 5, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(time);
            rotate.setStartOffset(50);
            rotate.setRepeatMode(Animation.REVERSE);
            rotate.setInterpolator(new CycleInterpolator(5));
            view.startAnimation(rotate);
        } catch (Exception e) {
            Issue.print(e, view.getClass().getName());
        }
    }
    public void upDown(View view) {
        try {
            if (view == null) return;
            Animation animUpDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.updown);
            view.startAnimation(animUpDown);
        } catch (Exception e) {
            Issue.print(e, getClass().getName());
        }
    }
    public static void zoom(View view) {
        try {
            if (view == null) return;
            Animation animUpDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.zoom);
            view.startAnimation(animUpDown);
        } catch (Exception e) {
            Issue.print(e, Animations.class.getName());
        }
    }
    private static int num=0;
    public static void rotateItself(View view) {
        try {
            if (view == null) return;
            for (int i = 0; i < 24; i++) {
                new Handler().postDelayed(() -> { Animations.num += 15;view.setRotation(Animations.num ); }, i * 150);
            }
        } catch (Exception e) {
            Issue.print(e, Animations.class.getName());
        }
    }
    public static void rotateItself(View view,long t) {
        try {
            if (view == null) return;
            for (int i = 0; i < 24; i++) {
                new Handler().postDelayed(() -> { Animations.num += 15;view.setRotation(Animations.num ); }, i * t);
            }
        } catch (Exception e) {
            Issue.print(e, Animations.class.getName());
        }
    }
    public static void colorChange(View view) {
        try {
            for (int i = 0; i < 11; i++) {
                if (view == null) return;
                view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + i + "A" + i + "B" + i + "C")));
                view.setBackgroundColor(Color.parseColor("#" + i + "A" + i + "B" + i + "C"));
                i = i % 10;
            }
        } catch (Exception e) {
            Issue.print(e, Animations.class.getName());
        }
    }

}
