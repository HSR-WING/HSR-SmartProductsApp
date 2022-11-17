package ch.ost.wing.smartproducts.shoppingcartapp.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.ost.wing.smartproducts.R;
import ch.ost.wing.smartproducts.shoppingcartapp.di.DI;
import ch.ost.wing.smartproducts.shoppingcartapp.viewmodels.ProductCatalogViewModel;
import ch.ost.wing.smartproducts.shoppingcartapp.views.adapters.ProductsAdapter;

public class ProductCatalogFragment extends Fragment {

    private ProductCatalogViewModel _viewModel;

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

        this._viewModel = ViewModelProviders.of(this.getActivity(), this._factory).get(ProductCatalogViewModel.class);
        this._viewModel.init();
    }

    @Inject
    Provider<ProductsAdapter> _adapterFactory;

    private ProductsAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_product_catalog, container, false);

        GridView grid = view.findViewById(R.id.product_catalog);
        this._adapter = this._adapterFactory.get();
        grid.setAdapter(this._adapter);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        this._viewModel.bind(this._adapter);
        this._viewModel.refresh();
    }

    @Override
    public void onPause(){
        this._viewModel.unbind(this._adapter);
        super.onPause();
    }
}
