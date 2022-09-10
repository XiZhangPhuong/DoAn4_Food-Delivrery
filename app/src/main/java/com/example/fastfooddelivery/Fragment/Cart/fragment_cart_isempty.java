package com.example.fastfooddelivery.Fragment.Cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.R;


public class fragment_cart_isempty extends Fragment {
private Button bt_tt_cart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View mView =inflater.inflate(R.layout.fragment_cart_isempty, container, false);

       bt_tt_cart = mView.findViewById(R.id.btn_continue_cart);
       bt_tt_cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getContext(), MainActivity.class);
               startActivity(intent);
           }
       });
       return mView;
    }
}


