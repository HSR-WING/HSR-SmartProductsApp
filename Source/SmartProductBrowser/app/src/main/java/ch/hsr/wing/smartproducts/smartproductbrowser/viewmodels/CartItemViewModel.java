package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import android.graphics.Bitmap;

import java.text.DecimalFormat;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.IProductRepository;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.CartItem;

public class CartItemViewModel {

    private final DecimalFormat df = new DecimalFormat("##0.00");

    private final IProductRepository _repo;
    private final IApp _app;

    @Inject
    public CartItemViewModel(IProductRepository repo, IApp app){
        this._repo = repo;
        this._app = app;
    }

    private CartItem _model;
    private Product _product;

    public void init(CartItem model){
        this._model = model;
        this._product = this._repo.get(this._model.getId());
    }

    private boolean hasProduct(){
        return this._product != null;
    }

    public String getName(){
        if(!this.hasProduct()){
            return this._app.getString(R.string.unknown_product);
        }
        return this._product.getName();
    }

    public String getArticleNumber(){
        if(!this.hasProduct()){
            return "";
        }
        return this._product.getArticleNumber();
    }

    public String getAmount(){
        return Integer.toString(this._model.getNumber()) + "x";
    }

    public String getPricePerItem(){
        if(!this.hasProduct()){
            return "";
        }
        return df.format(this._product.getPrice());
    }

    public String getTotalPrice(){
        if(!this.hasProduct()){
            return "";
        }
        return df.format(this._model.getNumber() * this._product.getPrice());
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
