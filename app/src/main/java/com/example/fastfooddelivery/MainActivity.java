package com.example.fastfooddelivery;

import static com.example.fastfooddelivery.Fragment.LoginFragment.dataUser;
import static com.example.fastfooddelivery.TEMP.ranDomCODE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.fastfooddelivery.Activity.CartActivity;
import com.example.fastfooddelivery.Adapter.ViewPagerAdapter;
import com.example.fastfooddelivery.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;
    private FloatingActionButton btncart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager2 = findViewById(R.id.viewpager);
        btncart = findViewById(R.id.btn_cart);
        btncart.setColorFilter(Color.WHITE);
        adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        viewPager2.setUserInputEnabled(false);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_home){
                    viewPager2.setCurrentItem(0);
                }else if(id == R.id.menu_category){
                    viewPager2.setCurrentItem(1);
                }else if(id == R.id.menu_user){
                    viewPager2.setCurrentItem(2);
                }else if (id == R.id.menu_setting){
                    viewPager2.setCurrentItem(3);
                }
                return false;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_category).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_user).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_setting).setChecked(true);
                        break;
                }
            }
        });
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CartActivity.class);
//                startActivity(intent);

                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

}