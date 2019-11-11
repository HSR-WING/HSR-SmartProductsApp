package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.IAdapterBinding;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ProductViewModel;

public class ProductsAdapter extends BaseAdapter implements IAdapterBinding<Product> {

    private final Provider<ProductViewModel> _vmFactory;

    @Inject
    public ProductsAdapter(Provider<ProductViewModel> vmFactory){
        this._vmFactory = vmFactory;
    }

    private List<ProductViewModel> _productViewModels = new ArrayList<>();

    public void refresh(Iterable<Product> products){
        List<ProductViewModel> vms = new ArrayList<>();
        for (Product product: products) {
            ProductViewModel vm = this._vmFactory.get();
            vm.init(product);
            vms.add(vm);
        }
        this._productViewModels = vms;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this._productViewModels.size();
    }

    @Override
    public Object getItem(int position) {
        return this._productViewModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this._productViewModels.get(0).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
