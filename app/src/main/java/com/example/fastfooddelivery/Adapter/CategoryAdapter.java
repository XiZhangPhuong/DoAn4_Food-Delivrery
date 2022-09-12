package com.example.fastfooddelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Model.Category;
import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.Model.IconUser;
import com.example.fastfooddelivery.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mListCate;
    private CategoryAdapter.IClickCategory mIClickCategory;
    public interface IClickCategory {
        void Click(Category category);
    }
    public CategoryAdapter(List<Category> mListCate,CategoryAdapter.IClickCategory mIClickCategory){
        this.mListCate = mListCate;
        this.mIClickCategory = mIClickCategory;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
          Category category = mListCate.get(position);
          if(category==null){
              return;
          }
          holder.img_cate.setImageResource(category.getImage_cate());
          holder.edt_cate.setText(category.getTitle_cate());
          holder.img_cate.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  mIClickCategory.Click(category);
              }
          });
    }

    @Override
    public int getItemCount() {
        if (mListCate != null)
            return mListCate.size();
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_cate;
        private TextView edt_cate;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cate = itemView.findViewById(R.id.img_category);
            edt_cate = itemView.findViewById(R.id.txt_name_category);
        }
    }
}
