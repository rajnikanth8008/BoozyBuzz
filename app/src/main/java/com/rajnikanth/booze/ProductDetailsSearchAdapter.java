package com.rajnikanth.booze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsSearchAdapter extends RecyclerView.Adapter<ProductDetailsSearchAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private List<ProductDetailsDO> mDataset = new ArrayList<>();
    private List<ProductDetailsDO> mFilteredDataset = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_productTitle, tv_producttag, tv_product_age, tv_price, tv_productdisc;
        public ImageView img_product;

        //        public TextView code;
        public ViewHolder(View view) {
            super(view);
            tv_productTitle = view.findViewById(R.id.tv_productTitle);
            tv_producttag = view.findViewById(R.id.tv_producttag);
            tv_product_age = view.findViewById(R.id.tv_product_age);
            tv_price = view.findViewById(R.id.tv_price);
            tv_productdisc = view.findViewById(R.id.tv_productdisc);
            img_product = view.findViewById(R.id.img_product);
        }
    }

    public ProductDetailsSearchAdapter(Context context, List<ProductDetailsDO> dataset) {
        mContext = context;
        mDataset.addAll(dataset);
        mFilteredDataset.addAll(dataset);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                //The search string we pass from Activity class
                String filterString = charSequence.toString().toLowerCase();
                List<ProductDetailsDO> filteredDataset = new ArrayList<>();

                //If Search String is empty
                if (filterString.isEmpty()) {
                    filteredDataset.addAll(mDataset);
                } else {
                    //If Search String is not empty
                    for (ProductDetailsDO data : mDataset) {
                        //Filtering Originaal daata by using contains() method on each data item
                        if (data.getProductTitle().toLowerCase().contains(filterString)) {/* || data.getProductId().toLowerCase().contains(filterString)*/
                            filteredDataset.add(data);
                        }
                    }
                }

                //Passing Filter results to publishResults() method
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDataset;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //Updating Adapter Dataset
                mFilteredDataset = (ArrayList<ProductDetailsDO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ProductDetailsSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_detailslist_view, null);
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
//        int height = windowManager.getDefaultDisplay().getHeight();
        itemView.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProductDetailsSearchAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsSearchAdapter.ViewHolder holder, int position) {
        holder.tv_productTitle.setText(mFilteredDataset.get(position).getProductTitle());
        holder.tv_producttag.setText(mFilteredDataset.get(position).getProductTag());
        holder.tv_product_age.setText(mFilteredDataset.get(position).getProductAge());
        holder.tv_price.setText(mFilteredDataset.get(position).getProductPrice());
        holder.tv_productdisc.setText(mFilteredDataset.get(position).getProductDescription());
        holder.img_product.setImageResource(mFilteredDataset.get(position).getProductPreviewId());
    }

    @Override
    public int getItemCount() {
        return mFilteredDataset.size();
    }

    public void update(List<ProductDetailsDO> newData) {

        mDataset.addAll(newData);
        mFilteredDataset.addAll(newData);

        notifyDataSetChanged();
    }
}
