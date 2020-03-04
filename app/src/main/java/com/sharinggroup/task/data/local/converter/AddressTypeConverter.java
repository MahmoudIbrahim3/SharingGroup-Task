package com.sharinggroup.task.data.local.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.sharinggroup.task.data.remote.model.Address;

public class AddressTypeConverter {

    @TypeConverter
    public Address toAddress(String value) {
        return new Gson().fromJson(value, Address.class);
    }

    @TypeConverter
    public String fromAddress(Address address) {
        return new Gson().toJson(address);
    }
}
