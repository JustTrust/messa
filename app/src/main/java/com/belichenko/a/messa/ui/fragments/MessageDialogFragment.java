package com.belichenko.a.messa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.base.BaseDialogFragment;


public class MessageDialogFragment extends BaseDialogFragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_TEXT = "text";
    private static final String KEY_LEFT = "left";
    private static final String KEY_RIGHT = "right";
    private static final String KEY_BUTTONS = "buttons";

    public static final int ONE_BT = 0;
    public static final int TWO_HORIZONTAL_BT = 1;
    public static final int TWO_VERTICAL_BT = 2;


    @BindView(R.id.dialog_tb_but_left)
    Button mButtonLeft;
    @BindView(R.id.dialog_tb_but_right)
    Button mButtonRight;

    @BindView(R.id.dialog_tb_tv_title)
    TextView mTitle;
    @BindView(R.id.dialog_tb_tv_text)
    TextView mText;

    @BindView(R.id.dialog_tb_img_close)
    ImageView mImageClose;
    @BindView(R.id.dialog_tb_but_layout)
    LinearLayout mBtLayout;

    private OnButtonClickListener mListener;

    public static MessageDialogFragment newOneBtInstance(String title, String text, String leftButtonText) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_TEXT, text);
        bundle.putString(KEY_LEFT, leftButtonText);
        bundle.putString(KEY_RIGHT, "");
        bundle.putInt(KEY_BUTTONS, ONE_BT);

        MessageDialogFragment instance = new MessageDialogFragment();
        instance.setArguments(bundle);
        return instance;
    }

    public static MessageDialogFragment newTwoHorizontalBtInstance(String title, String text, String leftButtonText, String rightButtonText) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_TEXT, text);
        bundle.putString(KEY_LEFT, leftButtonText);
        bundle.putString(KEY_RIGHT, rightButtonText);
        bundle.putInt(KEY_BUTTONS, TWO_HORIZONTAL_BT);

        MessageDialogFragment instance = new MessageDialogFragment();
        instance.setArguments(bundle);
        return instance;
    }

    public static MessageDialogFragment newTwoVerticalBtInstance(String title, String text, String leftButtonText, String rightButtonText) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_TEXT, text);
        bundle.putString(KEY_LEFT, leftButtonText);
        bundle.putString(KEY_RIGHT, rightButtonText);
        bundle.putInt(KEY_BUTTONS, TWO_VERTICAL_BT);

        MessageDialogFragment instance = new MessageDialogFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFragmentStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle.setText(bundle.getString(KEY_TITLE, ""));
            mText.setText(bundle.getString(KEY_TEXT, ""));
            mButtonLeft.setText(bundle.getString(KEY_LEFT, ""));
            mButtonRight.setText(bundle.getString(KEY_RIGHT, ""));
            int btOption = bundle.getInt(KEY_BUTTONS, 0);
            switch (btOption) {
                case ONE_BT:
                    mButtonRight.setVisibility(View.GONE);
                    mBtLayout.setWeightSum(1);
                    break;
                case TWO_HORIZONTAL_BT:
                    mBtLayout.setOrientation(LinearLayout.HORIZONTAL);
                    break;
                case TWO_VERTICAL_BT:
                    mBtLayout.setOrientation(LinearLayout.VERTICAL);
                    break;
            }
        } else {
            dismiss();
        }

    }

    @OnClick(R.id.dialog_tb_but_right)
    void onRightClick() {
        dismiss();
        if (mListener != null) {
            mListener.onRightClick();
        }
    }

    @OnClick(R.id.dialog_tb_but_left)
    void onLeftClick() {
        dismiss();
        if (mListener != null) {
            mListener.onLeftClick();
        }
    }

    @OnClick(R.id.dialog_tb_img_close)
    void onCloseClick() {
        dismiss();
        if (mListener != null) {
            mListener.onCloseClick();
        }
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        if (listener != null) this.mListener = listener;
    }

    public interface OnButtonClickListener {
        void onLeftClick();

        void onRightClick();

        void onCloseClick();
    }
}