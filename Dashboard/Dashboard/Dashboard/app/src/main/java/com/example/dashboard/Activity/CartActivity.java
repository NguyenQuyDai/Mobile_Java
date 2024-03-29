package com.example.dashboard.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dashboard.Adapter.CartAdapter;
import com.example.dashboard.Domain.PopularDomain;
import com.example.dashboard.Helper.ChangeNumberItemsListener;
import com.example.dashboard.Helper.ManagementCart;
import com.example.dashboard.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private CartAdapter.Viewholder cartAdapter;

    private RecyclerView recyclerView;
    ArrayList<PopularDomain> listItem;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, deliveryTxt, totalTxt;
    private ScrollView scrollView;
    private ImageView backBtn;
    private Button checkOutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);
        listItem = (ArrayList<PopularDomain>) managementCart.getListCart();

        initView();
        setVariable();
        caculateCart();
        initList();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    String deleleItem = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            if (direction == ItemTouchHelper.LEFT) {
                if (position < listItem.size()) {
                    deleleItem = String.valueOf(listItem.get(position));
                    listItem.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView, deleleItem, Snackbar.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView1, @NonNull RecyclerView.ViewHolder viewHolder1
                , float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(CartActivity.this, c, recyclerView1, viewHolder1, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(CartActivity.this, R.color.red))
                            .addSwipeLeftActionIcon(R.drawable.delete)
                                    .create()
                                            .decorate();

            super.onChildDraw(c, recyclerView1, viewHolder1, dX, dY, actionState, isCurrentlyActive);
        }
    };


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

        double delivery = 20000;

        double itemTotal = Math.round(managementCart.getTotalPrice() * 100) / 100;
        double total = Math.round((managementCart.getTotalPrice() + delivery) * 100) / 100;

        totalFeeTxt.setText(formatter.format(itemTotal) + " VNĐ");
        deliveryTxt.setText(formatter.format(delivery) + " VNĐ");
        totalTxt.setText(formatter.format(total) + " VNĐ");
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> {
            finish();
        });

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, PaymentActivity.class));
            }
        });
    }



    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);

        checkOutBtn = findViewById(R.id.checkOutBtn);

        recyclerView = findViewById(R.id.cartView);

        scrollView = findViewById(R.id.scrollView);

        backBtn = findViewById(R.id.backBtn);
    }
}