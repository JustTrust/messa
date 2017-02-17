package com.belichenko.a.messa.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.belichenko.a.messa.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<String> mMessage;

    @Inject
    public MessageAdapter() {
        mMessage = new ArrayList<>();
    }

    public void setUsers(List<String> users) {
        mMessage = users;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        String text = mMessage.get(position);
        holder.mMessageImage.setBackgroundColor(Color.LTGRAY);
        holder.mMessageText.setText(String.format("%s", text));
    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.message_image) ImageView mMessageImage;
        @BindView(R.id.message_text) TextView mMessageText;
        @BindView(R.id.message_root_view) CardView mMessageRootView;

        public MessageViewHolder(View messageView) {
            super(messageView);
            ButterKnife.bind(this, messageView);
        }
    }
}
