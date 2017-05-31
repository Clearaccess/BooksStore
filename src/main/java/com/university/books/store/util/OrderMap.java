package com.university.books.store.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 5/31/2017.
 */
public class OrderMap {
    public static Map<Integer, String> orders=new HashMap<Integer, String>(){{
        put(1,"Популярности");
        put(2,"Новизне");
        put(3,"Цене");
    }};
}
