package com.example.fastfooddelivery.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fastfooddelivery.Adapter.CategoryAdapter;
import com.example.fastfooddelivery.Adapter.PhotoViewPager2Adapter;
import com.example.fastfooddelivery.Adapter.RecommendFoodAdapter;
import com.example.fastfooddelivery.Adapter.TodayFoodAdapter;
import com.example.fastfooddelivery.Dialog.BottomSheetDialogFragment;
import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.Model.Category;
import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.Model.PhotoIndicator;
import com.example.fastfooddelivery.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {
    private View mView;
    private RecyclerView rcv_recommend,rcv_today;
    private ViewPager2 viewPager2;
    private RecommendFoodAdapter recommendFoodAdapter;
    private TodayFoodAdapter todayFoodAdapter;
    private CategoryAdapter categoryAdapter;
    private CircleIndicator3 circleIndicator3;
    private List<PhotoIndicator> photos;
    private List<Category> listCategory;
    private MainActivity mainActivity;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == photos.size() - 1)
                viewPager2.setCurrentItem(0);
            else
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        viewPager2 = mView.findViewById(R.id.view_pager_2);
        rcv_recommend = mView.findViewById(R.id.rcv_recommend);
        rcv_today = mView.findViewById(R.id.rcv_today);
        circleIndicator3 = mView.findViewById(R.id.circle_indicator_3);
        photos = getListPhoto();
        PhotoViewPager2Adapter adapter = new PhotoViewPager2Adapter(photos);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
        setDataIconCategory();
        recommendFoodAdapter = new RecommendFoodAdapter(getListFood(), new RecommendFoodAdapter.IClickRecommendFood() {
            @Override
            public void onClickOpenFood(Food food) {
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetDialogFragment.newInstance(food);
                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);
        rcv_recommend.setLayoutManager(linearLayoutManager);
        rcv_recommend.setAdapter(recommendFoodAdapter);

        todayFoodAdapter = new TodayFoodAdapter(getListFood(), new TodayFoodAdapter.IClickRecommendFood() {
            @Override
            public void onClickOpenFood(Food food) {
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetDialogFragment.newInstance(food);
                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,2);
        rcv_today.setLayoutManager(gridLayoutManager);
        rcv_today.setAdapter(todayFoodAdapter);

        return mView;
    }
    private void setDataIconCategory(){
        listCategory = new ArrayList<>();
        listCategory.add(new Category(R.drawable.icon_rice_50px,"Cơm"));
        listCategory.add(new Category(R.drawable.icon_noodle_50px,"Mỳ"));
        listCategory.add(new Category(R.drawable.icon_chicken_50px,"Gà rán"));
        listCategory.add(new Category(R.drawable.icon_drinks_50px,"Đồ uống"));
        listCategory.add(new Category(R.drawable.icon_tea_50px,"Trà sửa"));
        listCategory.add(new Category(R.drawable.icon_bread_50px,"Ăn vặt"));

        RecyclerView rcv_category  = mView.findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(listCategory,null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,3);
        rcv_category.setLayoutManager(gridLayoutManager);
        rcv_category.setAdapter(categoryAdapter);

    }
    private List<Food> getListFood() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("food1",R.drawable.slide_1,"Cơm chiên trứng",50000.0,false,1));
        foods.add(new Food("food2",R.drawable.slide_2,"Mỳ sào",40000.0,false,1));
        foods.add(new Food("food3",R.drawable.slide_3,"Mực hấp",30000.0,false,1));
        foods.add(new Food("food4",R.drawable.slide_4,"Trà sửa",80000.0,false,1));
        foods.add(new Food("food5",R.drawable.slide_5,"Mon 5",90000.0,false,1));
        foods.add(new Food("food6",R.drawable.slide_2,"Mon 2",40000.0,false,1));
        foods.add(new Food("food7",R.drawable.slide_3,"Mon 3",30000.0,false,1));
        foods.add(new Food("food8",R.drawable.slide_4,"Mon 4",80000.0,false,1));
        foods.add(new Food("food9",R.drawable.slide_5,"Mon 5",90000.0,false,1));
        return foods;
    }

    private List<PhotoIndicator> getListPhoto() {
        List<PhotoIndicator> photoIndicators = new ArrayList<>();
        photoIndicators.add(new PhotoIndicator(R.drawable.indicator1));
        photoIndicators.add(new PhotoIndicator(R.drawable.indicator2));
        photoIndicators.add(new PhotoIndicator(R.drawable.indicator3));
        photoIndicators.add(new PhotoIndicator(R.drawable.indiactor5));
        return photoIndicators;
    }
}