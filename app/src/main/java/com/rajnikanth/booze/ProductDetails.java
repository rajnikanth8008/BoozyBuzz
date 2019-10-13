package com.rajnikanth.booze;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    RecyclerView recycler_view;
    List<ProductDetailsDO> productDetailsList;
    TextView tv_count, tv_product_title, tv_sub_title, tv_product_price;
    Button btn_buy;
    ImageView imv_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.product_details);

            tv_product_title = findViewById(R.id.tv_product_title);
            tv_sub_title = findViewById(R.id.tv_sub_title);
            tv_product_price = findViewById(R.id.tv_product_price);
            imv_product = findViewById(R.id.imv_product);
            btn_buy = findViewById(R.id.btn_buy);

            tv_count = findViewById(R.id.tv_count);

            recycler_view = findViewById(R.id.recycler_view);
            recycler_view.setHasFixedSize(true);
            recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

            productDetailsList = new ArrayList<>();
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

            tv_count.setText(productDetailsList.size() + "");
            final RecyclerAdapter adapter = new RecyclerAdapter(this, productDetailsList);
            recycler_view.setAdapter(adapter);

            int selectedPos = getIntent().getIntExtra("Position", 0);
            tv_product_title.setText(productDetailsList.get(selectedPos).getProductTitle());
            tv_sub_title.setText(productDetailsList.get(selectedPos).getProductTag());
            tv_product_price.setText(productDetailsList.get(selectedPos).getProductPrice());
            imv_product.setImageResource(productDetailsList.get(selectedPos).getProductPreviewId());
            btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ProductDetails.this,"Item added to cart",Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
