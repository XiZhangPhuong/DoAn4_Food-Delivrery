package com.example.fastfooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfooddelivery.Adapter.CartAdapter;
import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.Model.Notification;
import com.example.fastfooddelivery.Model.Order;
import com.example.fastfooddelivery.Model.User;
import com.example.fastfooddelivery.R;
import com.example.fastfooddelivery.SharedPreferences.DataSharedPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.fastfooddelivery.Dialog.BottomSheetDialogFragment.list_home_to_cart;
import static com.example.fastfooddelivery.Fragment.FavoriteFragment.list_cart_from_favorite;
import static com.example.fastfooddelivery.Fragment.FavoriteFragment.listfood_fravorite;
import static com.example.fastfooddelivery.Fragment.LoginFragment.KEY_OBJECT;
import static com.example.fastfooddelivery.TEMP.listNoti;

public class CartActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private RecyclerView rcv_cart;
    private TextView tv_subtotal_cart,tv_total_cart;
    private EditText edt_maps;
    private Button btn_confirm_cart;
    private CartAdapter cartAdapter;
    private List<Food> list;
    private ImageButton imb_back_cart;
    private DatabaseReference data;
    double sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rcv_cart = findViewById(R.id.rcv_cart);
        tv_total_cart = findViewById(R.id.tv_total_cart);
        tv_subtotal_cart = findViewById(R.id.tv_subtotal_cart);
        btn_confirm_cart = findViewById(R.id.btn_confirm_cart);
        imb_back_cart = findViewById(R.id.imb_back_cart);
        edt_maps = findViewById(R.id.edt_maps);
        data = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("ListDelivery");
        cartAdapter = new CartAdapter();
        list = new ArrayList<>();
        if (list_cart_from_favorite != null)
            list.addAll(list_cart_from_favorite);
        if (list_home_to_cart != null)
            list.addAll(list_home_to_cart);
        for(Food food : list){
            sum += food.getPrice()*food.getCount();
        }
        tv_subtotal_cart.setText(""+sum);
        tv_total_cart.setText(""+sum);
        cartAdapter.setData(list, new CartAdapter.IClickCart() {
            @Override
            public void ClickPlusCart(TextView tv_number_cart,TextView tv_total_item, Food food) {
                int i = Integer.parseInt(tv_number_cart.getText().toString());
                i++;
                food.setCount(i);
                tv_number_cart.setText(""+i);
                tv_total_item.setText(""+i*food.getPrice());
                sum = sum + food.getPrice();
                tv_subtotal_cart.setText(""+sum);
                tv_total_cart.setText(""+sum);
            }

            @Override
            public void ClickMinusCart(TextView tv_number_cart,TextView tv_total_item, Food food) {
                int i = Integer.parseInt(tv_number_cart.getText().toString());
                i--;
                tv_total_item.setText(""+i*food.getPrice());
                food.setCount(i);
                sum = sum - food.getPrice();
                tv_subtotal_cart.setText(""+sum);
                tv_total_cart.setText(""+sum);
                if (i==0){
                    list.remove(food);
                    list_cart_from_favorite.remove(food);
                    list_home_to_cart.remove(food);
                    cartAdapter.notifyDataSetChanged();
                    return;
                }
                tv_number_cart.setText(""+i);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_cart.setLayoutManager(linearLayoutManager);
        rcv_cart.setAdapter(cartAdapter);
        btn_confirm_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() == 0) {
                    Toast.makeText(CartActivity.this, "Ban chua dat mon", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edt_maps.getText().toString().equals("")){
                    edt_maps.setError("Chua nhap dia chi");
                    edt_maps.requestFocus();
                    return;
                }
            String mess ="Mon ban da dat la:";
            for (Food food : list){
                mess += "\nTen mon:"+food.getName() +"\nSo Luong:"+food.getCount();
            }
            String messtemp = mess;
            mess += "\nTong tien:"+sum;
            //////////
            User user = DataSharedPreferences.getUser(CartActivity.this,KEY_OBJECT);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            listNoti = DataSharedPreferences.getList(CartActivity.this,"KEY_NOTI");
            if (listNoti == null)
                listNoti = new ArrayList<>();
            listNoti.add(new Notification(user.getPhoneNumber(),mess, timeStamp));
            DataSharedPreferences.setList(CartActivity.this,listNoti,"KEY_NOTI");
            //////////
            new AlertDialog.Builder(CartActivity.this).setMessage(mess).setTitle("Warring").setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.clear();
                    list_cart_from_favorite.clear();
                    list_home_to_cart.clear();
                    cartAdapter.notifyDataSetChanged();
                    tv_subtotal_cart.setText("");
                    tv_total_cart.setText("");
                    Toast.makeText(CartActivity.this,"?????t h??ng th??nh c??ng!",Toast.LENGTH_SHORT).show();
                    ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            String key = data.push().getKey();
                            Order order = new Order(key,user.getFullName(),sum,edt_maps.getText().toString(),1,messtemp,false);
                            data.child(order.getId()).setValue(order);
                        }
                    });
                    finish();
                }
            }).setPositiveButton("no",null).show();
            }
        });
        imb_back_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}