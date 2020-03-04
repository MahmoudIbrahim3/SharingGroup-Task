package com.sharinggroup.task.data.local.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.sharinggroup.task.data.remote.model.Company;

public class CompanyTypeConverter {

    @TypeConverter
    public Company toCompany(String value) {
        Company company = new Gson().fromJson(value, Company.class);
        return company;
    }

    @TypeConverter
    public String fromCompany(Company company) {
        return new Gson().toJson(company);
    }
}
