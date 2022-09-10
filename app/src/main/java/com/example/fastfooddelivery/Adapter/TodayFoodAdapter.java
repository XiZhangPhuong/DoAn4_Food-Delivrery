package com.example.fastfooddelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;

import java.util.List;


public class TodayFoodAdapter extends RecyclerView.Adapter<TodayFoodAdapter.RecoommendViewHolder> {
    private List<Food> foods;
    private IClickRecommendFood mIClickRecommendFood;
    public interface IClickRecommendFood{
        void onClickOpenFood(Food food);
    }

    public TodayFoodAdapter(List<Food> foods, IClickRecommendFood iClickRecommendFood) {
        this.foods = foods;
        mIClickRecommendFood = iClickRecommendFood;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecoommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecoommendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_today_food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodayFoodAdapter.RecoommendViewHolder holder, int position) {
        Food food = foods.get(position);
        if (food == null)
            return;
        holder.img_red_photo.setImageResource(food.getImage());
        holder.tv_red_price.setText(""+food.getName());
        holder.img_red_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickRecommendFood.onClickOpenFood(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foods != null)
            return foods.size();
        return 0;
    }

    public class RecoommendViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_red_photo;
        private TextView tv_red_price;
        public RecoommendViewHolder(@NonNull View itemView) {
            super(itemView);
            img_red_photo = itemView.findViewById(R.id.img_red_photo);
            tv_red_price = itemView.findViewById(R.id.tv_name_red_food);
        }
    }
}
