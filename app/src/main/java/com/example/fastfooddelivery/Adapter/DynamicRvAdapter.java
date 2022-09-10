package com.example.fastfooddelivery.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;

import java.util.ArrayList;
import java.util.List;

public class DynamicRvAdapter extends RecyclerView.Adapter<DynamicRvAdapter.DynamicHolder>{
    public List<Food> mList;
    public ArrayList<Food> listcoppy;
    public IClickRCVItem mIClickRCVItem;
    public interface IClickRCVItem{
        void ClickItem(Food food);
    }

    public DynamicRvAdapter(List<Food> mList,IClickRCVItem iClickRCVItem) {
        this.mList = mList;
        this.listcoppy = new ArrayList<>();
        listcoppy.addAll(mList);
        mIClickRCVItem = iClickRCVItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DynamicRvAdapter.DynamicHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new DynamicHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRvAdapter.DynamicHolder holder, int position) {
        Food food = mList.get(position);
        if (food == null)
            return;
        holder.imageView.setImageResource(food.getImage());
        holder.name.setText(food.getName());
        holder.price.setText(food.getPrice()+"");
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickRCVItem.ClickItem(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public class DynamicHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name,price;
        ConstraintLayout constraintLayout;
        public DynamicHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_photo_item_search);
            name = itemView.findViewById(R.id.tv_name_item_search);
            price = itemView.findViewById(R.id.tv_price_item_search);
            constraintLayout = itemView.findViewById(R.id.constraint_layout);
        }
    }
    public void filter(CharSequence charSequence){
        ArrayList<Food> tempArraylist = new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            for(Food food : mList){
                if (food.getName().toLowerCase().contains(charSequence)){
                    tempArraylist.add(food);
                }
            }
        }else {
            tempArraylist.addAll(listcoppy);
        }
        mList.clear();
        mList.addAll(tempArraylist);
        notifyDataSetChanged();
        tempArraylist.clear();
    }
}
