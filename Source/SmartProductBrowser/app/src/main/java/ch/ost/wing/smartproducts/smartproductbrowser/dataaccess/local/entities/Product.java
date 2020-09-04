package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "Products")
public class Product {

    public static Product getDeletable(UUID id){
        return new Product(id, "", "");
    }

    public Product(){

    }

    @Ignore
    public Product(UUID id, String name, String imageUrl){
        this._id = id;
        this._name = name;
        this._imageUrl = imageUrl;
    }

    @PrimaryKey
    @ColumnInfo(name="Id")
    @NotNull
    private UUID _id;
    public UUID getId(){
        return this._id;
    }
    public void setId(UUID value){
        this._id = value;
    }

    @ColumnInfo(name="ArticleNumber")
    private String _articleNumber;
    public String getArticleNumber(){
        return this._articleNumber;
    }
    public void setArticleNumber(String value){
        this._articleNumber = value;
    }

    @ColumnInfo(name="Name")
    private String _name;
    public String getName(){
        return this._name;
    }
    public void setName(String value){
        this._name = value;
    }

    @ColumnInfo(name="ImageUrl")
    private String _imageUrl;
    public String getImageUrl(){
        return this._imageUrl;
    }
    public void setImageUrl(String value){
        this._imageUrl = value;
    }

    @ColumnInfo(name="Price")
    private double _price;
    public double getPrice(){
        return this._price;
    }
    public void setPrice(double value){
        this._price = value;
    }

    @ColumnInfo(name="Weight")
    private double _weight;
    public double getWeight(){
        return this._weight;
    }
    public void setWeight(double value){
        this._weight = value;
    }

    @ColumnInfo(name="Retailer")
    private String _retailer;
    public String getRetailer(){
        return this._retailer;
    }
    public void setRetailer(String value){
        this._retailer = value;
    }
}
