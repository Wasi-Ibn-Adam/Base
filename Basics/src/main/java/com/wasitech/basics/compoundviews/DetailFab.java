package com.wasitech.basics.compoundviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wasitech.basics.R;
import com.wasitech.basics.theme.Theme;

public class DetailFab extends LinearLayout {
    private TextView text;
    private FloatingActionButton fab;
    private LinearLayout layout;

    public DetailFab(Context context) {
        super(context);
        init(context, null);
    }

    public DetailFab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DetailFab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public DetailFab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_detail_fab, this);
        text = findViewById(R.id.view_text);
        fab = findViewById(R.id.view_fab);
        layout = findViewById(R.id.view_lay);
        setTheme();

        if (attrs == null)
            return;
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DetailFab, 0, 0);
        text.setText(typedArray.getString(R.styleable.DetailFab_text));
        fab.setImageResource(typedArray.getResourceId(R.styleable.DetailFab_srcCompat, R.drawable.ic_launcher_background));
    }

    public void setTheme() {
        text.setBackgroundTintList(Theme.Colors.getDefaultColorList());
        text.setTextColor(Theme.Colors.getDefaultOppositeColors());
    }

    public void setListener(OnClickListener listener) {
        fab.setOnClickListener(listener);
    }

    public void setAnimationGone(long l) {
        new Handler().postDelayed(() -> {
            text.setVisibility(View.INVISIBLE);
            text.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.hide_text));
        }, l);
        new Handler().postDelayed(() -> {
            layout.setVisibility(View.GONE);
            layout.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
        }, l + 10);
    }

    public void setAnimationVisible(long l) {
        new Handler().postDelayed(() -> {
            layout.setVisibility(View.VISIBLE);
            layout.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
        }, l);
        new Handler().postDelayed(() -> {
            text.setVisibility(View.VISIBLE);
            text.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.show_text));
        }, l + 10);
    }

    public void setVisibility(int visibility) {
        text.setVisibility(visibility);
        layout.setVisibility(visibility);
    }

    public void setText(String txt) {
        text.setText(txt);
    }

    public void setImageResource(int res) {
        fab.setImageResource(res);
    }

    public TextView getText() {
        return text;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public LinearLayout getLayout() {
        return layout;
    }
}
