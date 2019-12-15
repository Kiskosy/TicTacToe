package university.tictactoe.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserModel")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    int uid;
    @ColumnInfo(name = "id")
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "username")
    String username;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "phone")
    String phone;
    @ColumnInfo(name = "website")
    String website;
    @ColumnInfo(name = "address_street")
    String address_street;
    @ColumnInfo(name = "address_suite")
    String address_suite;
    @ColumnInfo(name = "address_city")
    String address_city;
    @ColumnInfo(name = "address_zipcode")
    String address_zipcode;
    @ColumnInfo(name = "geo_lat")
    String geo_lat;
    @ColumnInfo(name = "geo_lng")
    String geo_lng;
    @ColumnInfo(name = "company_name")
    String company_name;
    @ColumnInfo(name = "company_catchPhrase")
    String company_catchPhrase;
    @ColumnInfo(name = "company_bs")
    String company_bs;

    @Ignore
    public UserModel() {
    }

    @Ignore
    public UserModel(String username){
        this.username = username;
    }

    public UserModel(int id, String name, String username, String email, String phone, String website, String address_street, String address_suite, String address_city, String address_zipcode, String geo_lat, String geo_lng, String company_name, String company_catchPhrase, String company_bs) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address_street = address_street;
        this.address_suite = address_suite;
        this.address_city = address_city;
        this.address_zipcode = address_zipcode;
        this.geo_lat = geo_lat;
        this.geo_lng = geo_lng;
        this.company_name = company_name;
        this.company_catchPhrase = company_catchPhrase;
        this.company_bs = company_bs;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_suite() {
        return address_suite;
    }

    public void setAddress_suite(String address_suite) {
        this.address_suite = address_suite;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_zipcode() {
        return address_zipcode;
    }

    public void setAddress_zipcode(String address_zipcode) {
        this.address_zipcode = address_zipcode;
    }

    public String getGeo_lat() {
        return geo_lat;
    }

    public void setGeo_lat(String geo_lat) {
        this.geo_lat = geo_lat;
    }

    public String getGeo_lng() {
        return geo_lng;
    }

    public void setGeo_lng(String geo_lng) {
        this.geo_lng = geo_lng;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_catchPhrase() {
        return company_catchPhrase;
    }

    public void setCompany_catchPhrase(String company_catchPhrase) {
        this.company_catchPhrase = company_catchPhrase;
    }

    public String getCompany_bs() {
        return company_bs;
    }

    public void setCompany_bs(String company_bs) {
        this.company_bs = company_bs;
    }
}
