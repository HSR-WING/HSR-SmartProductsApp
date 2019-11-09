package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;


public class ProductInfoDto {
    @SerializedName("Id")
    private UUID _id;

    public UUID getId(){
        return this._id;
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
}
