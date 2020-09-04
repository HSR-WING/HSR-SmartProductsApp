package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class DataDto {
    @SerializedName("Id")
    private UUID _id;

    public UUID getId(){
        return this._id;
    }

    @SerializedName("Timestamp")
    private Date _timestamp;

    public Date getTimestamp(){
        return this._timestamp;
    }

    @SerializedName("Data")
    private JsonObject _data;

    public JsonObject getData(){
        return this._data;
    }
}
