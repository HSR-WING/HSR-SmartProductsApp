package ch.hsr.wing.smartproducts.smartproductbrowser.views.navigation;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IAppSettings;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.ProductCatalogFragment;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.ShoppingCartFragment;

public class NavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final IAppSettings _settings;

    @Inject
    public NavigationListener(IAppSettings settings){
        this._settings = settings;
    }

    private IFragmentViewer _viewer;
    public void setViewer(IFragmentViewer viewer){
        this._viewer = viewer;
    }

    public void removeViewer(){
        this._viewer = null;
    }

    public int getCurrentView(){
        int view = this.getInternalCurrentView();
        if(view != 0){
            return view;
        }
        this.saveCurrentViewTo(R.id.action_product_catalog);
        return this.getInternalCurrentView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        return show(itemId);
    }

    private boolean show(int itemId) {
        boolean success = this.changeView(itemId);
        if(success){
            this.saveCurrentViewTo(itemId);
        }
        return success;
    }

    private boolean changeView(int itemId) {
        if(this._viewer == null){
            return false;
        }
        switch(itemId){
            case R.id.action_shopping_cart:
                return this._viewer.show(new ShoppingCartFragment());
            case R.id.action_product_catalog:
                return this._viewer.show(new ProductCatalogFragment());
        }
        return false;
    }

    private static final String CURRENT_VIEW = "currentView";
    private void saveCurrentViewTo(int itemId) {
        this._settings.setInt(CURRENT_VIEW, itemId);
    }

    private int getInternalCurrentView(){
        return this._settings.getInt(CURRENT_VIEW);
    }
}
