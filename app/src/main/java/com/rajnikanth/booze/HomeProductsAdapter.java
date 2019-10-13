package com.rajnikanth.booze;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class HomeProductsAdapter extends BaseAdapter {
    List<ProductCategoryDO> productCategoryDOList;
    Activity activity;

    public HomeProductsAdapter(Activity activity, List<ProductCategoryDO> productCategoryDOList) {
        this.activity = activity;
        this.productCategoryDOList = productCategoryDOList;
    }

    @Override
    public int getCount() {
        return productCategoryDOList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        view = activity.getLayoutInflater().inflate(R.layout.home_product_view, null, true);
        ImageView imageView = view.findViewById(R.id.imv_image);
        imageView.setImageResource(productCategoryDOList.get(i).getCategoryPreviewId());
        return view;
    }
}
