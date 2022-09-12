package com.example.fastfooddelivery.Fragment.Cart;

import static com.example.fastfooddelivery.Dialog.BottomSheetDialogFragment.list_home_to_cart;
import static com.example.fastfooddelivery.Fragment.FavoriteFragment.list_cart_from_favorite;
import static com.example.fastfooddelivery.Fragment.LoginFragment.KEY_OBJECT;
import static com.example.fastfooddelivery.TEMP.listNoti;
import static com.example.fastfooddelivery.TEMP.showNotification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfooddelivery.Activity.CartActivity;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class fragment_cart extends Fragment {
    private RecyclerView rcv_cart;
    private TextView tv_subtotal_cart,tv_total_cart;
    private EditText edt_maps;
    private Button btn_confirm_cart;
    private CartAdapter cartAdapter;
    private List<Food> list;
    private DatabaseReference dataListOrder = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("ListDelivery");
    private double sum = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView =inflater.inflate(R.layout.fragment_cart, container, false);

        rcv_cart = mView.findViewById(R.id.rcv_cart);
        tv_total_cart = mView.findViewById(R.id.tv_total_cart);
        tv_subtotal_cart = mView.findViewById(R.id.tv_subtotal_cart);
        btn_confirm_cart = mView.findViewById(R.id.btn_confirm_cart);
        edt_maps = mView.findViewById(R.id.edt_maps);

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv_cart.setLayoutManager(linearLayoutManager);
        rcv_cart.setAdapter(cartAdapter);
        btn_confirm_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() == 0) {
                    Toast.makeText(getContext(), "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edt_maps.getText().toString().equals("")){
                    edt_maps.setError("Bạn chưa nhập địa chỉ");
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
                User user = DataSharedPreferences.getUser(getContext(),KEY_OBJECT);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                listNoti = DataSharedPreferences.getList(getContext(),"KEY_NOTI");
                if (listNoti == null)
                    listNoti = new ArrayList<>();
                listNoti.add(new Notification(user.getPhoneNumber(),mess, timeStamp));
                DataSharedPreferences.setList(getContext(),listNoti,"KEY_NOTI");
                //////////
                new AlertDialog.Builder(getContext()).setMessage(mess).setTitle("Warring").setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        list.clear();
                        list_cart_from_favorite.clear();
                        list_home_to_cart.clear();
                        cartAdapter.notifyDataSetChanged();
                        tv_subtotal_cart.setText("");
                        tv_total_cart.setText("");
                        Toast.makeText(getContext(),"Đặt hàng thành công!",Toast.LENGTH_SHORT).show();
                        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                String key = dataListOrder.push().getKey();
                                Order order = new Order(key,user.getFullName(),sum,edt_maps.getText().toString(),1,messtemp,false);
                                dataListOrder.child(order.getId()).setValue(order);
                                String title = "Đang chờ tài xế xác nhận";
                                String mess  ="Tổng đơn hàng của bạn là : "+order.getPrice();
                                showNotification(getContext(),title,mess);
                            }
                        });

                    }
                }).setPositiveButton("no",null).show();
            }
        });

        return mView;

    }

}