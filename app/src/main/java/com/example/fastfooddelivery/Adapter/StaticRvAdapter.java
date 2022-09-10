package com.example.fastfooddelivery.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Interface.UpdateRecylerView;
import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRvViewHolder> {
    private List<Food> mList;
    private int row_index = -1;
    UpdateRecylerView updateRecylerView;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public void setData(List<Food> list, Activity activity,UpdateRecylerView updateRecylerView){
        this.mList = list;
        this.activity = activity;
        this.updateRecylerView = updateRecylerView;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaticRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvAdapter.StaticRvViewHolder holder, int position) {
        Food food = mList.get(position);
        if (food == null)
            return;
        holder.imageView.setImageResource(food.getImage());
        holder.textView.setText(food.getName());

        if (check){
            ArrayList<Food> items = new ArrayList<>();
            items.add(new Food("pizza1",R.drawable.slide_2,"pizza1", 0,false,0));
            items.add(new Food("pizza2",R.drawable.slide_2,"pizza2", 0,false,0));
            items.add(new Food("pizza3",R.drawable.slide_2,"pizza3", 0,false,0));
            items.add(new Food("pizza4",R.drawable.slide_2,"pizza4", 0,false,0));
            items.add(new Food("pizza5",R.drawable.slide_2,"pizza5", 0,false,0));
            items.add(new Food("pizza6",R.drawable.slide_2,"pizza6", 0,false,0));
            items.add(new Food("pizza7",R.drawable.slide_2,"pizza7", 0,false,0));
            items.add(new Food("pizza8",R.drawable.slide_2,"pizza8", 0,false,0));
            updateRecylerView.callback(position, items);
            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                if (position == 0){
                    ArrayList<Food> items = new ArrayList<>();
                    items.add(new Food("pizza1",R.drawable.slide_2,"pizza1", 0,false,0));
                    items.add(new Food("pizza2",R.drawable.slide_2,"pizza2", 0,false,0));
                    items.add(new Food("pizza3",R.drawable.slide_2,"pizza3", 0,false,0));
                    items.add(new Food("pizza4",R.drawable.slide_2,"pizza4", 0,false,0));
                    items.add(new Food("pizza5",R.drawable.slide_2,"pizza5", 0,false,0));
                    items.add(new Food("pizza6",R.drawable.slide_2,"pizza6", 0,false,0));
                    items.add(new Food("pizza7",R.drawable.slide_2,"pizza7", 0,false,0));
                    items.add(new Food("pizza8",R.drawable.slide_2,"abc", 0,false,0));
                    updateRecylerView.callback(position, items);

                }else if (position == 1){
                    ArrayList<Food> items = new ArrayList<>();
                    items.add(new Food("burger1",R.drawable.slide_2,"burger 1", 0,false,0));
                    items.add(new Food("burger2",R.drawable.slide_2,"burger 2", 0,false,0));
                    items.add(new Food("burger3",R.drawable.slide_2,"burger 3", 0,false,0));
                    items.add(new Food("burger4",R.drawable.slide_2,"burger 4", 0,false,0));
                    items.add(new Food("burger5",R.drawable.slide_2,"burger 5", 0,false,0));
                    items.add(new Food("burger6",R.drawable.slide_2,"burger 6", 0,false,0));
                    items.add(new Food("burger7",R.drawable.slide_2,"burger 7", 0,false,0));
                    items.add(new Food("burger8",R.drawable.slide_2,"burger 8", 0,false,0));
                    updateRecylerView.callback(position, items);
                }else if (position == 2){
                    ArrayList<Food> items = new ArrayList<>();
                    for(int i = 0 ; i < 8 ;i++){
                        String name = "fries";
                        items.add(new Food(name+(i+1),R.drawable.slide_2,name+(i+1), 0,false,0));
                    }
                    updateRecylerView.callback(position,items);
                }else if (position == 3){
                    ArrayList<Food> items = new ArrayList<>();
                    for(int i = 0 ; i < 8 ;i++){
                        String name = "Sandwitch";
                        items.add(new Food(name+(i+1),R.drawable.slide_2,name+(i+1), 0,false,0));
                    }
                    updateRecylerView.callback(position,items);
                }else if (position == 4){
                    ArrayList<Food> items = new ArrayList<>();
                    for(int i = 0 ; i < 8 ;i++){
                        String name = "Icecream";
                        items.add(new Food(name+(i+1),R.drawable.slide_2,name+(i+1), 0,false,0));
                    }
                    updateRecylerView.callback(position,items);
                }
            }
        });
        if (select){
            if (position == 0){
                holder.linearLayout.setBackgroundResource(R.drawable.search_selected_bg);
            }
            select = false;
        }else {
            if (row_index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.search_selected_bg);
            }else {
                holder.linearLayout.setBackgroundResource(R.drawable.search_bg_custom);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public class StaticRvViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CircleImageView imageView;
        private LinearLayout linearLayout;
        public StaticRvViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_search_item);
            imageView = itemView.findViewById(R.id.img_search_item);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
