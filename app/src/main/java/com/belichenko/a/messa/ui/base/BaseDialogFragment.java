package com.belichenko.a.messa.ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.belichenko.a.messa.MessaApplication;
import com.belichenko.a.messa.injection.module.FragmentModule;
import com.belichenko.a.messa.util.Consts;

import com.belichenko.a.messa.injection.component.DaggerFragmentComponent;
import com.belichenko.a.messa.injection.component.FragmentComponent;


public class BaseDialogFragment extends DialogFragment {

    private FragmentComponent mFragmentComponent;
    private DialogInterface.OnDismissListener onDismissListener;
    private BlurDialogFragmentHelperWithRadius mHelper;
    private long mLastClickTime = SystemClock.elapsedRealtime();

    public FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(MessaApplication.get(getContext()).getComponent())
                    .build();
        }
        return mFragmentComponent;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mHelper = new BlurDialogFragmentHelperWithRadius(this, 1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHelper.onActivityCreated();
    }

    @Override
    public void onStart() {
        super.onStart();
        mHelper.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHelper.onPause();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mHelper.onDismiss();
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    protected boolean isClickAllowed() {
        long now = SystemClock.elapsedRealtime();
        if (now - mLastClickTime > Consts.CLICK_TIME_INTERVAL) {
            mLastClickTime = now;
            return true;
        } else return false;
    }
}
