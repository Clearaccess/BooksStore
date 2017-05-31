package com.university.books.store.service.filter;

import com.university.books.store.model.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Aleksandr on 5/27/2017.
 */
@Component
@PropertySource(value = {"classpath:defaultBookFilter.properties"})
public class BookFilter {

    @Autowired
    private Environment environment;

    @Value("${minPrice}")
    private double minPrice=0.0;

    @Value("${maxPrice}")
    private double maxPrice=0x1.fffffffffffffP+1023;

    @Value("${category_id}")
    private long category=0;

    public double getMinPrice() {
        return minPrice;
    }

    public BookFilter setMinPrice(double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public BookFilter setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public long getCategory() {
        return category;
    }

    public BookFilter setCategory(long category) {
        this.category = category;
        return this;
    }
}
