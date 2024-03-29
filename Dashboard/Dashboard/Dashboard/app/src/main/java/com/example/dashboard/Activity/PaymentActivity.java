package com.example.dashboard.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dashboard.Adapter.CartAdapter;
import com.example.dashboard.Helper.ChangeNumberItemsListener;
import com.example.dashboard.Helper.ManagementCart;
import com.example.dashboard.R;

import java.text.DecimalFormat;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, deliveryTxt, disDeliveryTxt, discountTxt, totalTxt;
    private double discount;
    private ScrollView scrollView;
    private ImageView backBtn;

    private RadioButton cashBtn, creditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        managementCart = new ManagementCart(this);

        initView();
        setVariable();
        caculateCart();
        initList();
    }

    //Hàm tạo danh sách mặt hàng trong giỏ hàng và hiển thị
    private void initList() {
        //kiểm tra giỏ hàng

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                caculateCart();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    //Hàm tình tiền
    private void caculateCart() {
        DecimalFormat formatter = new DecimalFormat("#");

        double percentDiscount = 0.1;
        double disDelivery = 0;
        double delivery = 20000;

        discount = Math.round(managementCart.getTotalPrice() * percentDiscount * 100.0) / 100.0;

        if (managementCart.getTotalPrice() > 1000000){
            disDelivery = delivery;
        }

        double itemTotal = Math.round(managementCart.getTotalPrice() * 100) / 100;
        double total = Math.round((managementCart.getTotalPrice() + delivery - discount - disDelivery) * 100) / 100;

        totalFeeTxt.setText(formatter.format(itemTotal) + " VNĐ");
        discountTxt.setText(formatter.format(discount) + " VNĐ");
        deliveryTxt.setText(formatter.format(delivery) + " VNĐ");
        disDeliveryTxt.setText(formatter.format(disDelivery) + " VNĐ");
        totalTxt.setText(formatter.format(total) + " VNĐ");
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> {
            finish();
        });

        creditBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    cashBtn.setChecked(false);
                }
            }
        });

        cashBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    creditBtn.setChecked(false);
                }
            }
        });
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        disDeliveryTxt = findViewById(R.id.disDeliveryTxt);
        discountTxt = findViewById(R.id.discountTxt);
        totalTxt = findViewById(R.id.totalTxt);

        recyclerView = findViewById(R.id.paymentView);

        scrollView = findViewById(R.id.scrollView);

        backBtn = findViewById(R.id.backBtn);

        cashBtn = findViewById(R.id.cashBtn);
        creditBtn = findViewById(R.id.creditBtn);
    }
}