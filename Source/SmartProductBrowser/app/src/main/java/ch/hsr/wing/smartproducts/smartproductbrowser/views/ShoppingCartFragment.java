package ch.hsr.wing.smartproducts.smartproductbrowser.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ShoppingCartViewModel;

public class ShoppingCartFragment extends Fragment {

    private ShoppingCartViewModel _viewModel;

    @Inject
    ViewModelProvider.Factory _factory;

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        DI.container(this).inject(this);
        super.onCreate(savedInstanceState);

        this._viewModel = ViewModelProviders.of(this, this._factory).get(ShoppingCartViewModel.class);
        this._viewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        return view;
    }


}
