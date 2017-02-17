package com.belichenko.a.messaga;

import android.location.Location;

/**
 * Created by Belichenko Anton on 17.02.17.
 * mailto: a.belichenko@gmail.com
 */
public class Send {
    private static Send ourInstance = new Send();

    public static Send getInstance() {
        if (ourInstance == null) {
            ourInstance = new Send();
        }
        return ourInstance;
    }

    private Send() {
    }

    public void message(String msg) {
        MsgWebSocket.getInstance().sendMessage(msg);
    }

    public void picture(String url){
        MsgWebSocket.getInstance().sendMessage(url);
    }

    public void location(Location location){
        String msg = Utils.toGson(location);
        MsgWebSocket.getInstance().sendMessage(msg);
    }
}
