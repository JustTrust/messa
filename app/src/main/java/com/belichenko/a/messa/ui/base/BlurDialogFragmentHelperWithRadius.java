package com.belichenko.a.messa.ui.base;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


class BlurDialogFragmentHelperWithRadius {
    private final DialogFragment mFragment;

    private ViewGroup mRoot;

    private ViewGroup mBlurContainer;

    private BlurView mBlurBgView;

    private float mRadius;

    BlurDialogFragmentHelperWithRadius(@NonNull DialogFragment fragment, float radius) {
        mFragment = fragment;
        mRadius = radius;
    }

    void onActivityCreated() {

        Rect visibleFrame = new Rect();
        mRoot = (ViewGroup) mFragment.getActivity().getWindow().getDecorView();
        mRoot.getWindowVisibleDisplayFrame(visibleFrame);

        mBlurContainer = new FrameLayout(mFragment.getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            // window has a navigation bar
            mBlurContainer = new FrameLayout(mFragment.getActivity());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(visibleFrame.right - visibleFrame.left,
                    visibleFrame.bottom - visibleFrame.top);
            params.setMargins(visibleFrame.left, visibleFrame.top, 0, 0);
            mBlurContainer.setLayoutParams(params);
        } else {
            mBlurContainer.setPadding(visibleFrame.left, visibleFrame.top, 0, 0);
        }

        mBlurBgView = new BlurView(mFragment.getContext());
        final View decorView = mFragment.getActivity().getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        mBlurBgView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(mFragment.getContext(), true)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(mRadius);
        mBlurContainer.addView(mBlurBgView);

        mRoot.addView(mBlurContainer);
    }

    void onStart() {

        mBlurBgView.startAutoBlurUpdate();
    }

    void onDismiss() {
        mBlurBgView.stopAutoBlurUpdate();
        mRoot.removeView(mBlurContainer);
    }

    void onResume() {
        if (mBlurBgView != null) {
            mBlurBgView.startAutoBlurUpdate();
        }
    }

    void onPause() {
        if (mBlurBgView != null) {
            mBlurBgView.stopAutoBlurUpdate();
        }
    }
}
