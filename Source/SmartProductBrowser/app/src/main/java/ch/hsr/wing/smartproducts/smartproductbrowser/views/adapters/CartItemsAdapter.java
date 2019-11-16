package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.databinding.CartitemBinding;
import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.CartItem;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.CartItemViewModel;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.IAdapterBinding;

public class CartItemsAdapter extends BaseAdapter implements IAdapterBinding<CartItem> {

    private final Provider<CartItemViewModel> _vmFactory;
    private final IApp _app;

    @Inject
    public CartItemsAdapter(Provider<CartItemViewModel> vmfactory, IApp app){
        this._vmFactory = vmfactory;
        this._app = app;
    }

    private List<CartItemViewModel> _cartItemViewModels = new ArrayList<>();

    @Override
    public void refresh(Iterable<CartItem> items) {
        List<CartItemViewModel> vms = new ArrayList<>();
        for(CartItem item : items){
            CartItemViewModel vm = this._vmFactory.get();
            vm.init(item);
            vms.add(vm);
        }
        this._cartItemViewModels = vms;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this._cartItemViewModels.size();
    }

    private CartItemViewModel getCartItemAt(int position){
        return this._cartItemViewModels.get(position);
    }

    @Override
    public Object getItem(int position) {
        return this.getCartItemAt(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getCartItemAt(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BindingViewHolder<CartitemBinding> holder;

        if(convertView == null){
            CartitemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this._app.getAppContext()), R.layout.cartitem, parent, false);
            holder = new BindingViewHolder<>(binding);
            holder.getView().setTag(holder);
        } else {
            holder = (BindingViewHolder<CartitemBinding>)convertView.getTag();
        }
        holder.getBinding().setCartItemViewModel(this.getCartItemAt(position));
        return holder.getView();
    }


}
