package com.rajnikanth.booze;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsList extends AppCompatActivity {
    public List<ProductDetailsDO> mDataSet;
    TextView tv_product_details_list;
    EditText et_product_details_list;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductDetailsDO> productDetailsDOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_list);
        mRecyclerView = findViewById(R.id.rcv_product_details_list);
        tv_product_details_list = findViewById(R.id.tv_product_details_list);
        et_product_details_list = findViewById(R.id.et_product_details_list);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        productDetailsDOList = new ArrayList<>();
        mDataSet = new ArrayList<>();
        mAdapter = new ProductDetailsSearchAdapter(this, mDataSet);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                ProductDetailsDO productDetailsDO = productDetailsDOList.get(position);
//                Toast.makeText(ProductDetailsList.this, productDetailsDO.getProductTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailsList.this, ProductDetails.class);
                intent.putExtra("Position", position);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        et_product_details_list.setHint("Search Products here...");
        et_product_details_list.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Passing Search String getFilter(0 method in Adapter
                ProductDetailsSearchAdapter adapter = (ProductDetailsSearchAdapter) mRecyclerView.getAdapter();
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        ProductDetailsSearchAdapter adapter = (ProductDetailsSearchAdapter) mRecyclerView.getAdapter();
        productDetailsDOList = productDetailsList();
        adapter.update(productDetailsDOList);
    }

    private List<ProductDetailsDO> productDetailsList() {
        List<ProductDetailsDO> productDetailsList = new ArrayList<>();
        try {
            productDetailsList.add(new ProductDetailsDO("PD01",
                    "WHITEWINE",
                    "single malt scotch whisky",
                    "12 Years old",
                    "2500",
                    "For many years, Glenkinchie was one of just two Lowland distilleries operating",
                    R.drawable.glenkinchie));

            productDetailsList.add(new ProductDetailsDO("PD02",
                    "Blenders Pride",
                    "Indian whisky",
                    "regular",
                    "1000",
                    "It is a blend of Indian grain spirits and imported Scotch malt and contains no artificial flavouring",
                    R.drawable.blenderspride));

            productDetailsList.add(new ProductDetailsDO("PD03",
                    "Teacher's whisky",
                    "Indian whisky",
                    "regular",
                    "1600",
                    "It uses fully smoked peated single malt whisky from The Ardmore distillery, with this single malt as its fingerprint whisky",
                    R.drawable.teachers));

            productDetailsList.add(new ProductDetailsDO("PD04",
                    "Doctors whisky",
                    "Doctors' Special Scotch Whisky ",
                    "regular",
                    "6137",
                    "Doctors' Special Blended Scotch Whisky was produced by Robert McNish & Co in Glasgow and even had a By Appointment warrant from King Gustaf VI Adolf of Sweden",
                    R.drawable.doctorsspecial));

            productDetailsList.add(new ProductDetailsDO("PD05",
                    "CHIVAS REGAL",
                    "Chivas Regal is a blended Scotch",
                    "12 Years old",
                    "3427",
                    "Chivas Regal traces its roots back to 1801. Chivas Regal's home is Strathisla distillery at Keith, Moray in Speyside, Scotland, the oldest operating Highland distillery, which was founded in 1786",
                    R.drawable.chivasregal));

        } catch (Exception e) {
            e.getMessage();
        }
        return productDetailsList;
    }
}
