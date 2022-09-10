package com.example.fastfooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.fastfooddelivery.Adapter.LoginAdapterViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginAndSingupActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    public static ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_singup);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));

       // tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        viewPager2 = findViewById(R.id.view_pager);

        LoginAdapterViewPager2 adapterViewPager2 = new LoginAdapterViewPager2(this);
        viewPager2.setAdapter(adapterViewPager2);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("ĐĂNG NHẬP");
                        break;
                    case 1:
                        tab.setText("ĐĂNG KÝ");
                        break;
                }
            }
        }).attach();
    }
}