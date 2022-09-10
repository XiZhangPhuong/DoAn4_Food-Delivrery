package com.example.fastfooddelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.Notification;
import com.example.fastfooddelivery.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> list;
    private IClickItemNoti mIClickItemNoti;
    public interface IClickItemNoti{
        void ClickItemNoti(Notification noti);
    }

    public NotificationAdapter(List<Notification> list,IClickItemNoti iClickItemNoti) {
        this.list = list;
        this.mIClickItemNoti = iClickItemNoti;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = list.get(position);
        if (notification == null)
            return;
        holder.tv_time_notification.setText(notification.getBookingDate());
        holder.rl_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemNoti.ClickItemNoti(notification);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time_notification;
        private RelativeLayout rl_notifi;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time_notification = itemView.findViewById(R.id.tv_time_notification);
            rl_notifi = itemView.findViewById(R.id.rl_notifi);
        }
    }
}
