package com.rajnikanth.booze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductsListSearchAdapter extends RecyclerView.Adapter<ProductsListSearchAdapter.ViewHolder> implements Filterable {

    private Context mContext;

    //Original Data List and Filtered Data List
    private List<ProductsListDO> mDataset = new ArrayList<>();
    private List<ProductsListDO> mFilteredDataset = new ArrayList<>();

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
//        public TextView code;
        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_productname);
//            code = (TextView) view.findViewById(R.id.code);
        }
    }

    //Contructor
    //Here we are adding same data to Original and Filtered List, we get as parameter
    //because in starting no filter/search string exists
    public ProductsListSearchAdapter(Context context, List<ProductsListDO> dataset) {
        mContext = context;
        mDataset.addAll(dataset);
        mFilteredDataset.addAll(dataset);
    }

    @Override
    public ProductsListSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_view, null);
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
//        int height = windowManager.getDefaultDisplay().getHeight();
        itemView.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProductsListSearchAdapter.ViewHolder(itemView);
    }

    //Binding each item from List to a ViewHolder
    //You must customize ViewHolder and onBindViewHolder according to your dataset
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mFilteredDataset.get(position).getProductName());
//        holder.name.setTag(mFilteredDataset.get(position).getProductId());
    }

    @Override
    public int getItemCount() {
        return mFilteredDataset.size();
    }

    //It is the method that we override/define from Filterable Interface
    //It returns a Filter Object which implements two methods, performFiltering() and publishResults()
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                //The search string we pass from Activity class
                String filterString = charSequence.toString().toLowerCase();
                List<ProductsListDO> filteredDataset = new ArrayList<>();

                //If Search String is empty
                if (filterString.isEmpty()) {
                    filteredDataset.addAll(mDataset);
                } else {
                    //If Search String is not empty
                    for (ProductsListDO data : mDataset) {
                        //Filtering Originaal daata by using contains() method on each data item
                        if (data.getProductName().toLowerCase().contains(filterString)) {/* || data.getProductId().toLowerCase().contains(filterString)*/
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
                mFilteredDataset = (ArrayList<ProductsListDO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // It is not necessary, to provide implementation for this method
    // I use this method to update Adapter Dataset with more data, if we have an infinite dataset from Internet
    public void update(List<ProductsListDO> newData){

        mDataset.addAll(newData);
        mFilteredDataset.addAll(newData);

        notifyDataSetChanged();
    }

}

