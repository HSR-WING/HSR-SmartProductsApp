package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class ProductDto {
    @SerializedName("Id")
    private UUID _id;

    public UUID getId(){
        return this._id;
    }

    @SerializedName("ArticleNumber")
    private String _articleNumber;

    public String getArticleNumber(){
        return this._articleNumber;
    }

    @SerializedName("Name")
    private String _name;

    public String getName(){
        return this._name;
    }

    @SerializedName("ImageUrl")
    private String _imageUrl;

    public String getImageUrl(){
        return this._imageUrl;
    }

    @SerializedName("Price")
    private double _price;

    public double getPrice(){
        return this._price;
    }

    @SerializedName("Weight")
    private double _weight;

    public double getWeight(){
        return this._weight;
    }

    @SerializedName("Retailer")
    private String _retailer;

    public String getRetailer(){
        return this._retailer;
    }
}
