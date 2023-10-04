package com.ericsospedra.country;

import android.content.res.Resources;
import android.widget.ImageView;

public class Country {
    private String countryCode;
    private String countryName;
    private long population;
    private String capital;
    private String isoAlpha3;

    public Country(String countryCode, String countryName, long population, String capital, String isoAlpha3) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.population = population;
        this.capital = capital;
        this.isoAlpha3 = isoAlpha3;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public long getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

}
