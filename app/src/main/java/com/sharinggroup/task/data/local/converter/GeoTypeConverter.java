package com.sharinggroup.task.data.local.converter;

import android.location.Address;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.sharinggroup.task.data.remote.model.Geo;

public class GeoTypeConverter {

    @TypeConverter
    public Geo toGeo(String value) {
        return new Gson().fromJson(value, Geo.class);
    }

    @TypeConverter
    public String fromGeo(Geo geo) {
        return new Gson().toJson(geo);
    }
}
