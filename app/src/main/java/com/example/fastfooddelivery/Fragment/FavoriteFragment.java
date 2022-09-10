package com.example.fastfooddelivery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Adapter.FavoriteAdapter;
import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;
import com.example.fastfooddelivery.SharedPreferences.MySharedPreferences;

import java.util.ArrayList;
import java.util.List;



public class FavoriteFragment extends Fragment {
    private View mView;
    private MainActivity activity;
    private RecyclerView rcv_favorite;
    private FavoriteAdapter adapter;
    public static List<Food> listfood_fravorite = new ArrayList<>();
    public static List<Food> list_cart_from_favorite = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_favorite, container, false);
        activity = (MainActivity) getActivity();
        rcv_favorite = mView.findViewById(R.id.rcv_favorite);
        listfood_fravorite = MySharedPreferences.getList(activity);
        adapter = new FavoriteAdapter(listfood_fravorite, new FavoriteAdapter.IClickFavoriteLis() {
            @Override
            public void DeleteItemFavorite(Food food) {
                listfood_fravorite.remove(food);
                MySharedPreferences.setList(activity, listfood_fravorite);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void AddToCartItemFromFavorite(Food food) {
                if (food == null)
                    return;
                if(list_cart_from_favorite.size() == 0) {
                    list_cart_from_favorite.add(food);
                    Toast.makeText(getContext(), "Da them vao gio hang", Toast.LENGTH_SHORT).show();
                }
                else{
                    for(int i=0 ; i < list_cart_from_favorite.size() ; i++){
                        if (food.getID().equals(list_cart_from_favorite.get(i).getID())){
                            int count = food.getCount() + 1;
                            food.setCount(count);
                            Toast.makeText(getContext(), "Da them vao gio hang", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    list_cart_from_favorite.add(food);
                    Toast.makeText(getContext(), "Da them vao gio hang", Toast.LENGTH_SHORT).show();
                }
            }

        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity,RecyclerView.VERTICAL,false);
        rcv_favorite.setLayoutManager(linearLayoutManager);
        rcv_favorite.setAdapter(adapter);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MySharedPreferences.setList(activity, listfood_fravorite);
        adapter.notifyDataSetChanged();
    }
}