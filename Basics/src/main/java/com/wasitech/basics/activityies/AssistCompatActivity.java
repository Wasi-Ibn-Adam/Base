package com.wasitech.basics.activityies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.snackbar.Snackbar;
import com.wasitech.basics.R;
import com.wasitech.basics.classes.Basics;
import com.wasitech.basics.classes.Issue;
import com.wasitech.basics.interfaces.AssistAcInterface;
import com.wasitech.basics.interfaces.AssistDrawOvers;
import com.wasitech.basics.theme.Theme;
import com.wasitech.permissioncenter.CustomGroupedPermission;
import com.wasitech.permissioncenter.Params;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public abstract class AssistCompatActivity extends AppCompatActivity implements AssistAcInterface, AssistDrawOvers {
    protected final ActivityResultLauncher<Intent> head = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            drawOverDenied();
        } else {
            drawOverAct();
        }
    });
    private final int res;

    public AssistCompatActivity(int res) {
        this.res = res;
    }

    protected CustomGroupedPermission permission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (res != 0)
            setContentView(res);
        try {
            setViews();
        } catch (Exception e) {
            Issue.Log(e, "setView");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            setTitle(titleBarText());
        } catch (Exception e) {
            Issue.Log(e, "setTitle");
        }
        try {
            setValues();
        } catch (Exception e) {
            Issue.Log(e, "setValues");
        }
        try {
            setExtras();
        } catch (Exception e) {
            Issue.Log(e, "setExtras");
        }
        try {
            setTheme();
        } catch (Exception e) {
            Issue.Log(e, "setTheme");
        }
        try {
            setActions();
        } catch (Exception e) {
            Issue.Log(e, "setActions");
        }
        try {
            setPermission();
        } catch (Exception e) {
            Issue.Log(e, "setPermission");
        }
        try {
            //startWorkManager();
        } catch (Exception e) {
            Issue.Log(e, "startWorkManager");
        }
    }

    protected String titleBarText() {
        return "";
    }

    /**
     * Preference is SnackBar else Toast
     */
    protected void NotifyToUser(String msg) {
        try {
            Snackbar.make(findViewById(R.id.content), msg, Snackbar.LENGTH_SHORT).show();
        } catch (Exception e) {
            Issue.Log(e, "NotifyToUser(String msg): " + msg);
            try {
                Basics.toasting(this, msg);
            } catch (Exception e1) {
                Issue.Log(e1, "NotifyToUser(String msg): " + msg);
            }
        }
    }

    protected void setToolbar(int id) {
        try {
            androidx.appcompat.widget.Toolbar toolbar = findViewById(id);
            onCreateOptionsMenu(toolbar.getMenu());
            toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        } catch (Exception e) {
            Issue.print(e, getClass().getName());
        }
    }

    protected void setBack() {
        try {
            ImageButton btn = findViewById(R.id.btn_back);
            btn.setOnClickListener(v -> onBackPressed());
        } catch (Exception e) {
            Issue.print(e, getClass().getName());
        }
    }

    protected void setTitle(String txt) {
        try {
            TextView textView = findViewById(R.id.title);
            textView.setText(txt);
        } catch (Exception e) {
            Issue.print(e, getClass().getName());
            try {
                setTitle((CharSequence) txt);
            } catch (Exception e1) {
                Issue.print(e1, getClass().getName());
            }
        }
    }

    protected Menu setToolBar(int id, int menu, Toolbar.OnMenuItemClickListener listener) {
        try {
            Toolbar bar = findViewById(id);
            bar.inflateMenu(menu);
            bar.setOnMenuItemClickListener(listener);
            return bar.getMenu();
        } catch (Exception e) {
            Issue.print(e, getClass().getName());
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permission != null)
            permission.onResult(requestCode);
    }

    /*
        private void startWorkManager() {
            try {
                // AssistAlwaysOn.AlwaysOnService(BaseCompatActivity.this);

                WorkManager manager = WorkManager.getInstance(AssistCompatActivity.this);
                PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(AlwaysOnWork.class, 1, TimeUnit.MINUTES)
                        .setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).build();

                manager.enqueueUniquePeriodicWork("AlwaysOn", ExistingPeriodicWorkPolicy.KEEP, request);

                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
                intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
                BroadcastReceiver mReceiver = new MyReceiver();
                registerReceiver(mReceiver, intentFilter);
            } catch (Exception e) {
                Issue.print(e, getClass().getName());
            }
        }
    */
    private LinearLayout empLay;

    protected void EmptyLay(boolean show) {
        TextPrintedLay(show, "Nothing to Show");
    }

    @SuppressLint("SetTextI18n")
    protected void TextPrintedLay(boolean show, String res) {
        try {
            if (show) {
                if (empLay != null) {
                    empLay.setVisibility(View.VISIBLE);
                    return;
                }
                empLay = new LinearLayout(this);
                empLay.setPadding(10, 0, 0, 10);
                ((ConstraintLayout) findViewById(R.id.bg)).addView(empLay, MATCH_PARENT, MATCH_PARENT);
                empLay.setGravity(Gravity.CENTER);
                {
                    ImageView view = new ImageView(this);
                    empLay.addView(view);
                    view.setImageResource(R.drawable.list);
                    view.getLayoutParams().height = 150;
                    view.getLayoutParams().width = 150;
                    Theme.imageView(view);
                }
                {
                    TextView view = new TextView(this);
                    empLay.addView(view);
                    view.setText(res);
                    view.setTextSize(36);
                    view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    view.getLayoutParams().width = WRAP_CONTENT;
                    view.getLayoutParams().height = WRAP_CONTENT;
                    view.setTypeface(ResourcesCompat.getFont(this, R.font.allura), Typeface.BOLD);
                    view.setPadding(0, 10, 10, 10);
                    Theme.textView(view);
                }

            } else {
                if (empLay != null) {
                    empLay.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            Issue.print(e, getClass().getName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   stopService(PermissionChecker.Intents.AlwaysOn(getApplicationContext()));
    }

    // DRAW OVERS

    @Override
    public void drawOverCheck() {
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            head.launch(Params.Intents.gotoDrawOver(getPackageName()));
        } else {
            drawOverAct();
        }
    }

    @Override
    public void drawOverAct() {
    }

    @Override
    public void drawOverDenied() {
    }

}
