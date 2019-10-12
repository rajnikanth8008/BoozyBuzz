package com.rajnikanth.booze;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HomeProductsAdapter extends BaseAdapter {
    Integer[] mThumbIds;
    Activity activity;

    public HomeProductsAdapter(Activity activity, Integer[] mThumbIds) {
        this.activity = activity;
        this.mThumbIds = mThumbIds;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
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
        imageView.setImageResource(mThumbIds[i]);
        return view;
    }
}
