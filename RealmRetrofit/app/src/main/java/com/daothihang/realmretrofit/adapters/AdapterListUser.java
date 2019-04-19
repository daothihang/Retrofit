package com.daothihang.realmretrofit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daothihang.realmretrofit.R;
import com.daothihang.realmretrofit.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdapterListUser extends RecyclerView.Adapter<AdapterListUser.UserItemViewHolder> {
    //private OnClick listener;
    private List<User> users;
    private Context context;

    public AdapterListUser(ArrayList<User> employeeList, Context context) {
        this.users = employeeList;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserItemViewHolder holder, final int position) {

        final User u = users.get(position);
        Glide.with(holder.itemView)
                .load(u.getAvatar_url())
                .into(holder.ivAvatar);
        holder.tvLoginName.setText(u.getLogin());
        holder.tvId.setText(String.valueOf(u.getId()));

    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLoginName;
        public TextView tvId;
        public ImageView ivAvatar;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            tvLoginName = itemView.findViewById(R.id.tv_nameUser);
            tvId = itemView.findViewById(R.id.tv_idUser);
            ivAvatar = itemView.findViewById(R.id.img_avartar);

        }
    }


}
