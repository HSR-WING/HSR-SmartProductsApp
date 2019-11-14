package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
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
    private String _data;

    public JsonObject getData(){
        return new Gson().fromJson(this._data, JsonObject.class);
    }
}
