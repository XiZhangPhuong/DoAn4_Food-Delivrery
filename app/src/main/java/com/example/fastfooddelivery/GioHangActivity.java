package com.example.fastfooddelivery;

import static com.example.fastfooddelivery.Dialog.BottomSheetDialogFragment.list_home_to_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fastfooddelivery.Fragment.Cart.fragment_cart;
import com.example.fastfooddelivery.Fragment.Cart.fragment_cart_isempty;
import com.example.fastfooddelivery.Model.Food;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        if(list_home_to_cart.size()==0){
            replaceFragmentCartIsEmpty(new fragment_cart_isempty());
        }else{
            replaceFragmentCartIsEmpty(new fragment_cart());
        }

    }

    private void replaceFragmentCartIsEmpty(Fragment fragment) {
          FragmentManager manager = getSupportFragmentManager();
          FragmentTransaction fragmentTransaction = manager.beginTransaction();
          fragmentTransaction.replace(R.id.fragment_cart,fragment);
          fragmentTransaction.commit();
    }





}