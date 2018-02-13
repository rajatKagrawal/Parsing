package com.example.rajat.parsing;

/**
 * Created by rajat on 13/2/18.
 */

public class Country {
    //@SerializedName("country")
    String country;
    //@SerializedName("population")
    int population;
    //@SerializedName("rank")
    int rank;
    //@SerializedName("flag")
    String imageurl;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
