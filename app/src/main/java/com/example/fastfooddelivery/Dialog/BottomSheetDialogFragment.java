package com.example.fastfooddelivery.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.R;
import com.example.fastfooddelivery.SharedPreferences.MySharedPreferences;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import static com.example.fastfooddelivery.Fragment.FavoriteFragment.listfood_fravorite;



public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {

    private static final String KEY_FOOD_OBJECT = "food_object";
    private Food food;
    private TextView tv_inf_price,tv_inf_name;
    private Button btn_huy,btn_xacnhan;
    private ImageView img_inf_photo, img_favorite;
    public static List<Food> list_home_to_cart = new ArrayList<>();
    Thread thread=null;

    public static BottomSheetDialogFragment newInstance(Food food){
        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FOOD_OBJECT,food);
        bottomSheetDialogFragment.setArguments(bundle);
        return bottomSheetDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle !=null)
        {
            food = (Food) bundle.get(KEY_FOOD_OBJECT);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View viewdialog = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet,null);
        bottomSheetDialog.setContentView(viewdialog);

        initView(viewdialog);
        setDataInfFood();

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(list_home_to_cart.size() == 0) {
                            list_home_to_cart.add(food);
                            //Toast.makeText(getContext(), "Da them vao gio hang", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        }
                        else{
                            for(int i=0 ; i < list_home_to_cart.size() ; i++){
                                if (food.getID().equals(list_home_to_cart.get(i).getID())){
                                    int count = food.getCount() + 1;
                                    food.setCount(count);
                                    //Toast.makeText(getContext(), "Da them vao gio hang", Toast.LENGTH_SHORT).show();
                                    bottomSheetDialog.dismiss();
                                    return;
                                }
                            }
                            list_home_to_cart.add(food);
                            //Toast.makeText(getContext(), "Da them vao gio hang", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        }
                    }
                });
                thread.start();
            }
        });
        img_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listfood_fravorite.size() == 0){
                    listfood_fravorite.add(food);
                    MySharedPreferences.setList(getContext(), listfood_fravorite);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        img_favorite.setImageResource(R.drawable.ic_favorite_2);
                        food.setChecked(true);
                            for(int i=0;i<listfood_fravorite.size();i++)
                                if (food.getID().equals(listfood_fravorite.get(i).getID())) {
                                    //Toast.makeText(getContext(),"Da co trong Favorite",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            listfood_fravorite.add(food);
                            MySharedPreferences.setList(getContext(), listfood_fravorite);
                    }
                }).start();

            }
        });
        return bottomSheetDialog;
    }
    private void initView(View view){
        tv_inf_price = view.findViewById(R.id.tv_inf_price);
        tv_inf_name = view.findViewById(R.id.tv_inf_name);
        img_inf_photo = view.findViewById(R.id.img_inf_photo);
        btn_huy = view.findViewById(R.id.btn_huy);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);
        img_favorite = view.findViewById(R.id.imb_favorite);
    }
    private void setDataInfFood(){
        if (food == null)
            return;
        tv_inf_price.setText(""+food.getPrice());
        tv_inf_name.setText(""+food.getName());
        img_inf_photo.setImageResource(food.getImage());
    }
}
