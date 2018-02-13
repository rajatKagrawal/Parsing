package com.example.rajat.parsing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rajat on 13/2/18.
 */

public class CountryList {
    @SerializedName("worldpopulation")
    @Expose
    private ArrayList<Country> country = new ArrayList<>();

    public ArrayList<Country> getCountry() {
        return country;
    }
    public void setContacts(ArrayList<Country> country) {
        this.country=country;
    }
}
