package com.wasitech.basics.compoundviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wasitech.basics.R;
import com.wasitech.basics.classes.Basics;
import com.wasitech.basics.theme.Theme;

import de.hdodenhof.circleimageview.CircleImageView;

public class PictureView extends ConstraintLayout {
    protected CircleImageView img;
    protected FloatingActionButton fab;

    public PictureView(@NonNull Context context) {
        super(context);
    }

    public PictureView(@NonNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PictureView(@NonNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PictureView(@NonNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public View getFabView() {
        return findViewById(R.id.img_edit);
    }

    protected void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_circular_input, this);
        //Get references to text views
        img = findViewById(R.id.user_pic);
        fab = findViewById(R.id.img_edit);
        ConstraintLayout lay = findViewById(R.id.lay);
        Theme.circleImageView(img,3);
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PictureView, 0, 0);
        lay.setMinimumWidth(typedArray.getInteger(R.styleable.PictureView_minMaxWidth, 250));
        lay.setMinimumHeight(typedArray.getInteger(R.styleable.PictureView_minMaxHeight, 250));
        lay.setMaxWidth(typedArray.getInteger(R.styleable.PictureView_minMaxWidth, 250));
        lay.setMaxHeight(typedArray.getInteger(R.styleable.PictureView_minMaxHeight, 250));
    }

    public void smallInit() {
        removeAllViews();
        inflate(getContext(), R.layout.view_small_pic, this);
        //Get references to text views
        img = findViewById(R.id.user_pic);
        fab = findViewById(R.id.img_edit);
        Theme.circleImageView(img,3);
    }


    public void fabIsVisible(boolean state) {
        if (state) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(GONE);
        }
    }

    public void setMaxSize(int w, int h) {
        img.setLayoutParams(new LayoutParams(w, h));
    }

    public void setImg(byte[] bytes) {
        Glide.with(getContext()).load(bytes).into(img);
    }

    public void setImg(byte[] bytes, Uri uri) {
        if (bytes != null)
            setImg(bytes);
        else
            setImg(uri);
    }

    public void setImg(Fragment fr, byte[] bytes) {
        Glide.with(fr).asBitmap().load(bytes).into(img);
    }

    public void setGif(int gif) {
        Glide.with(getContext()).asGif().load(gif).into(img);
    }

    public void setExtraTint(int color) {
        img.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void setImg(Uri uri) {
        Glide.with(getContext()).asBitmap().load(uri).into(img);
    }

    public void setFabListener(OnClickListener listener) {
        fab.setOnClickListener(listener);
    }

    public void setImageListener(OnClickListener listener) {
        img.setOnClickListener(listener);
    }

    public Drawable getDrawable() {
        return img.getDrawable();
    }

    public boolean fabClick() {
        return fab.performClick();
    }

    public Bitmap getBitmap() {
        return Basics.Img.parseBitmap(img.getDrawable());
    }

}
