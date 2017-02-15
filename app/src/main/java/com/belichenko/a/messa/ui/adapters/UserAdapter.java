package com.belichenko.a.messa.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.belichenko.a.messa.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<String> mUsers;
    private OnUserClickListener mListener;

    @Inject
    public UserAdapter() {
        mUsers = new ArrayList<>();
    }

    public void setUsers(List<String> users) {
        mUsers = users;
    }

    public void setListener(OnUserClickListener listener){
        mListener = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.belichenko.a.messa.R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        final String name = mUsers.get(position);
        holder.hexColorView.setBackgroundColor(Color.LTGRAY);
        holder.nameTextView.setText(String.format("%s %s", name, name));
        holder.emailTextView.setText(String.format("%s%s", name, "@google.com").toLowerCase());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onUserClick(name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(com.belichenko.a.messa.R.id.view_hex_color) View hexColorView;
        @BindView(R.id.user_root_view) View rootView;
        @BindView(com.belichenko.a.messa.R.id.text_name) TextView nameTextView;
        @BindView(com.belichenko.a.messa.R.id.text_email) TextView emailTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnUserClickListener{
        void onUserClick(String name);
    }
}
