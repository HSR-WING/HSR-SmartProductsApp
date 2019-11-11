package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import android.graphics.Bitmap;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.IProductRepository;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

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
            return this._app.getImage(R.drawable.ic_local_pizza_black_24dp);
        }
        return this._repo.getImageOf(this._model.getId());
    }
}
