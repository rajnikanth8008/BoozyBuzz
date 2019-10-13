package com.rajnikanth.booze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText mFilter;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public List<ProductsListDO> mDataSet;
    Activity activity;
    String listOfProducts;

    /*Random Product Name generation*/
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final java.util.Random rand = new java.util.Random();
    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<>();
    List<ProductsListDO> recyclerSearchModelList;
    /****************/

    public SearchFragment(Activity activity, String listOfProducts) {
        this.activity = activity;
        this.listOfProducts = listOfProducts;
        // Required empty public constructor
    }

    public SearchFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mFilter = view.findViewById(R.id.filter);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        recyclerSearchModelList = new ArrayList<>();

        //An emplty Data List
        mDataSet = new ArrayList<>();

        //Instantiating ProductsListSearchAdapter with empty Data List
        mAdapter = new ProductsListSearchAdapter(activity, mDataSet);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                ProductsListDO productsListDO = recyclerSearchModelList.get(position);
//                Toast.makeText(activity, productsListDO.getProductName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity,ProductDetailsList.class);
                activity.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mFilter.setHint("Search " + listOfProducts + " Products here...");

        //Adding Text Change Listener on Edittext, to get updated Search Input from User
        mFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Passing Search String getFilter(0 method in Adapter
                ProductsListSearchAdapter adapter = (ProductsListSearchAdapter) mRecyclerView.getAdapter();
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        fetchData();
        return view;
    }

    // Fetching dat from local or Internet
    public void fetchData() {
        //Here you should write your code to fetch your data from local storage or from Internet

        /*List<RecyclerSearchModel> fetchedData = <fetched_data>;*/
        //Then update the ProductsListSearchAdapter with updated dataset
        ProductsListSearchAdapter adapter = (ProductsListSearchAdapter) mRecyclerView.getAdapter();
        adapter.update(fetchedData());
    }

    private List<ProductsListDO> fetchedData() {
        for (int i = 0; i < 15; i++) {
            ProductsListDO productsListDO = new ProductsListDO();
//            productsListDO.setProductId(randomIdentifier());
            productsListDO.setProductName(randomIdentifier());
            recyclerSearchModelList.add(productsListDO);
        }
        return recyclerSearchModelList;
    }

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
/*http://www.all-about-wine.com/white-wine-list.html*/
