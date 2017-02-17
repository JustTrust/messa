package com.belichenko.a.messaga;

import com.google.gson.Gson;

/**
 * Created by Belichenko Anton on 17.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class Utils {

    static public <T> T fromGson(Class<T> type, String gson) {
        return new Gson().fromJson(gson, type);
    }

    public static String toGson(Object object){
        return new Gson().toJson(object);
    }

}
