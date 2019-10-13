package com.rajnikanth.booze;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Activity activity;
    FragmentManager supportFragmentManager;

//    public HomeFragment(MainActivity activity, FragmentManager supportFragmentManager) {
//        this.activity = activity;
//        this.supportFragmentManager = supportFragmentManager;
//    }

    public HomeFragment() {

    }

    public HomeFragment(Activity activity, FragmentManager supportFragmentManager) {
        this.activity = activity;
        this.supportFragmentManager = supportFragmentManager;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        GridView gridview = view.findViewById(R.id.gridview);
        gridview.setAdapter(new HomeProductsAdapter(activity, productCategoryDOList()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long result) {
//                MainActivity.viewPager.setAdapter(new HomePageAdapter(activity, supportFragmentManager,"test"));
                MainActivity.viewPager.setCurrentItem(1, true);
                MainActivity.bubbleNavigationLinearView.setCurrentActiveItem(1);
            }
        });
        return view;
    }

    private List<ProductCategoryDO> productCategoryDOList() {
        List<ProductCategoryDO> productCategoryDOList = new ArrayList<>();
        try {
            productCategoryDOList.add(new ProductCategoryDO("WW01", "WHITEWINE", R.drawable.whitewine));
            productCategoryDOList.add(new ProductCategoryDO("RW02", "REDWINE", R.drawable.redwine));
            productCategoryDOList.add(new ProductCategoryDO("CP03", "CHAMPAGNE", R.drawable.champagne));
            productCategoryDOList.add(new ProductCategoryDO("VM04", "VERMOUTH", R.drawable.vermouth));
            productCategoryDOList.add(new ProductCategoryDO("RM05", "RUM", R.drawable.rum));
            productCategoryDOList.add(new ProductCategoryDO("WS06", "WHISKEY", R.drawable.whiskey));
            productCategoryDOList.add(new ProductCategoryDO("TQ07", "TEQUILA", R.drawable.tequila));
            productCategoryDOList.add(new ProductCategoryDO("CG08", "COGNAC", R.drawable.cognac));
            productCategoryDOList.add(new ProductCategoryDO("VD09", "VODKA", R.drawable.vodka));
            productCategoryDOList.add(new ProductCategoryDO("GN10", "GIN", R.drawable.gin));
            productCategoryDOList.add(new ProductCategoryDO("LQ11", "LIQUEUR", R.drawable.liqueur));
            productCategoryDOList.add(new ProductCategoryDO("BR12", "BEER", R.drawable.beer));
        } catch (Exception e) {
            e.getMessage();
        }
        return productCategoryDOList;
    }
}
