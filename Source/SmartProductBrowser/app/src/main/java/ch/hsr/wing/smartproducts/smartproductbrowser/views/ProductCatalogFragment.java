package ch.hsr.wing.smartproducts.smartproductbrowser.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import ch.hsr.wing.smartproducts.R;

public class ProductCatalogFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_product_catalog, container, false);
        return view;
    }

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
    }
}
