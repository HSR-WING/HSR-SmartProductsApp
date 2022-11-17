package ch.ost.wing.smartproducts.shoppingcartapp.viewmodels;

import android.graphics.Bitmap;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.R;
import ch.ost.wing.smartproducts.shoppingcartapp.IApp;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.IProductRepository;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.entities.Product;

public class ProductViewModel {

    private final IProductRepository _repo;
    private final IApp _app;

    @Inject
    public ProductViewModel(IProductRepository repo, IApp app){
        this._repo = repo;
        this._app = app;
    }

    private Product _model;

    public void init(Product model){
        this._model = model;
    }

    public String getName(){
        if(this._model == null){
            return "";
        }
        return this._model.getName();
    }

    public Bitmap getImage(){
        if(this._model == null){
            return this._app.getImageFromDrawable(R.drawable.ic_local_pizza_black_24dp);
        }
        if(!this._repo.hasImage(this._model.getId())){
            return this._app.getImageFromDrawable(R.drawable.ic_local_pizza_black_24dp);
        }
        return this._repo.getImageOf(this._model.getId());
    }
}
