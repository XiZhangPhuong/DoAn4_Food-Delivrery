package com.example.fastfooddelivery.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Adapter.DynamicRvAdapter;
import com.example.fastfooddelivery.Adapter.StaticRvAdapter;
import com.example.fastfooddelivery.Dialog.BottomSheetDialogFragment;
import com.example.fastfooddelivery.Interface.UpdateRecylerView;
import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SearchFragment extends Fragment implements UpdateRecylerView {
    private View mView;
    private MainActivity mMainActivity;
    private RecyclerView rcv_1_search,rcv_2_search;
    private StaticRvAdapter adapter;
    private List<Food> mList,list;
    private DynamicRvAdapter dynamicRvAdapter;
    private EditText edt_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_search, container, false);
        edt_search = mView.findViewById(R.id.edt_search);
        mMainActivity = (MainActivity) getActivity();
        mList = new ArrayList<>();
        mList.add(new Food("itemsearch1",R.drawable.slide_1,"Pizza",0,false,0));
        mList.add(new Food("itemsearch2",R.drawable.slide_1,"Burger",0,false,0));
        mList.add(new Food("itemsearch3",R.drawable.slide_1,"Fries",0,false,0));
        mList.add(new Food("itemsearch4",R.drawable.slide_1,"Sandwitch",0,false,0));
        mList.add(new Food("itemsearch5",R.drawable.slide_1,"Icecream",0,false,0));
        rcv_1_search = mView.findViewById(R.id.rcv_1_search);
        rcv_2_search = mView.findViewById(R.id.rcv_2_search);
        adapter = new StaticRvAdapter();
        adapter.setData(mList,mMainActivity,this);
        rcv_1_search.setLayoutManager(new LinearLayoutManager(mMainActivity,RecyclerView.HORIZONTAL,false));
        rcv_1_search.setAdapter(adapter);

        list = new ArrayList<>();
        dynamicRvAdapter = new DynamicRvAdapter(list, null);
        rcv_2_search.setLayoutManager(new LinearLayoutManager(mMainActivity,RecyclerView.VERTICAL,false));
        rcv_2_search.setAdapter(dynamicRvAdapter);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dynamicRvAdapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return mView;
    }

    @Override
    public void callback(int position, List<Food> foods) {
        dynamicRvAdapter = new DynamicRvAdapter(foods, new DynamicRvAdapter.IClickRCVItem() {
            @Override
            public void ClickItem(Food food) {
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetDialogFragment.newInstance(food);
                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        dynamicRvAdapter.notifyDataSetChanged();
        rcv_2_search.setAdapter(dynamicRvAdapter);
    }
}