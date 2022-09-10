package com.example.fastfooddelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<Food> foods;
    private IClickFavoriteLis mIClickFavoriteLis;
    public interface IClickFavoriteLis{
        void DeleteItemFavorite(Food food);
        void AddToCartItemFromFavorite(Food food);
    }

    public FavoriteAdapter(List<Food> foods, IClickFavoriteLis iClickFavoriteLis) {
        this.foods = foods;
        mIClickFavoriteLis = iClickFavoriteLis;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  FavoriteAdapter.FavoriteViewHolder holder, int position) {
        Food food = foods.get(position);
        if (food == null)
            return;
        holder.img_favorite_photo.setImageResource(food.getImage());
        holder.tv_favorite_name.setText(food.getName());
        holder.tv_favorite_price.setText(""+food.getPrice());
        holder.imb_delete_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickFavoriteLis.DeleteItemFavorite(food);
            }
        });
        holder.imb_add_cart_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickFavoriteLis.AddToCartItemFromFavorite(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foods != null)
            return foods.size();
        return 0;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_favorite_photo;
        private TextView tv_favorite_name,tv_favorite_price;
        private ImageButton imb_delete_favorite,imb_add_cart_favorite;
        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            img_favorite_photo = itemView.findViewById(R.id.img_favorite_photo);
            tv_favorite_name = itemView.findViewById(R.id.tv_favorite_name);
            tv_favorite_price = itemView.findViewById(R.id.tv_favorite_price);
            imb_delete_favorite = itemView.findViewById(R.id.imb_delete_favorite);
            imb_add_cart_favorite = itemView.findViewById(R.id.imb_add_cart_favorite);
        }
    }
}
