package com.wasitech.basics.compoundviews;

import android.app.Activity;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wasitech.basics.R;

public abstract class FabToggleButton {
    private final FloatingActionButton btn;
    private boolean check;

    public FabToggleButton(Activity v, int id) {
        btn = v.findViewById(id);
        setChecked(false);
    }

    public void setChecked(boolean state) {
        check = state;
        if (check) {
            btn.setImageResource(R.drawable.microne);
        } else {
            focusOut();
            btn.setImageResource(R.drawable.mic);
        }
    }

    public boolean isChecked() {
        return check;
    }

    public void setOnClick(View.OnClickListener listener) {
        btn.setOnClickListener(listener);
    }

    public abstract void focusOut();
}
