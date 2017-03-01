package com.belichenko.a.messaga.data.models;

/**
 * Created by Belichenko Anton on 27.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class ChatMessage {

    public int id;
    public int creator_id;
    public int room_id;
    public String  message_type;
    public String text;
    public int message_status;
    public int temp_id;
    public long created_at;
    public long updated_at;

    public ChatMessage(String msg){
        id = 1;
        creator_id = 2;
        room_id = 3;
        message_type = "Text";
        text = msg;
        message_status = 0;
        temp_id = 1;
        created_at = 232434232;
    }

}
