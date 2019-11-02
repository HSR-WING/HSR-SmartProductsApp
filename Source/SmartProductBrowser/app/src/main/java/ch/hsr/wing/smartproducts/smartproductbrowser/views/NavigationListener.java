package ch.hsr.wing.smartproducts.smartproductbrowser.views;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ch.hsr.wing.smartproducts.R;

public class NavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final IFragmentViewer _viewer;

    public NavigationListener(IFragmentViewer viewer){
        this._viewer = viewer;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.action_shopping_cart:
                return this._viewer.show(new ShoppingCartFragment());
            case R.id.action_product_catalog:
                return this._viewer.show(new ProductCatalogFragment());
        }
        return false;
    }
}
