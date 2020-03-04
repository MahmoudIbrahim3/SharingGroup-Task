//
//package com.sharinggroup.task.data.local.entity;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import androidx.room.Entity;
//import androidx.room.TypeConverters;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//import com.sharinggroup.task.data.local.converter.AddressTypeConverter;
//import com.sharinggroup.task.data.local.converter.CompanyTypeConverter;
//import com.sharinggroup.task.data.remote.model.Address;
//import com.sharinggroup.task.data.remote.model.Company;
//
//@Entity(primaryKeys = ("id"))
//public class UserProfileEntity implements Parcelable
//{
//
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    @SerializedName("username")
//    @Expose
//    private String username;
//    @SerializedName("email")
//    @Expose
//    private String email;
//    @SerializedName("address")
//    @Expose
//    @TypeConverters(AddressTypeConverter.class)
//    private Address address;
//    @SerializedName("phone")
//    @Expose
//    private String phone;
//    @SerializedName("website")
//    @Expose
//    private String website;
//    @SerializedName("company")
//    @Expose
//    @TypeConverters(CompanyTypeConverter.class)
//    private Company company;
//    public final static Creator<UserProfileEntity> CREATOR = new Creator<UserProfileEntity>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public UserProfileEntity createFromParcel(Parcel in) {
//            return new UserProfileEntity(in);
//        }
//
//        public UserProfileEntity[] newArray(int size) {
//            return (new UserProfileEntity[size]);
//        }
//
//    }
//    ;
//
//    protected UserProfileEntity(Parcel in) {
//        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        this.name = ((String) in.readValue((String.class.getClassLoader())));
//        this.username = ((String) in.readValue((String.class.getClassLoader())));
//        this.email = ((String) in.readValue((String.class.getClassLoader())));
//        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
//        this.phone = ((String) in.readValue((String.class.getClassLoader())));
//        this.website = ((String) in.readValue((String.class.getClassLoader())));
//        this.company = ((Company) in.readValue((Company.class.getClassLoader())));
//    }
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public UserProfileEntity() {
//    }
//
//    /**
//     *
//     * @param website
//     * @param address
//     * @param phone
//     * @param name
//     * @param company
//     * @param id
//     * @param email
//     * @param username
//     */
//    public UserProfileEntity(Integer id, String name, String username, String email, Address address, String phone, String website, Company company) {
//        super();
//        this.id = id;
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.address = address;
//        this.phone = phone;
//        this.website = website;
//        this.company = company;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public void setWebsite(String website) {
//        this.website = website;
//    }
//
//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(id);
//        dest.writeValue(name);
//        dest.writeValue(username);
//        dest.writeValue(email);
//        dest.writeValue(address);
//        dest.writeValue(phone);
//        dest.writeValue(website);
//        dest.writeValue(company);
//    }
//
//    public int describeContents() {
//        return  0;
//    }
//
//}
