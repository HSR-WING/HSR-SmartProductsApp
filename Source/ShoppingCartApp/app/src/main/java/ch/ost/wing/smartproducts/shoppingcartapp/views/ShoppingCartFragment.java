package ch.ost.wing.smartproducts.shoppingcartapp.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.ost.wing.smartproducts.R;
import ch.ost.wing.smartproducts.databinding.FragmentShoppingCartBinding;
import ch.ost.wing.smartproducts.shoppingcartapp.di.DI;
import ch.ost.wing.smartproducts.shoppingcartapp.viewmodels.ShoppingCartViewModel;
import ch.ost.wing.smartproducts.shoppingcartapp.views.adapters.CartItemsAdapter;
import ch.ost.wing.smartproducts.shoppingcartapp.views.adapters.ViewModelObserver;

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

        this._viewModel = ViewModelProviders.of(this.getActivity(), this._factory).get(ShoppingCartViewModel.class);
        this._viewModel.init();
    }

    @Inject
    Provider<CartItemsAdapter> _adapterFactory;

    private CartItemsAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        FragmentShoppingCartBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_cart, container, false);
        binding.setShoppingCartViewModel(this._viewModel);
        this._viewModel.bind(new ViewModelObserver(binding));

        View view = binding.getRoot();

        ListView list = view.findViewById(R.id.cart_items);
        this._adapter = this._adapterFactory.get();
        list.setAdapter(this._adapter);

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

    @Override
    public void onStop(){
        this._viewModel.unbind();
        super.onStop();
    }
}
