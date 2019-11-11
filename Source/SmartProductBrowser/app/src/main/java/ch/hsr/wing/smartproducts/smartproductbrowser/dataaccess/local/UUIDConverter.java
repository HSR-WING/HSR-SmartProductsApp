package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import androidx.room.TypeConverter;

import java.util.UUID;

public class UUIDConverter {

    @TypeConverter
    public static String fromId(UUID id){
        return id.toString();
    }

    @TypeConverter
    public static UUID stringToId(String id){
        return UUID.fromString(id);
    }
}
