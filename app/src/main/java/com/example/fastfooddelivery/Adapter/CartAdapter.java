package com.example.fastfooddelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Food> mList;
    private IClickCart mIClickCart;
    public interface IClickCart {
        void ClickPlusCart(TextView tv_number_cart,TextView tv_total_item, Food food);
        void ClickMinusCart(TextView tv_number_cart, TextView tv_total_item, Food food);
    }
    public void setData(List<Food> list, IClickCart iClickCart){
        mList = list;
        mIClickCart = iClickCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Food food = this.mList.get(position);
        if (food == null)
            return;
        holder.img_cart_photo.setImageResource(food.getImage());
        holder.tv_name_cart.setText(food.getName());
//        holder.tv_price_cart.setText(""+food.getPrice());
        holder.tv_total_item.setText(""+food.getPrice()*food.getCount());
        holder.tv_number_cart.setText(""+food.getCount());
        holder.imb_minus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickCart.ClickMinusCart(holder.tv_number_cart, holder.tv_total_item, food);
            }
        });
        holder.imb_plus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickCart.ClickPlusCart(holder.tv_number_cart, holder.tv_total_item, food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView img_cart_photo;
        private TextView tv_name_cart,tv_price_cart,tv_total_item,tv_number_cart;
        private ImageButton imb_minus_cart,imb_plus_cart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cart_photo = itemView.findViewById(R.id.img_cart_photo);
            tv_name_cart = itemView.findViewById(R.id.tv_name_cart);
//            tv_price_cart = itemView.findViewById(R.id.tv_price_cart);
            tv_total_item = itemView.findViewById(R.id.tv_total_item);
            tv_number_cart = itemView.findViewById(R.id.tv_number_cart);
            imb_minus_cart = itemView.findViewById(R.id.imb_minus_cart);
            imb_plus_cart = itemView.findViewById(R.id.imb_plus_cart);
        }
    }
}
