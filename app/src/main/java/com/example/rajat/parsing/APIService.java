package com.example.rajat.parsing;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
/**
 * Created by rajat on 13/2/18.
 */

public @interface APIService {
    @GET("/tutorial/jsonparsetutorial.txt")
    Observable <List<Country>> getcountrydata();
}
