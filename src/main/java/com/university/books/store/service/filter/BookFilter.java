package com.university.books.store.service.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Aleksandr on 5/27/2017.
 */
@PropertySource(value = {"classpath:defaultBookFilter.properties"})
public class BookFilter {

    @Autowired
    private Environment environment;

    private double minPrice=Double.parseDouble(environment.getRequiredProperty("minPrice"));
    private double maxPrice=Double.parseDouble(environment.getRequiredProperty("maxPrice"));
    private long category=Long.parseLong(environment.getRequiredProperty("category_id"));

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
