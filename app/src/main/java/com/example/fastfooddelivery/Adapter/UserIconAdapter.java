package com.example.fastfooddelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.IconUser;
import com.example.fastfooddelivery.R;

import java.util.List;

public class UserIconAdapter extends RecyclerView.Adapter<UserIconAdapter.UserIconVIewHolder> {
    private List<IconUser> mList;
    private IClickIconUser mIClickIconUser;
    public interface IClickIconUser {
        void Click(int i);
    }

    public UserIconAdapter(List<IconUser> mList,IClickIconUser iClickIconUser) {
        this.mList = mList;
        this.mIClickIconUser = iClickIconUser;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserIconVIewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new UserIconVIewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_icon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  UserIconAdapter.UserIconVIewHolder holder, int position) {
        IconUser icon = mList.get(position);
        if (icon == null)
            return;
        holder.tv_name_item_user.setText(icon.getName());
        holder.imb_photo_item_user.setImageResource(icon.getImage());
        holder.lin_icon_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickIconUser.Click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public class UserIconVIewHolder extends RecyclerView.ViewHolder{
        private ImageButton imb_photo_item_user;
        private TextView tv_name_item_user;
        private LinearLayout lin_icon_user;
        public UserIconVIewHolder(@NonNull View itemView) {
            super(itemView);
            imb_photo_item_user = itemView.findViewById(R.id.imb_photo_item_user);
            tv_name_item_user = itemView.findViewById(R.id.tv_name_item_user);
            lin_icon_user = itemView.findViewById(R.id.lin_icon_user);
        }
    }
}
