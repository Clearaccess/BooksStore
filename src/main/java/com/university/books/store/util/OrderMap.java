package com.university.books.store.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 5/31/2017.
 */
public class OrderMap {
    public static Map<String, String> orders=new HashMap<String, String>(){{
        put("0","Упорядочить");
        put("1","По популярности");
        put("2","Новизне");
        put("3","Цене");
    }};
}
