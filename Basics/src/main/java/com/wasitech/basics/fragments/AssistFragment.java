package com.wasitech.basics.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.wasitech.basics.R;
import com.wasitech.basics.classes.Basics;
import com.wasitech.basics.classes.Issue;
import com.wasitech.basics.interfaces.AssistFragInterface;
import com.wasitech.permissioncenter.GroupPermission;
import com.wasitech.permissioncenter.Params;

public abstract class AssistFragment extends Fragment implements AssistFragInterface {
    protected TaskEvents task;
    protected GroupPermission permissions;
    private int code;

    private final ActivityResultLauncher<String[]> launcher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> permissions.onResult(code));

    protected void onPermission(int code) {
        this.code = code;
        launcher.launch(Params.AppPermission.permissionList(code));
    }

    protected void setFixed() {
    }

    protected int setLayout() {
        return 0;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            setViews(view);
        } catch (Exception e) {
            Issue.Log(e, "setViews(view)");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = requireView();
        try {
            setTheme(view);
        } catch (Exception e) {
            Issue.Log(e, "setTheme(view)");
        }
        try {
            setPermission(view);
        } catch (Exception e) {
            Issue.Log(e, "setPermission(view)");
        }
        try {
            setAnimation(view);
        } catch (Exception e) {
            Issue.Log(e, "setAnimation(view)");
        }
        try {
            setValues(view);
        } catch (Exception e) {
            Issue.Log(e, "setValues(view)");
        }
        try {
            setFixed();
        } catch (Exception e) {
            Issue.Log(e, "setFixed()");
        }
        try {
            setActions(view);
        } catch (Exception e) {
            Issue.Log(e, "setActions(view)");
        }
        try {
            setExtra(view);
        } catch (Exception e) {
            Issue.Log(e, "setExtra(view)");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        task = (TaskEvents) context;
    }

    public interface TaskEvents {
        void onComplete();

        void onAction();

        void onPrev();

    }

    protected void NotifyToUser(String msg) {
        try {
            Snackbar.make(requireView().findViewById(R.id.content), msg, Snackbar.LENGTH_SHORT).show();
        } catch (Exception e) {
            Issue.Log(e, "NotifyToUser(String msg)");
            try {
                Basics.toasting(requireContext(), msg);
            } catch (Exception e1) {
                Issue.Log(e1, "NotifyToUser(String msg)");
            }
        }
    }
}
